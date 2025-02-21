package net.ethernity.lucky.event.vanilla;

import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class ChargedCreeperBlockEvent extends LuckyEvent {
    public ChargedCreeperBlockEvent() {
        super(-1);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        this.spawnMob(EntityType.CREEPER, world, pos);
        LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
        lightning.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos));
        world.spawnEntity(lightning);
    }
}
