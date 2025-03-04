package net.ethernity.lucky.event.vanilla;

import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.util.ItemUtil;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BobEvent extends LuckyEvent {
    protected BobEvent() {
        super(-1);
    }

    @Override
    public void execute(BlockPos pos, World world, PlayerEntity player) {
        ZombieEntity bob = new ZombieEntity(world);
        bob.equipStack(EquipmentSlot.HEAD, this.createEnchantedItem(Items.DIAMOND_HELMET, world));
        bob.equipStack(EquipmentSlot.CHEST, this.createEnchantedItem(Items.DIAMOND_CHESTPLATE, world));
        bob.equipStack(EquipmentSlot.LEGS, this.createEnchantedItem(Items.DIAMOND_LEGGINGS, world));
        bob.equipStack(EquipmentSlot.FEET, this.createEnchantedItem(Items.DIAMOND_BOOTS, world));
        bob.equipStack(EquipmentSlot.MAINHAND, this.createEnchantedWeapon(world));

        bob.setCustomName(Text.of("Bob").copy().setStyle(Style.EMPTY.withBold(true).withColor(Formatting.BLUE)));
        bob.setCanPickUpLoot(false);
        bob.setPersistent();

        this.spawnMob(bob, world, pos);
    }

    private ItemStack createEnchantedItem(net.minecraft.item.Item item, World world) {
        ItemStack stack = new ItemStack(item);
        ItemUtil.enchant(stack, Enchantments.PROTECTION, 4, world);
        ItemUtil.enchant(stack, Enchantments.UNBREAKING, 3, world);
        ItemUtil.enchant(stack, Enchantments.MENDING, 1, world);
        ItemUtil.enchant(stack, Enchantments.THORNS, 3, world);
        if (item == Items.DIAMOND_BOOTS)
            ItemUtil.enchant(stack, Enchantments.FEATHER_FALLING, 4, world);

        return stack;
    }

    private ItemStack createEnchantedWeapon(World world) {
        ItemStack sword = new ItemStack(Items.DIAMOND_SWORD);
        ItemUtil.enchant(sword, Enchantments.SHARPNESS, 5, world);
        ItemUtil.enchant(sword, Enchantments.SWEEPING_EDGE, 3, world);
        ItemUtil.enchant(sword, Enchantments.UNBREAKING, 3, world);
        ItemUtil.enchant(sword, Enchantments.MENDING, 1, world);
        ItemUtil.enchant(sword, Enchantments.FIRE_ASPECT, 2, world);
        ItemUtil.enchant(sword, Enchantments.LOOTING, 3, world);
        ItemUtil.enchant(sword, Enchantments.KNOCKBACK, 2, world);
        return sword;
    }
}
