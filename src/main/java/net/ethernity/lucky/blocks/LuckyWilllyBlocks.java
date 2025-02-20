package net.ethernity.lucky.blocks;

import net.ethernity.lucky.LuckyWilly;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class LuckyWilllyBlocks {
    public static final Block WEAPON_LUCKY_BLOCK = register(new WeaponLuckyBlock(), "weapon_lucky_block", true);
    public static final Block ARMOR_LUCKY_BLOCK = register(new ArmorLuckyBlock(), "armor_lucky_block", true);
    public static final Block WILLY_LUCKY_BLOCK = register(new WillyLuckyBlock(), "willy_lucky_block", true);

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
