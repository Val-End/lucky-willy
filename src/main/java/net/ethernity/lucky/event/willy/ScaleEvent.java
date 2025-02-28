package net.ethernity.lucky.event.willy;

import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class ScaleEvent extends LuckyEvent {
    public ScaleEvent() {
        super(1);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        if(world.random.nextFloat() > 0.5f)
            player.addStatusEffect(new StatusEffectInstance(LuckyWillyEffects.TINY, 600, 3));
        else
            player.addStatusEffect(new StatusEffectInstance(LuckyWillyEffects.GIANT, 600, 3));
    }
}
