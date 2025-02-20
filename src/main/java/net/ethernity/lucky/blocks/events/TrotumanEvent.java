package net.ethernity.lucky.blocks.events;

import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.ethernity.lucky.entity.TrotumanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class TrotumanEvent extends LuckyEvent {
    protected TrotumanEvent() {
        super(0.0f);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        TrotumanEntity trotuman = LuckyWillyEntities.TROTUMAN.create(world);
        trotuman.setOwner(player);
        this.spawnMob(trotuman, world, pos);
    }
}
