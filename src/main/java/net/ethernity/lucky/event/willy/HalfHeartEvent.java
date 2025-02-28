package net.ethernity.lucky.event.willy;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.entity.damage.LuckyWillyDamageTypes;
import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class HalfHeartEvent extends LuckyEvent {
    public HalfHeartEvent() {
        super(-1);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        for(int i = 0; i < 20; i++) {
            LuckyWilly.queueServerWork(i, () -> {
                if(player.getHealth() > 2)
                    player.damage(LuckyWillyDamageTypes.of(world, LuckyWillyDamageTypes.LUCKY_DAMAGE_TYPE), 1f);
            });
        }
    }
}
