package net.ethernity.lucky.event.willy;

import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.util.ItemUtil;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EquipCurseEvent extends LuckyEvent {
    private final Item item;

    public EquipCurseEvent(Item item) {
        super(-1);
        this.item = item;
    }

    @Override
    public void execute(BlockPos pos, World world, PlayerEntity player) {
        ItemStack stack = item.getDefaultStack();
        if (stack.isOf(Items.CARVED_PUMPKIN)) {
            player.dropStack(player.getEquippedStack(EquipmentSlot.HEAD));
            player.equipStack(EquipmentSlot.HEAD, stack);
            return;
        }

        ItemUtil.enchant(stack, Enchantments.BINDING_CURSE, 1, world);
        if (item instanceof ArmorItem armor) {
            player.dropStack(player.getEquippedStack(armor.getSlotType()));
            player.equipStack(armor.getSlotType(), stack);
        }
    }
}
