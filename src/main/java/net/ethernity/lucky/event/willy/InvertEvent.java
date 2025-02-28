package net.ethernity.lucky.event.willy;

import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class InvertEvent extends LuckyEvent {

    protected InvertEvent() {
        super(1);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(LuckyWillyEffects.INVERT, 600, 3));
    }
}
