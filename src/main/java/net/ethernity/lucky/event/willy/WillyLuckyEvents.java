package net.ethernity.lucky.event.willy;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.event.LuckyEventPreset;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.ethernity.lucky.world.gen.LuckyWillyFeature;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class WillyLuckyEvents {
    public static final String ID = "willy";

    public static final LuckyEvent SCALE_EVENT = register(
            "scale_event", new ScaleEvent()
    );
    public static final LuckyEvent HALF_HEART_EVENT = register(
            "half_heart_event", new HalfHeartEvent()
    );
    public static final LuckyEvent TROTUMAN_EVENT = register(
            "trotuman_event", new TrotumanEvent()
    );
    public static final LuckyEvent ZOMBIE_EVENT = register(
            "zombie_event", new MobWithHeadEvent<>(EntityType.ZOMBIE)
    );
    public static final LuckyEvent SKELETON_EVENT = register(
            "skeleton_event", new MobWithHeadEvent<>(EntityType.SKELETON)
    );
    public static final LuckyEvent SPIDER_EVENT = register(
            "spider_event", new MobWithHeadEvent<>(EntityType.SPIDER)
    );
    public static final LuckyEvent STAFF_SOUND_EVENT = register(
            "staff_sound_event", LuckyEventPreset.dropStack(1, Identifier.of("gobber2", "gobber2_staff_sound"))
    );
    public static final LuckyEvent CREEPER_FALL_EVENT = register(
            "creeper_fall_event", new CreeperFallEvent()
    );
    public static final LuckyEvent INVERT_EVENT = register(
            "invert_event", new InvertEvent()
    );

    public static final LuckyEvent SCREAMER = register(
            "screamer_event", new ScreamerEvent()
    );

    private static LuckyEvent register(String id, LuckyEvent event) {
        return Registry.register(LuckyWillyRegistries.LUCKY_EVENT, Identifier.of(LuckyWilly.MOD_ID, ID + "/" + id), event);
    }

    public static void initialize() {}
}
