package net.ethernity.lucky.event.weapon;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.event.LuckyEventBuilder;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class WeaponLuckyEvents {
    public static final String ID = "weapon";

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
    public static final LuckyEvent BOW_EVENT = register(
            "bow_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.BOW)
                    .dropStack(Items.ARROW.getDefaultStack().copyWithCount(16))
    );

    public static final LuckyEvent LASER_BLADE_EVENT = register(
            "laser_blade_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("tolaserblade", "laser_blade"))
    );
    public static final LuckyEvent LASER_BLADE_FP_EVENT = register(
            "laser_blade_fp_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("tolaserblade", "laser_blade_fp"))
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

    public static void initialize() {
    }
}
