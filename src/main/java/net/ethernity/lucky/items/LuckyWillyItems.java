package net.ethernity.lucky.items;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.blocks.LuckyWilllyBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LuckyWillyItems {
    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(LuckyWilly.MOD_ID, id);
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);
        return registeredItem;
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register((itemGroup) -> {
            // Spawn Eggs
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register((itemGroup) -> {
            itemGroup.add(LuckyWilllyBlocks.WEAPON_LUCKY_BLOCK.asItem());
            itemGroup.add(LuckyWilllyBlocks.ARMOR_LUCKY_BLOCK.asItem());
            itemGroup.add(LuckyWilllyBlocks.WILLY_LUCKY_BLOCK.asItem());
        });
    }
}
