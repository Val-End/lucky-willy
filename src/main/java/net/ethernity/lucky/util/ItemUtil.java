package net.ethernity.lucky.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

public class ItemUtil {
    public static void enchant(ItemStack stack, RegistryKey<Enchantment> enchantment, int level, World world) {
        RegistryEntry<Enchantment> entry = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(enchantment).get();
        stack.addEnchantment(entry, level);
    }
}
