package net.ethernity.lucky.items;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.block.LuckyBlock;
import net.ethernity.lucky.block.LuckyWilllyBlocks;
import net.ethernity.lucky.component.LuckyDataComponentTypes;
import net.ethernity.lucky.component.type.LuckysComponent;
import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LuckyWillyItems {
    public static final Item SLOW_TIME_ITEM = register(new SlowTimeItem(new Item.Settings()), "slow_time");
    public static final Item TROTUMAN_SPAWN_EGG = registerEgg("trotuman", LuckyWillyEntities.TROTUMAN, 0x0AF991, 0x001F8B);

    private static Item registerEgg(String id, EntityType entity, int firstColor, int secondColor) {
        return LuckyWillyItems.register(new SpawnEggItem(entity, firstColor, secondColor, new Item.Settings()), id + "_spawn_egg");
    }

    protected static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(LuckyWilly.MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup) -> {
            itemGroup.add(LuckyWillyItems.SLOW_TIME_ITEM);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register((itemGroup) -> {
            itemGroup.add(LuckyWillyItems.TROTUMAN_SPAWN_EGG);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register((itemGroup) -> {
            addLuckyVariants(itemGroup, LuckyWilllyBlocks.LUCKY_BLOCK);
            addLuckyVariants(itemGroup, LuckyWilllyBlocks.ARMOR_LUCKY_BLOCK);
            addLuckyVariants(itemGroup, LuckyWilllyBlocks.WEAPON_LUCKY_BLOCK);
            addLuckyVariants(itemGroup, LuckyWilllyBlocks.WILLY_LUCKY_BLOCK);
        });
    }

    private static void addLuckyVariants(FabricItemGroupEntries entries, Block block) {
        for (byte lucky : LuckyBlock.LUCKY_VALUES) {
            ItemStack itemStack = block.asItem().getDefaultStack();
            itemStack.set(LuckyDataComponentTypes.LUCKYS, new LuckysComponent(lucky + 1));
            entries.add(itemStack);
        }
    }
}
