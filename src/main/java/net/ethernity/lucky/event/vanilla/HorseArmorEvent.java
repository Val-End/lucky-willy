package net.ethernity.lucky.event.vanilla;

import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class HorseArmorEvent extends LuckyEvent {
    public HorseArmorEvent() {
        super(0);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        this.dropStack(Items.DIAMOND_HORSE_ARMOR.getDefaultStack(), world, pos);
        this.dropStack(Items.GOLDEN_HORSE_ARMOR.getDefaultStack(), world, pos);
        this.dropStack(Items.IRON_HORSE_ARMOR.getDefaultStack(), world, pos);
    }
}
