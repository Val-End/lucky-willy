package net.ethernity.lucky.event;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Optional;

abstract public class LuckyEvent {
    private final int lucky;

    protected LuckyEvent(int lucky) {
        this.lucky = lucky;
    }

    public boolean isExecutable() { return true; }

    public int getLucky() { return this.lucky; }

    public abstract void execute(BlockPos pos, ServerWorld world, PlayerEntity player);

    public static ItemStack getModStack(Identifier id) {
        if (!FabricLoader.getInstance().isModLoaded(id.getNamespace()))
            return ItemStack.EMPTY;

        return Registries.ITEM.get(id).getDefaultStack();
    }

    /*
     * Use this if you want to access another mod item.
     */
    protected void giveStack(Identifier id, PlayerEntity player) {
        this.giveStack(this.getModStack(id), player);
    }

    protected void giveStack(ItemStack stack, PlayerEntity player) {
        player.giveItemStack(stack);
    }

    /*
     * Use this if you want to access another mod item.
     */
    protected void dropStack(Identifier id, ServerWorld world, BlockPos pos) {
        this.dropStack(this.getModStack(id), world, pos);
    }

    protected void dropStack(ItemStack stack, ServerWorld world, BlockPos pos) {
        Block.dropStack(world, pos, stack);
    }

    /*
     * Use this if you want to access another mod entity.
     */
    protected void spawnMob(Identifier id, ServerWorld world, BlockPos pos) {
        if (!FabricLoader.getInstance().isModLoaded(id.getNamespace()))
            return;

        EntityType<?> type = Registries.ENTITY_TYPE.get(id);
        this.spawnMob(type, world, pos);
    }

    protected<T extends Entity> void spawnMob(EntityType<T> type, ServerWorld world, BlockPos pos) {
        this.spawnMob(type.create(world), world, pos);
    }

    protected<T extends Entity> void spawnMob(T entity, ServerWorld world, BlockPos pos) {
        entity.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
        world.spawnEntity(entity);
    }

    protected void placeFeature(RegistryKey<Feature<?>> key, ServerWorld world, PlayerEntity player) {
        this.placeFeature(key, world, player.getBlockPos());
    }

    protected void placeFeature(RegistryKey<Feature<?>> key, ServerWorld world, BlockPos pos) {
        Optional<Feature<?>> feature = world.getRegistryManager().get(RegistryKeys.FEATURE)
                .getEntry(key)
                .flatMap(entry -> Optional.ofNullable(entry.value()));

        feature.ifPresent(f -> f.generate(new FeatureContext<>(Optional.empty(), world, world.getChunkManager().getChunkGenerator(), world.getRandom(), pos, null)));
    }
}
