package net.ethernity.lucky.items;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.block.LuckyWilllyBlocks;
import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LuckyWillyItems {
    public static final Item TROTUMAN_SPAWN_EGG = registerEgg("trotuman", LuckyWillyEntities.TROTUMAN, 0x0AF991, 0x001F8B);

    private static Item registerEgg(String id, EntityType entity, int firstColor, int secondColor) {
        return LuckyWillyItems.register(new SpawnEggItem(entity, firstColor, secondColor, new Item.Settings()),id + "_spawn_egg");
    }

    protected static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(LuckyWilly.MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register((itemGroup) -> {
            itemGroup.add(LuckyWillyItems.TROTUMAN_SPAWN_EGG);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register((itemGroup) -> {
            itemGroup.add(LuckyWilllyBlocks.LUCKY_BLOCK.asItem());
            itemGroup.add(LuckyWilllyBlocks.WEAPON_LUCKY_BLOCK.asItem());
            itemGroup.add(LuckyWilllyBlocks.ARMOR_LUCKY_BLOCK.asItem());
            itemGroup.add(LuckyWilllyBlocks.WILLY_LUCKY_BLOCK.asItem());
        });
    }
}
