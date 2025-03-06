package net.ethernity.lucky.event.global;

import net.ethernity.lucky.entity.PaintingEntity;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.network.LuckyWillyNetwork;
import net.ethernity.lucky.network.packet.s2c.SwitchCameraPayload;
import net.ethernity.lucky.server.LuckyWillyServer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SwitchCameraEvent extends LuckyEvent {
    public SwitchCameraEvent() {
        super(-1);
    }

    @Override
    public void execute(BlockPos pos, World world, PlayerEntity player) {
        LivingEntity nearestEntity = world.getEntitiesByClass(LivingEntity.class, player.getBoundingBox()
                        .expand(50), (entity) -> this.predicate(entity, player))
                .stream().min((e1, e2) -> this.compare(e1, e2, player)).orElse(null);

        if (nearestEntity != null && player instanceof ServerPlayerEntity serverPlayer) {
            LuckyWillyNetwork.s2c(serverPlayer, new SwitchCameraPayload(nearestEntity.getId()));
            LuckyWillyServer.queueWork(600, () -> LuckyWillyNetwork.s2c(serverPlayer, new SwitchCameraPayload(serverPlayer.getId())));
        }
    }

    private boolean predicate(LivingEntity entity, PlayerEntity player) {
        return entity != player && entity.isAlive() && !(entity instanceof PaintingEntity);
    }

    private int compare(LivingEntity e1, LivingEntity e2, PlayerEntity player) {
        return Float.compare(e1.distanceTo(player), e2.distanceTo(player));
    }
}
