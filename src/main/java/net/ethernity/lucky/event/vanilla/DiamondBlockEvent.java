package net.ethernity.lucky.event.vanilla;

import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class DiamondBlockEvent extends LuckyEvent {
    public DiamondBlockEvent() {
        super(1);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        FallingBlockEntity.spawnFromBlock(world, pos.add(0, 20, 0), Blocks.DIAMOND_BLOCK.getDefaultState());
        this.spawnMob(EntityType.LIGHTNING_BOLT, world, pos);
    }
}
