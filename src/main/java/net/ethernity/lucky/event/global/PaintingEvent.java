package net.ethernity.lucky.event.global;

import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class PaintingEvent extends LuckyEvent {
    public PaintingEvent() {
        super(-1);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        this.spawnMob(LuckyWillyEntities.PAINTING, world, pos);
    }
}
