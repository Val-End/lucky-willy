package net.ethernity.lucky.util;

import net.ethernity.lucky.LuckyWilly;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ItemUtil {
    public static void enchant(ItemStack stack, RegistryKey<Enchantment> enchantment, int level, World world) {
        RegistryEntry<Enchantment> entry = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(enchantment).get();
        stack.addEnchantment(entry, level);
    }

    /**
     * Gets an ItemStack from a mod using its identifier.
     *
     * @param id The identifier of the mod item
     * @return The ItemStack, or ItemStack.EMPTY if the mod is not loaded
     */
    public static ItemStack modStack(Identifier id) {
        if (!FabricLoader.getInstance().isModLoaded(id.getNamespace())) {
            LuckyWilly.LOGGER.error("{} is not loaded yet", id);
            return ItemStack.EMPTY;
        }

        LuckyWilly.LOGGER.info(Registries.ITEM.get(id).toString());
        return Registries.ITEM.get(id).getDefaultStack();
    }
}
