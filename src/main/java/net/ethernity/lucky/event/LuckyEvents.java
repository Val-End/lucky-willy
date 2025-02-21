package net.ethernity.lucky.event;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.event.vanilla.ChargedCreeperBlockEvent;
import net.ethernity.lucky.event.vanilla.DiamondBlockEvent;
import net.ethernity.lucky.event.vanilla.ExplosionEvent;
import net.ethernity.lucky.event.willy.TrotumanEvent;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.ethernity.lucky.world.gen.LuckyWillyFeature;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LuckyEvents {
    public static final LuckyEvent TROTUMAN_EVENT = register(
            "trotuman_event", new TrotumanEvent()
    );
    public static final LuckyEvent CHARGED_CREEPER_EVENT = register(
            "charged_creeper_event", new ChargedCreeperBlockEvent()
    );
    public static final LuckyEvent DIAMOND_BLOCK_EVENT = register(
            "diamond_block_event", new DiamondBlockEvent()
    );
    public static final LuckyEvent ANVIL_TRAP_EVENT = register(
            "anvil_trap_event", LuckyEventPreset.placeFeature(-1 , LuckyWillyFeature.ANVIL_TRAP_KEY, true)
    );
    public static final LuckyEvent LAVA_TRAP_EVENT = register(
            "lava_trap_event", LuckyEventPreset.placeFeature(-1 , LuckyWillyFeature.LAVA_TRAP_KEY, true)
    );
    public static final LuckyEvent LUCKY_PYRAMID_EVENT = register(
            "lucky_pyramid_event", LuckyEventPreset.placeFeature(0 , LuckyWillyFeature.LUCKY_PYRAMID_KEY, false)
    );
    public static final LuckyEvent EXPLOSION_EVENT = register(
            "explosion_event", new ExplosionEvent()
    );

    public static Identifier getId(LuckyEvent type) {
        return LuckyWillyRegistries.LUCKY_EVENT.getId(type);
    }

    public static void initialize() {}

    private static LuckyEvent register(String id, LuckyEvent event) {
        return Registry.register(LuckyWillyRegistries.LUCKY_EVENT, Identifier.of(LuckyWilly.MOD_ID, id), event);
    }
}
