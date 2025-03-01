package net.ethernity.lucky.event.weapon;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.event.LuckyEventBuilder;
import net.ethernity.lucky.event.vanilla.ExplosionEvent;
import net.ethernity.lucky.event.vanilla.RandomChestLootEvent;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class WeaponLuckyEvents {
    public static final String ID = "weapon";

    public static final LuckyEvent RAW_FOOD_EVENT = register(
            "raw_food_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.ROTTEN_FLESH)
                    .dropStack(Items.MUTTON)
                    .dropStack(Items.CHICKEN)
                    .dropStack(Items.BEEF)
                    .dropStack(Items.PORKCHOP)
                    .dropStack(Items.RABBIT)
                    .dropStack(Items.SALMON)
                    .dropStack(Items.COD)
                    .dropStack(Items.SPIDER_EYE)
    );

    public static final LuckyEvent VEGETABLES_EVENT = register(
            "vegetables_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.CARROT.getDefaultStack().copyWithCount(5))
                    .dropStack(Items.SWEET_BERRIES.getDefaultStack().copyWithCount(4))
                    .dropStack(Items.BEETROOT.getDefaultStack().copyWithCount(10))
                    .dropStack(Items.POTATO.getDefaultStack().copyWithCount(4))
                    .dropStack(Items.MELON.getDefaultStack().copyWithCount(4))
    );

    public static final LuckyEvent CHARGED_CREEPER_EVENT = register(
            "charged_creeper_event", LuckyEventBuilder.create(-1)
                    .spawnMob(EntityType.CREEPER)
                    .spawnMob(EntityType.LIGHTNING_BOLT)
    );

    public static final LuckyEvent STONE_KIT_EVENT = register(
            "stone_kit_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.STONE_AXE)
                    .dropStack(Items.STONE_HOE)
                    .dropStack(Items.STONE_PICKAXE)
                    .dropStack(Items.STONE_SHOVEL)
                    .dropStack(Items.STONE_SWORD)
    );

    public static final LuckyEvent M4A1_EVENT = register(
            "m4a1_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("pointblank", "m4a1"))
                    .dropStack(Identifier.of("pointblank", "ammo556"), 15)
    );

    public static final LuckyEvent MK23_EVENT = register(
            "mk23_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("pointblank", "mk23"))
                    .dropStack(Identifier.of("pointblank", "ammo45acp"), 12)
    );

    public static final LuckyEvent AR57_EVENT = register(
            "ar57_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("pointblank", "ar57"))
                    .dropStack(Identifier.of("pointblank", "ammo57"), 30)
    );

    public static final LuckyEvent STAR15_EVENT = register(
            "star15_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("pointblank", "star15"))
                    .dropStack(Identifier.of("pointblank", "ammo556"), 10)
    );

    public static final LuckyEvent CREEPER_TOTEM_EVENT = register(
            "creeper_totem_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("moretotems", "explosive_totem_of_undying"))
    );
    public static final LuckyEvent ZOMBIE_TOTEM_EVENT = register(
            "zombie_totem_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("moretotems", "rotting_totem_of_undying"))
    );
    public static final LuckyEvent SKELETON_TOTEM_EVENT = register(
            "skeleton_totem_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("moretotems", "skeletal_totem_of_undying"))
    );
    public static final LuckyEvent NETHERITE_SWORD_EVENT = register(
            "netherite_sword_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.NETHERITE_SWORD)
    );
    public static final LuckyEvent DIAMOND_SWORD_EVENT = register(
            "diamond_sword_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.DIAMOND_SWORD)
    );
    public static final LuckyEvent IRON_SWORD_EVENT = register(
            "iron_sword_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.IRON_SWORD)
    );
    public static final LuckyEvent STONE_SWORD_EVENT = register(
            "stone_sword_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.STONE_SWORD)
    );
    public static final LuckyEvent WOODEN_SWORD_EVENT = register(
            "wooden_sword_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.WOODEN_SWORD)
    );
    public static final LuckyEvent GOLDEN_SWORD_EVENT = register(
            "golden_sword_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.GOLDEN_SWORD)
    );
    public static final LuckyEvent NETHERITE_CLUB_EVENT = register(
            "netherite_club_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("basicweapons", "netherite_club"))
    );
    public static final LuckyEvent DIAMOND_CLUB_EVENT = register(
            "diamond_club_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("basicweapons", "diamond_club"))
    );
    public static final LuckyEvent IRON_CLUB_EVENT = register(
            "iron_club_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("basicweapons", "iron_club"))
    );
    public static final LuckyEvent STONE_CLUB_EVENT = register(
            "stone_club_event", LuckyEventBuilder.create(-1)
                    .dropStack(Identifier.of("basicweapons", "stone_club"))
    );
    public static final LuckyEvent WOODEN_CLUB_EVENT = register(
            "wooden_club_event", LuckyEventBuilder.create(-1)
                    .dropStack(Identifier.of("basicweapons", "wooden_club"))
    );
    public static final LuckyEvent GOLDEN_CLUB_EVENT = register(
            "golden_club_event", LuckyEventBuilder.create(-1)
                    .dropStack(Identifier.of("basicweapons", "golden_club"))
    );
    public static final LuckyEvent NETHERITE_HAMMER_EVENT = register(
            "netherite_hammer_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("basicweapons", "netherite_hammer"))
    );
    public static final LuckyEvent DIAMOND_HAMMER_EVENT = register(
            "diamond_hammer_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("basicweapons", "diamond_hammer"))
    );
    public static final LuckyEvent IRON_HAMMER_EVENT = register(
            "iron_hammer_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("basicweapons", "iron_hammer"))
    );
    public static final LuckyEvent STONE_HAMMER_EVENT = register(
            "stone_hammer_event", LuckyEventBuilder.create(-1)
                    .dropStack(Identifier.of("basicweapons", "stone_hammer"))
    );
    public static final LuckyEvent WOODEN_HAMMER_EVENT = register(
            "wooden_hammer_event", LuckyEventBuilder.create(-1)
                    .dropStack(Identifier.of("basicweapons", "wooden_hammer"))
    );
    public static final LuckyEvent GOLDEN_HAMMER_EVENT = register(
            "golden_hammer_event", LuckyEventBuilder.create(-1)
                    .dropStack(Identifier.of("basicweapons", "golden_hammer"))
    );

    public static final LuckyEvent EXPLOSION_EVENT = register("explosion_event", ExplosionEvent::new);
    public static final LuckyEvent RANDOM_CHEST_LOOT_EVENT = register("random_chest_loot_event", RandomChestLootEvent::new);

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
