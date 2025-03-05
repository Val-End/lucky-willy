package net.ethernity.lucky.event.global;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.network.LuckyWillyNetwork;
import net.ethernity.lucky.network.packet.s2c.SwitchCameraPayload;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SwitchCameraEvent extends LuckyEvent {
    private PlayerEntity player;

    public SwitchCameraEvent() {
        super(-1);
    }

    @Override
    public void execute(BlockPos pos, World world, PlayerEntity player) {
        this.setPlayer(player);
        LivingEntity nearestEntity = world.getEntitiesByClass(LivingEntity.class, player.getBoundingBox().expand(50), this::predicate)
                .stream().min(this::compare).orElse(null);

        if (nearestEntity != null && player instanceof ServerPlayerEntity serverPlayer) {
            LuckyWillyNetwork.s2c(serverPlayer, new SwitchCameraPayload(nearestEntity.getId()));
            LuckyWilly.queueServerWork(600, () -> LuckyWillyNetwork.s2c(serverPlayer, new SwitchCameraPayload(this.getPlayer().getId())));
        }
    }

    private void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    private PlayerEntity getPlayer() {
        return this.player;
    }

    private boolean predicate(LivingEntity entity) {
        return entity != this.getPlayer() && entity.isAlive();
    }

    private int compare(LivingEntity e1, LivingEntity e2) {
        return Float.compare(e1.distanceTo(this.getPlayer()), e2.distanceTo(this.getPlayer()));
    }
}
