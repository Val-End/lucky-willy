package net.ethernity.lucky.block;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.event.armor.ArmorLuckyEvents;
import net.ethernity.lucky.event.vanilla.VanillaLuckyEvents;
import net.ethernity.lucky.event.weapon.WeaponLuckyEvents;
import net.ethernity.lucky.event.willy.WillyLuckyEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LuckyWilllyBlocks {
    public static final Block LUCKY_BLOCK = register(new LuckyBlock(VanillaLuckyEvents.ID), "lucky_block", true);
    public static final Block WEAPON_LUCKY_BLOCK = register(new LuckyBlock(WeaponLuckyEvents.ID), "weapon_lucky_block", true);
    public static final Block ARMOR_LUCKY_BLOCK = register(new LuckyBlock(ArmorLuckyEvents.ID), "armor_lucky_block", true);
    public static final Block WILLY_LUCKY_BLOCK = register(new LuckyBlock(WillyLuckyEvents.ID), "willy_lucky_block", true);

    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        Identifier id = Identifier.of(LuckyWilly.MOD_ID, name);
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void initialize() {}
}
