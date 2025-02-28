package net.ethernity.lucky.event.willy;

import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class ScreamerEvent extends LuckyEvent {

    protected ScreamerEvent() {
        super(1);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(LuckyWillyEffects.SCREAMER, 2, 1));
    }
}
