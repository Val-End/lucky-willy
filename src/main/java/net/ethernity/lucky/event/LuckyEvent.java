package net.ethernity.lucky.event;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.util.ItemUtil;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Optional;

/**
 * Base class for lucky events that can be triggered in the game.
 * Events carry a luck value and provide functionality for interacting with the game world.
 * SPONSOR CHATGPT (Le pedi que documente el codigo)
 */
public abstract class LuckyEvent {
    private final int lucky;

    /**
     * Creates a new lucky event with the specified luck value.
     *
     * @param lucky The luck value affecting this event
     */
    protected LuckyEvent(int lucky) {
        this.lucky = lucky;
    }

    /**
     * Determines if this event can be executed.
     *
     * @return True if the event can be executed, false otherwise
     */
    public boolean isExecutable() {
        return true;
    }

    /**
     * Gets the luck value of this event.
     *
     * @return The luck value
     */
    public int getLucky() {
        return this.lucky;
    }

    /**
     * Executes this event at the specified position.
     *
     * @param pos The position where the event occurs
     * @param world The world in which the event occurs
     * @param player The player triggering the event
     */
    public abstract void execute(BlockPos pos, ServerWorld world, PlayerEntity player);


    /**
     * Gives an item from another mod to a player.
     *
     * @param id The identifier of the mod item
     * @param player The player to receive the item
     */
    protected void giveStack(Identifier id, PlayerEntity player) {
        LuckyWilly.LOGGER.info("External item: {}", id);
        this.giveStack(ItemUtil.modStack(id), player);
    }

    /**
     * Gives an item to a player.
     *
     * @param item The item to give
     * @param player The player to receive the item
     */
    protected void giveStack(Item item, PlayerEntity player) {
        this.giveStack(item.getDefaultStack(), player);
    }

    /**
     * Gives an item to a player.
     *
     * @param stack The item to give
     * @param player The player to receive the item
     */
    protected void giveStack(ItemStack stack, PlayerEntity player) {
        LuckyWilly.LOGGER.info("Giving item: {}", stack.toString());
        player.giveItemStack(stack);
    }

    /**
     * Drops an item from another mod at a specific position.
     *
     * @param id The identifier of the mod item
     * @param world The world to drop the item in
     * @param pos The position to drop the item at
     */
    protected void dropStack(Identifier id, ServerWorld world, BlockPos pos) {
        LuckyWilly.LOGGER.info("External item: {}", id);
        this.dropStack(ItemUtil.modStack(id), world, pos);
    }

    /**
     * Drops an item at a specific position.
     *
     * @param item The item to drop
     * @param world The world to drop the item in
     * @param pos The position to drop the item at
     */
    protected void dropStack(Item item, ServerWorld world, BlockPos pos) {
        this.dropStack(item.getDefaultStack(), world, pos);
    }

    /**
     * Drops an item at a specific position.
     *
     * @param stack The item to drop
     * @param world The world to drop the item in
     * @param pos The position to drop the item at
     */
    protected void dropStack(ItemStack stack, ServerWorld world, BlockPos pos) {
        LuckyWilly.LOGGER.info("Dropping item: {}", stack.toString());
        Block.dropStack(world, pos, stack);
    }

    /**
     * Spawns a mob from another mod at a specific position.
     *
     * @param id The identifier of the mod entity
     * @param world The world to spawn the entity in
     * @param pos The position to spawn the entity at
     */
    protected void spawnMob(Identifier id, ServerWorld world, BlockPos pos) {
        if (!FabricLoader.getInstance().isModLoaded(id.getNamespace())) {
            LuckyWilly.LOGGER.warn("Mod {} is not loaded, cannot spawn entity", id.getNamespace());
            return;
        }

        EntityType<?> type = Registries.ENTITY_TYPE.get(id);
        this.spawnMob(type, world, pos);
    }

    /**
     * Spawns a mob of a specific type at a position.
     *
     * @param <T> The entity type
     * @param type The entity type to spawn
     * @param world The world to spawn the entity in
     * @param pos The position to spawn the entity at
     */
    protected <T extends Entity> void spawnMob(EntityType<T> type, ServerWorld world, BlockPos pos) {
        T entity = type.create(world);
        if (entity != null)
            this.spawnMob(entity, world, pos);
        else
            LuckyWilly.LOGGER.warn("Failed to create entity of type {}", type);
    }

    /**
     * Spawns a pre-created entity at a position.
     *
     * @param <T> The entity type
     * @param entity The entity to spawn
     * @param world The world to spawn the entity in
     * @param pos The position to spawn the entity at
     */
    protected <T extends Entity> void spawnMob(T entity, ServerWorld world, BlockPos pos) {
        entity.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
        world.spawnEntity(entity);
        LuckyWilly.LOGGER.warn("Mob Spawned: {}", entity.getType().toString());
    }

    /**
     * Places a feature at a player's position.
     *
     * @param key The registry key of the feature
     * @param world The world to place the feature in
     * @param player The player whose position to use
     */
    protected void placeFeature(RegistryKey<Feature<?>> key, ServerWorld world, PlayerEntity player) {
        this.placeFeature(key, world, player.getBlockPos());
    }

    /**
     * Places a feature at a specific position.
     *
     * @param key The registry key of the feature
     * @param world The world to place the feature in
     * @param pos The position to place the feature at
     */
    protected void placeFeature(RegistryKey<Feature<?>> key, ServerWorld world, BlockPos pos) {
        Optional<Feature<?>> feature = world.getRegistryManager().get(RegistryKeys.FEATURE)
                .getEntry(key)
                .flatMap(entry -> Optional.ofNullable(entry.value()));

        if (feature.isEmpty()) {
            LuckyWilly.LOGGER.warn("Feature with key {} not found", key.toString());
            return;
        }

        LuckyWilly.LOGGER.warn("Placing Feature: {}", key.toString());
        feature.get().generate(new FeatureContext<>(
            Optional.empty(),
            world,
            world.getChunkManager().getChunkGenerator(),
            world.getRandom(),
            pos,
            null
        ));
    }

    /**
     * Applies a default status effect to the player with a duration of 10 ticks and no amplifier.
     *
     * @param player The player to apply the effect to.
     * @param effect The status effect to apply.
     */
    protected void applyEffect(PlayerEntity player, RegistryEntry effect) {
        this.applyEffect(player, effect, 200);
    }

    /**
     * Applies a status effect to the player with a specified duration and no amplifier.
     *
     * @param player   The player to apply the effect to.
     * @param effect   The status effect to apply.
     * @param duration The duration of the effect in ticks.
     */
    protected void applyEffect(PlayerEntity player, RegistryEntry effect, int duration) {
        this.applyEffect(player, effect, duration, 0);
    }

    /**
     * Applies a status effect to the player with a specified duration and amplifier level.
     *
     * @param player    The player to apply the effect to.
     * @param effect    The status effect to apply.
     * @param duration  The duration of the effect in ticks.
     * @param amplifier The amplifier level of the effect (higher values increase potency).
     */
    protected void applyEffect(PlayerEntity player, RegistryEntry effect, int duration, int amplifier) {
        player.addStatusEffect(new StatusEffectInstance(effect, duration, amplifier));
        LuckyWilly.LOGGER.warn("Effect Applied: {}", effect.getIdAsString());
    }
}