package net.ethernity.lucky.items;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.blocks.LuckyWilllyBlocks;
import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LuckyWillyItems {
    public static final Item SONIC_SPAWN_EGG = register(
            new SpawnEggItem(LuckyWillyEntities.SONIC, 0x255fba, 0xe3363c, new Item.Settings()),
            "sonic_spawn_egg"
    );

    public static final Item TAILS_SPAWN_EGG = register(
            new SpawnEggItem(LuckyWillyEntities.TAILS, 0xf8a346, 0xfbf273, new Item.Settings()),
            "tails_spawn_egg"
    );

    public static final Item KNUCKLES_SPAWN_EGG = register(
            new SpawnEggItem(LuckyWillyEntities.KNUCKLES, 0xee4e53, 0xe9edf8, new Item.Settings()),
            "knuckles_spawn_egg"
    );

    public static final Item AMY_SPAWN_EGG = register(
            new SpawnEggItem(LuckyWillyEntities.AMY, 0xfeaad4, 0xff5b52, new Item.Settings()),
            "amy_spawn_egg"
    );

    public static final Item SILVER_SPAWN_EGG = register(
            new SpawnEggItem(LuckyWillyEntities.SILVER, 0xd8e2eb, 0xffd254, new Item.Settings()),
            "silver_spawn_egg"
    );

    public static final Item SHADOW_SPAWN_EGG = register(
            new SpawnEggItem(LuckyWillyEntities.SHADOW, 0x232323, 0xbc2641, new Item.Settings()),
            "shadow_spawn_egg"
    );

    public static final Item METAL_SONIC_SPAWN_EGG = register(
            new SpawnEggItem(LuckyWillyEntities.METAL_SONIC, 0x0140a3, 0xfce283, new Item.Settings()),
            "sonic_robot_spawn_egg"
    );

    public static final Item EGGMAN_SPAWN_EGG = register(
            new SpawnEggItem(LuckyWillyEntities.EGGMAN, 0xe3363c, 0xfce283, new Item.Settings()),
            "eggman_spawn_egg"
    );

    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(LuckyWilly.MOD_ID, id);
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);
        return registeredItem;
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register((itemGroup) -> {
            itemGroup.add(LuckyWillyItems.SONIC_SPAWN_EGG.asItem());
            itemGroup.add(LuckyWillyItems.TAILS_SPAWN_EGG.asItem());
            itemGroup.add(LuckyWillyItems.KNUCKLES_SPAWN_EGG.asItem());
            itemGroup.add(LuckyWillyItems.AMY_SPAWN_EGG.asItem());
            itemGroup.add(LuckyWillyItems.SILVER_SPAWN_EGG.asItem());
            itemGroup.add(LuckyWillyItems.SHADOW_SPAWN_EGG.asItem());
            itemGroup.add(LuckyWillyItems.METAL_SONIC_SPAWN_EGG.asItem());
            itemGroup.add(LuckyWillyItems.EGGMAN_SPAWN_EGG.asItem());
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register((itemGroup) -> {
            itemGroup.add(LuckyWilllyBlocks.WEAPON_LUCKY_BLOCK.asItem());
            itemGroup.add(LuckyWilllyBlocks.ARMOR_LUCKY_BLOCK.asItem());
            itemGroup.add(LuckyWilllyBlocks.WILLY_LUCKY_BLOCK.asItem());
        });
    }
}
