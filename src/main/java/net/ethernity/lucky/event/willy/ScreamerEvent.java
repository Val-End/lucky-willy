package net.ethernity.lucky.event.willy;

import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.network.LuckyWillyNetwork;
import net.ethernity.lucky.network.packet.s2c.ScreamerPayload;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ScreamerEvent extends LuckyEvent {
    protected ScreamerEvent() {
        super(1);
    }

    @Override
    public void execute(BlockPos pos, World world, PlayerEntity player) {
        LuckyWillyNetwork.s2c((ServerPlayerEntity) player, new ScreamerPayload());
    }
}
