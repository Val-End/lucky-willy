package net.ethernity.lucky.event.armor;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.event.LuckyEventBuilder;
import net.ethernity.lucky.event.willy.EquipCurseEvent;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class ArmorLuckyEvents {
    public static final String ID = "armor";

    public static final LuckyEvent EQUIP_PUMPKIN_EVENT = register("equip_pumpkin_event", new EquipCurseEvent(Items.CARVED_PUMPKIN));
    public static final LuckyEvent EQUIP_CURSED_CHESTPLATE_EVENT = register("equip_cursed_chestplate_event", new EquipCurseEvent(Items.GOLDEN_CHESTPLATE));
    public static final LuckyEvent EQUIP_CURSED_LEGGINGS_EVENT = register("equip_cursed_leggings_event", new EquipCurseEvent(Items.GOLDEN_LEGGINGS));
    public static final LuckyEvent EQUIP_CURSED_BOOTS_EVENT = register("equip_cursed_boots_event", new EquipCurseEvent(Items.GOLDEN_BOOTS));
    public static final LuckyEvent DIAMOND_HORSE_ARMOR_EVENT = register(
            "diamond_horse_armor_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.DIAMOND_HORSE_ARMOR)
    );
    public static final LuckyEvent IRON_HORSE_ARMOR_EVENT = register(
            "iron_horse_armor_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.IRON_HORSE_ARMOR)
    );
    public static final LuckyEvent GOLDEN_ARMOR_EVENT = register(
            "golden_horse_armor_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.GOLDEN_HORSE_ARMOR)
    );
    public static final LuckyEvent LEATHER_ARMOR_EVENT = register(
            "leather_horse_armor_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.LEATHER_HORSE_ARMOR)
    );
    public static final LuckyEvent NETHERITE_HELMET_EVENT = register(
            "netherite_helmet_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.NETHERITE_HELMET)
    );
    public static final LuckyEvent NETHERITE_CHESTPLATE_EVENT = register(
            "netherite_chestplate_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.NETHERITE_CHESTPLATE)
    );
    public static final LuckyEvent NETHERITE_LEGGINGS_EVENT = register(
            "netherite_leggings_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.NETHERITE_LEGGINGS)
    );
    public static final LuckyEvent NETHERITE_BOOTS_EVENT = register(
            "netherite_boots_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.NETHERITE_BOOTS)
    );
    public static final LuckyEvent DIAMOND_HELMET_EVENT = register(
            "diamond_helmet_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.DIAMOND_HELMET)
    );
    public static final LuckyEvent DIAMOND_CHESTPLATE_EVENT = register(
            "diamond_chestplate_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.DIAMOND_CHESTPLATE)
    );
    public static final LuckyEvent DIAMOND_LEGGINGS_EVENT = register(
            "diamond_leggings_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.DIAMOND_LEGGINGS)
    );
    public static final LuckyEvent DIAMOND_BOOTS_EVENT = register(
            "diamond_boots_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.DIAMOND_BOOTS)
    );
    public static final LuckyEvent IRON_HELMET_EVENT = register(
            "iron_helmet_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.IRON_HELMET)
    );
    public static final LuckyEvent IRON_CHESTPLATE_EVENT = register(
            "iron_chestplate_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.IRON_CHESTPLATE)
    );
    public static final LuckyEvent IRON_LEGGINGS_EVENT = register(
            "iron_leggings_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.IRON_LEGGINGS)
    );
    public static final LuckyEvent IRON_BOOTS_EVENT = register(
            "iron_boots_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.IRON_BOOTS)
    );
    public static final LuckyEvent CHAINMAIL_HELMET_EVENT = register(
            "chainmail_helmet_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.CHAINMAIL_HELMET)
    );
    public static final LuckyEvent CHAINMAIL_CHESTPLATE_EVENT = register(
            "chainmail_chestplate_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.CHAINMAIL_CHESTPLATE)
    );
    public static final LuckyEvent CHAINMAIL_LEGGINGS_EVENT = register(
            "chainmail_leggings_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.CHAINMAIL_LEGGINGS)
    );
    public static final LuckyEvent CHAINMAIL_BOOTS_EVENT = register(
            "chainmail_boots_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.CHAINMAIL_BOOTS)
    );
    public static final LuckyEvent LEATHER_HELMET_EVENT = register(
            "leather_helmet_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.LEATHER_HELMET)
    );
    public static final LuckyEvent LEATHER_CHESTPLATE_EVENT = register(
            "leather_chestplate_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.LEATHER_CHESTPLATE)
    );
    public static final LuckyEvent LEATHER_LEGGINGS_EVENT = register(
            "leather_leggings_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.LEATHER_LEGGINGS)
    );
    public static final LuckyEvent LEATHER_BOOTS_EVENT = register(
            "leather_boots_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.LEATHER_BOOTS)
    );
    public static final LuckyEvent GOLDEN_HELMET_EVENT = register(
            "golden_helmet_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.GOLDEN_HELMET)
    );
    public static final LuckyEvent GOLDEN_CHESTPLATE_EVENT = register(
            "golden_chestplate_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.GOLDEN_CHESTPLATE)
    );
    public static final LuckyEvent GOLDEN_LEGGINGS_EVENT = register(
            "golden_leggings_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.GOLDEN_LEGGINGS)
    );
    public static final LuckyEvent GOLDEN_BOOTS_EVENT = register(
            "golden_boots_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.GOLDEN_BOOTS)
    );


    private static LuckyEvent register(String id, LuckyEvent event) {
        return Registry.register(LuckyWillyRegistries.LUCKY_EVENT, Identifier.of(LuckyWilly.MOD_ID, ID + "/" + id), event);
    }

    private static LuckyEvent register(String id, LuckyEventBuilder builder) {
        return register(id, builder.build());
    }

    private static LuckyEvent register(String id, Supplier<LuckyEvent> supplier) {
        return register(id, supplier.get());
    }

    public static void initialize() {}
}
