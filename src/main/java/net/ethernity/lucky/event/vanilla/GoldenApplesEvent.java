package net.ethernity.lucky.event.vanilla;

import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class GoldenApplesEvent extends LuckyEvent {
    public GoldenApplesEvent() {
        super(1);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        this.dropStack(Items.GOLDEN_APPLE.getDefaultStack().copyWithCount(3), world, pos);
        this.dropStack(Items.ENCHANTED_GOLDEN_APPLE.getDefaultStack(), world, pos);
    }
}
