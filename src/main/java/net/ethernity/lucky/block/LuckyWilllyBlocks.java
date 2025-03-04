package net.ethernity.lucky.block;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.component.LuckyDataComponentTypes;
import net.ethernity.lucky.component.type.LuckysComponent;
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
    public static final Block LUCKY_BLOCK = registerLuckyBlock(VanillaLuckyEvents.ID);
    public static final Block WEAPON_LUCKY_BLOCK = registerLuckyBlock(WeaponLuckyEvents.ID);
    public static final Block ARMOR_LUCKY_BLOCK = registerLuckyBlock(ArmorLuckyEvents.ID);
    public static final Block WILLY_LUCKY_BLOCK = registerLuckyBlock(WillyLuckyEvents.ID);

    private static Block register(Block block, String name, boolean shouldRegisterItem) {
        Identifier id = Identifier.of(LuckyWilly.MOD_ID, name);
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }

    private static Block registerLuckyBlock(String ID) {
        String name = ID != VanillaLuckyEvents.ID ? ID + "_lucky_block" : "lucky_block";
        Block block = new LuckyBlock(ID);
        BlockItem blockItem = new BlockItem(block, new Item.Settings().component(LuckyDataComponentTypes.LUCKYS, new LuckysComponent(1)));

        Registry.register(Registries.ITEM, Identifier.of(LuckyWilly.MOD_ID, name), blockItem);
        return register(block, name, false);
    }

    public static void initialize() {
    }
}
