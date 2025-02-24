package net.ethernity.lucky.event.vanilla;

import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.block.Blocks;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class LapizBlockEvent extends LuckyEvent {
    public LapizBlockEvent() {
        super(0);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        BlockPos fallPos = pos.add(0, 20, 0);
        FallingBlockEntity.spawnFromBlock(world, fallPos, Blocks.LAPIS_BLOCK.getDefaultState());

        Vec3d pos3d = fallPos.toCenterPos();
        world.spawnParticles(ParticleTypes.FALLING_DRIPSTONE_WATER, pos3d.getX(), pos3d.getY(), pos3d.getZ(), 50, 0.5, 0.5, 0.5, 1);
    }
}
