package net.ethernity.lucky.event.willy;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.event.LuckyEventBuilder;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class WillyLuckyEvents {
    public static final String ID = "willy";

    public static final LuckyEvent STAFF_SOUND_EVENT = register(
            "staff_sound_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("gobber2", "gobber2_staff_sound"))
    );
    public static final LuckyEvent NETHERITE_HOE_EVENT = register(
            "netherite_hoe_event", LuckyEventBuilder.create(-1)
                    .dropStack(Items.NETHERITE_HOE)
    );
    public static final LuckyEvent BUCKETS_EVENT = register(
            "buckets_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.WATER_BUCKET)
                    .dropStack(Items.LAVA_BUCKET)
                    .dropStack(Items.POWDER_SNOW_BUCKET)
    );
    public static final LuckyEvent MINIGUN_EVENT = register(
            "minigun_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("pointblank", "m134minigun"))
                    .dropStack(Identifier.of("pointblank", "ammo556"), 50)
    );
    public static final LuckyEvent GRENADE_LAUNCHER_EVENT = register(
            "grenade_launcher_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("pointblank", "m32mgl"))
                    .dropStack(Identifier.of("pointblank", "grenade40mm"), 6)
    );
    public static final LuckyEvent ROCKET_LAUNCHER_EVENT = register(
            "rocket_launcher_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("pointblank", "smaw"))
                    .dropStack(Identifier.of("pointblank", "smaw_rocket"))
    );
    public static final LuckyEvent GRENADE_EVENT = register(
            "grenade_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("pointblank", "grenade"), 6)
    );
    public static final LuckyEvent SCREAMER_EVENT = register("screamer_event", ScreamerEvent::new);
    public static final LuckyEvent RESONARIUM_HELMET_EVENT = register(
            "resonarium_helmet_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("deeperdarker", "resonarium_helmet"))
    );
    public static final LuckyEvent RESONARIUM_CHESTPLATE_EVENT = register(
            "resonarium_chestplate_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("deeperdarker", "resonarium_chestplate"))
    );
    public static final LuckyEvent RESONARIUM_LEGGINGS_EVENT = register(
            "resonarium_leggings_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("deeperdarker", "resonarium_leggings"))
    );
    public static final LuckyEvent RESONARIUM_BOOTS_EVENT = register(
            "resonarium_boots_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("deeperdarker", "resonarium_boots"))
    );
    public static final LuckyEvent WARDEN_HELMET_EVENT = register(
            "warden_helmet_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("deeperdarker", "warden_helmet"))
    );
    public static final LuckyEvent WARDEN_CHESTPLATE_EVENT = register(
            "warden_chestplate_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("deeperdarker", "warden_chestplate"))
    );
    public static final LuckyEvent WARDEN_LEGGINGS_EVENT = register(
            "warden_leggings_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("deeperdarker", "warden_leggings"))
    );
    public static final LuckyEvent WARDEN_BOOTS_EVENT = register(
            "warden_boots_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("deeperdarker", "warden_boots"))
    );
    public static final LuckyEvent DRAGON_HELMET_EVENT = register(
            "dragon_helmet_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("dragonloot", "dragon_helmet"))
    );
    public static final LuckyEvent DRAGON_CHESTPLATE_EVENT = register(
            "dragon_chestplate_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("dragonloot", "dragon_chestplate"))
    );
    public static final LuckyEvent DRAGON_LEGGINGS_EVENT = register(
            "dragon_leggings_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("dragonloot", "dragon_leggings"))
    );
    public static final LuckyEvent DRAGON_BOOTS_EVENT = register(
            "dragon_boots_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("dragonloot", "dragon_boots"))
    );
    public static final LuckyEvent RESONARIUM_SWORD_EVENT = register(
            "resonarium_sword_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("deeperdarker", "resonarium_sword"))
    );
    public static final LuckyEvent WARDEN_SWORD_EVENT = register(
            "warden_sword_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("deeperdarker", "warden_sword"))
    );
    public static final LuckyEvent DRAGON_SWORD_EVENT = register(
            "dragon_sword_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("dragonloot", "dragon_sword"))
    );
    public static final LuckyEvent HALF_HEART_EVENT = register(
            "half_heart_event", LuckyEventBuilder.create(-1)
                    .applyEffect(LuckyWillyEffects.HALF_HEART, 20)
    );

    public static final LuckyEvent ZOMBIE_EVENT = register("zombie_event", new MobWithHeadEvent<>(EntityType.ZOMBIE));
    public static final LuckyEvent SKELETON_EVENT = register("skeleton_event", new MobWithHeadEvent<>(EntityType.SKELETON));
    public static final LuckyEvent SPIDER_EVENT = register("spider_event", new MobWithHeadEvent<>(EntityType.SPIDER));
    public static final LuckyEvent TROTUMAN_EVENT = register("trotuman_event", TrotumanEvent::new);
    public static final LuckyEvent EQUIP_PUMPKIN_EVENT = register("equip_pumpkin_event", new EquipCurseEvent(Items.CARVED_PUMPKIN));
    public static final LuckyEvent EQUIP_CURSED_CHESTPLATE_EVENT = register("equip_cursed_chestplate_event", new EquipCurseEvent(Items.GOLDEN_CHESTPLATE));
    public static final LuckyEvent EQUIP_CURSED_LEGGINGS_EVENT = register("equip_cursed_leggings_event", new EquipCurseEvent(Items.GOLDEN_LEGGINGS));
    public static final LuckyEvent EQUIP_CURSED_BOOTS_EVENT = register("equip_cursed_boots_event", new EquipCurseEvent(Items.GOLDEN_BOOTS));

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
