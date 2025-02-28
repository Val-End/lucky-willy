package net.ethernity.lucky.event.willy;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.event.LuckyEventBuilder;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class WillyLuckyEvents {
    public static final String ID = "willy";

    public static final LuckyEvent TINY_SCALE_EVENT = register(
            "tiny_scale_event", LuckyEventBuilder.create(0)
                    .applyEffect(LuckyWillyEffects.TINY, 600, 3)
    );
    public static final LuckyEvent GIANT_SCALE_EVENT = register(
            "giant_scale_event", LuckyEventBuilder.create(0)
                    .applyEffect(LuckyWillyEffects.GIANT, 600, 3)
    );
    public static final LuckyEvent STAFF_SOUND_EVENT = register(
            "staff_sound_event", LuckyEventBuilder.create(1)
                    .dropStack(Identifier.of("gobber2", "gobber2_staff_sound"))
    );
    public static final LuckyEvent INVERT_EVENT = register(
            "invert_event", LuckyEventBuilder.create(0)
                    .applyEffect(LuckyWillyEffects.INVERT, 600)
    );

    public static final LuckyEvent ZOMBIE_EVENT = register("zombie_event", new MobWithHeadEvent<>(EntityType.ZOMBIE));
    public static final LuckyEvent SKELETON_EVENT = register("skeleton_event", new MobWithHeadEvent<>(EntityType.SKELETON));
    public static final LuckyEvent SPIDER_EVENT = register("spider_event", new MobWithHeadEvent<>(EntityType.SPIDER));
    public static final LuckyEvent HALF_HEART_EVENT = register("half_heart_event", HalfHeartEvent::new);
    public static final LuckyEvent TROTUMAN_EVENT = register("trotuman_event", TrotumanEvent::new);
    public static final LuckyEvent CREEPER_FALL_EVENT = register("creeper_fall_event", CreeperFallEvent::new);

    public static final LuckyEvent SCREAMER = register(
            "screamer_event", new ScreamerEvent()
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
