package net.ethernity.lucky.event.vanilla;

import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ExplosionEvent extends LuckyEvent {
    public ExplosionEvent() {
        super(-1);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        Vec3d pos3d = pos.toCenterPos();
        world.createExplosion(player, pos3d.getX(), pos3d.getY(), pos3d.getZ(), 1.0f, false, World.ExplosionSourceType.BLOCK);
    }
}
