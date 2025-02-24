package net.ethernity.lucky.event.vanilla;

import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class StoneKitEvent extends LuckyEvent {
    public StoneKitEvent() {
        super(1);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        this.dropStack(Items.STONE_AXE.getDefaultStack(), world, pos);
        this.dropStack(Items.STONE_HOE.getDefaultStack(), world, pos);
        this.dropStack(Items.STONE_SWORD.getDefaultStack(), world, pos);
        this.dropStack(Items.STONE_SHOVEL.getDefaultStack(), world, pos);
        this.dropStack(Items.STONE_PICKAXE.getDefaultStack(), world, pos);
    }
}
