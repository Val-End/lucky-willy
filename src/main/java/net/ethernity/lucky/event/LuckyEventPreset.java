package net.ethernity.lucky.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;

public abstract class LuckyEventPreset {

    public static LuckyEvent empty() {
        return new LuckyEvent(0) {
            public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {}
        };
    }
    /*
     * Use this if you want to access another mod item.
     */
    public static LuckyEvent giveStack(int lucky, Identifier id) {
        return LuckyEventPreset.giveStack(lucky, LuckyEvent.getModStack(id));
    }

    public static LuckyEvent giveStack(int lucky, ItemStack stack) {
        return new LuckyEvent(lucky) {
            public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
                this.giveStack(stack, player);
            }
        };
    }

    /*
     * Use this if you want to access another mod item.
     */
    public static LuckyEvent dropStack(int lucky, Identifier id) {
        return LuckyEventPreset.dropStack(lucky, LuckyEvent.getModStack(id));
    }

    public static LuckyEvent dropStack(int lucky, ItemStack stack) {
        return new LuckyEvent(lucky) {
            @Override
            public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
                this.dropStack(stack, world, pos);
            }
        };
    }

    /*
     * Use this if you want to access another mod entity.
     */
    public static LuckyEvent spawnMob(int lucky, Identifier id) {
        return new LuckyEvent(lucky) {
            @Override
            public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
                this.spawnMob(id, world, pos);
            }
        };
    }

    public static <T extends Entity> LuckyEvent spawnMob(int lucky, EntityType<T> type) {
        return new LuckyEvent(lucky) {
            @Override
            public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
                this.spawnMob(type, world, pos);
            }
        };
    }

    public static LuckyEvent placeFeature(int lucky, RegistryKey<Feature<?>> key, boolean placeOnPlayer) {
        return new LuckyEvent(lucky) {
            public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
                if(placeOnPlayer)
                    this.placeFeature(key, world, player);
                else
                    this.placeFeature(key, world, pos);
            }
        };
    }
}
