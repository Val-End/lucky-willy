package net.ethernity.lucky.block;

import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.event.LuckyEvents;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LuckyBlock extends Block {
    private final String luckyID;

    public LuckyBlock(String id) {
        super(AbstractBlock.Settings.create()
                .sounds(BlockSoundGroup.METAL)
                .strength(0.2F, 1200.0F));

        this.luckyID = id;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        player.incrementStat(Stats.MINED.getOrCreateStat(this));
        player.addExhaustion(0.005F);
        if(world instanceof ServerWorld svWorld)
            this.executeEvent(pos, svWorld, player);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        this.isPowered(state, world, pos);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean notify) {
        if (!world.isClient)
            this.isPowered(state, world, pos);

        super.neighborUpdate(state, world, pos, neighborBlock, fromPos, notify);
    }

    private void isPowered(BlockState state, World world, BlockPos pos) {
        if (!world.isReceivingRedstonePower(pos))
            return;

        world.breakBlock(pos, true);

        Vec3d pos3d = pos.toCenterPos();
        PlayerEntity player = world.getClosestPlayer(pos3d.getX(), pos3d.getY(), pos3d.getZ(), 32, true);
        if(player != null)
            this.afterBreak(world, player, pos, state, null, ItemStack.EMPTY);
    }

    private void executeEvent(BlockPos pos, ServerWorld world, PlayerEntity player) {
        List<LuckyEvent> events = this.getEvents();
        if(events.isEmpty())
            return;

        LuckyEvent event = events.get(world.random.nextInt(events.size()));
        event.execute(pos, world, player);
    }

    public List<LuckyEvent> getEvents() {
        return LuckyWillyRegistries.LUCKY_EVENT.stream().filter(key ->
                LuckyEvents.getId(key).getPath().startsWith(this.luckyID)
        ).toList();
    }

    @Override
    protected List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        return List.of();
    }
}
