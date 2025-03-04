package net.ethernity.lucky.event.global;

import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.world.gen.LuckyWillyFeature;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CreeperFallEvent extends LuckyEvent {
    public CreeperFallEvent() {
        super(-1);
    }

    @Override
    public void execute(BlockPos pos, World world, PlayerEntity player) {
        this.spawnMob(EntityType.CREEPER, world, player.getBlockPos().add(0, 30, 0));
        this.placeFeature(LuckyWillyFeature.CREEPER_FALL_KEY, world, player);
    }
}
