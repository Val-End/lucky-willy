package net.ethernity.lucky.event;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.ethernity.lucky.event.armor.ArmorLuckyEvents;
import net.ethernity.lucky.event.global.*;
import net.ethernity.lucky.event.vanilla.VanillaLuckyEvents;
import net.ethernity.lucky.event.weapon.WeaponLuckyEvents;
import net.ethernity.lucky.event.willy.WillyLuckyEvents;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class LuckyEvents {
    public static final String ID = "global";

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

    public static final LuckyEvent COOKED_FOOD_EVENT = register(
            "cooked_food_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.COOKED_BEEF.getDefaultStack().copyWithCount(6))
                    .dropStack(Items.COOKED_CHICKEN.getDefaultStack().copyWithCount(8))
                    .dropStack(Items.COOKED_PORKCHOP.getDefaultStack().copyWithCount(2))
    );

    public static final LuckyEvent GOLDEN_APPLE_EVENT = register(
            "golden_apple_food_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.ENCHANTED_GOLDEN_APPLE)
                    .dropStack(Items.GOLDEN_APPLE.getDefaultStack().copyWithCount(2))
    );

    public static final LuckyEvent CHARGED_CREEPER_EVENT = register(
            "charged_creeper_event", LuckyEventBuilder.create(-1)
                    .spawnMob(EntityType.CREEPER)
                    .spawnMob(EntityType.LIGHTNING_BOLT)
    );
    public static final LuckyEvent BUCKETS_EVENT = register(
            "buckets_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.WATER_BUCKET)
                    .dropStack(Items.LAVA_BUCKET)
                    .dropStack(Items.POWDER_SNOW_BUCKET)
    );
    public static final LuckyEvent CREEPER_FALL_EVENT = register("creeper_fall_event", CreeperFallEvent::new);
    public static final LuckyEvent EXPLOSION_EVENT = register("explosion_event", ExplosionEvent::new);
    public static final LuckyEvent SWITCH_CAMERA_EVENT = register("switch_camera_event", SwitchCameraEvent::new);
    public static final LuckyEvent MINI_SPIDERS_EVENT = register("mini_spiders_event", MiniSpidersEvent::new);
    public static final LuckyEvent GIANTS_BATTLE_EVENT = register("giants_battle_event", GiantsBattleEvent::new);

    public static final LuckyEvent TINY_SCALE_EVENT = register(
            "tiny_scale_event", LuckyEventBuilder.create(0)
                    .applyEffect(LuckyWillyEffects.TINY, 600, 8)
    );
    public static final LuckyEvent GIANT_SCALE_EVENT = register(
            "giant_scale_event", LuckyEventBuilder.create(0)
                    .applyEffect(LuckyWillyEffects.GIANT, 600, 8)
    );
    public static final LuckyEvent INVERT_EVENT = register(
            "invert_event", LuckyEventBuilder.create(-1)
                    .applyEffect(LuckyWillyEffects.INVERT, 600)
    );
    public static final LuckyEvent GOOD_ARMOR_STAND_EVENT = register(
            "good_armor_stand_event", new ArmorStandEvent(1)
    );

    public static final LuckyEvent BAD_ARMOR_STAND_EVENT = register(
            "bad_armor_stand_event", new ArmorStandEvent(-1)
    );

    public static final LuckyEvent PAINTING_EVENT = register(
            "painting_event", LuckyEventBuilder.create(-1)
                    .spawnMob(LuckyWillyEntities.PAINTING)
    );

    public static final LuckyEvent GOOD_PAINTING_EVENT = register(
            "good_painting_event", LuckyEventBuilder.create(-1)
                    .spawnMob(LuckyWillyEntities.GOOD_PAINTING)
    );

    public static Identifier getId(LuckyEvent type) {
        return LuckyWillyRegistries.LUCKY_EVENT.getId(type);
    }

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
        VanillaLuckyEvents.initialize();
        WeaponLuckyEvents.initialize();
        ArmorLuckyEvents.initialize();
        WillyLuckyEvents.initialize();
    }
}
