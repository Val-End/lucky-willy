package net.ethernity.lucky.event.weapon;

import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.util.ItemUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

public class SmashCodEvent extends LuckyEvent {
    protected SmashCodEvent() {
        super(1);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        ItemStack stack = Items.COD.getDefaultStack();
        stack.set(DataComponentTypes.CUSTOM_NAME, Text.of("SMASH!").copy().setStyle(Style.EMPTY.withBold(true).withColor(Formatting.BLUE)));
        ItemUtil.enchant(stack, Enchantments.KNOCKBACK, 10, world);
        this.dropStack(stack, world, pos);
    }
}
