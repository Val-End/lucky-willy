package net.ethernity.lucky.blocks.events;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Optional;

abstract public class LuckyEvent {
    private final float lucky;

    protected LuckyEvent(float lucky) {
        this.lucky = lucky;
    }

    public float getLucky() { return this.lucky; }

    public abstract void execute(BlockPos pos, ServerWorld world, PlayerEntity player);

    protected void giveStack(ItemStack stack, PlayerEntity player) {
        player.giveItemStack(stack);
    }

    public static LuckyEvent createGiveEvent(ItemStack stack, float lucky) {
        return new LuckyEvent(lucky) {
            @Override
            public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
                this.giveStack(stack, player);
            }
        };
    }

    protected void dropStack(ItemStack stack, ServerWorld world, BlockPos pos) {
        Block.dropStack(world, pos, stack);
    }

    public static LuckyEvent createDropEvent(ItemStack stack, float lucky) {
        return new LuckyEvent(lucky) {
            @Override
            public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
                this.dropStack(stack, world, pos);
            }
        };
    }

    protected<T extends LivingEntity> void spawnMob(EntityType<T> type, ServerWorld world, BlockPos pos) {
        this.spawnMob(type.create(world), world, pos);
    }

    public static<T extends LivingEntity> LuckyEvent createSpawnMob(EntityType<T> type, float lucky) {
        return new LuckyEvent(lucky) {
            @Override
            public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
                this.spawnMob(type, world, pos);
            }
        };
    }

    protected<T extends LivingEntity> void spawnMob(T entity, ServerWorld world, BlockPos pos) {
        entity.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
        world.spawnEntity(entity);
    }

    public static<T extends LivingEntity> LuckyEvent createSpawnMob(T entity, float lucky) {
        return new LuckyEvent(lucky) {
            @Override
            public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
                this.spawnMob(entity, world, pos);
            }
        };
    }

    protected void placeFeature(RegistryKey<Feature<?>> key, ServerWorld world, PlayerEntity player) {
        this.placeFeature(key, world, player.getBlockPos());
    }

    public static<T extends LivingEntity> LuckyEvent createPlaceFeaturePlayer(RegistryKey<Feature<?>> key, float lucky) {
        return new LuckyEvent(lucky) {
            @Override
            public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
                this.placeFeature(key, world, player);
            }
        };
    }

    protected void placeFeature(RegistryKey<Feature<?>> key, ServerWorld world, BlockPos pos) {
        Optional<Feature<?>> feature = world.getRegistryManager().get(RegistryKeys.FEATURE)
                .getEntry(key)
                .flatMap(entry -> Optional.ofNullable(entry.value()));

        feature.ifPresent(f -> f.generate(new FeatureContext<>(Optional.empty(), world, world.getChunkManager().getChunkGenerator(), world.getRandom(), pos, null)));
    }

    public static<T extends LivingEntity> LuckyEvent createPlaceFeature(RegistryKey<Feature<?>> key, float lucky) {
        return new LuckyEvent(lucky) {
            @Override
            public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
                this.placeFeature(key, world, pos);
            }
        };
    }
}
