package net.ethernity.lucky.blocks.events;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.ethernity.lucky.world.gen.feature.LuckyWillyFeature;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LuckyEvents {
    public static final LuckyEvent TROTUMAN_EVENT = register("trotuman_event", new TrotumanEvent());
    public static final LuckyEvent ANVIL_TRAP_EVENT = register("anvil_trap_event", LuckyEvent.createPlaceFeaturePlayer(LuckyWillyFeature.ANVIL_TRAP_KEY, -1.0F));
    public static final LuckyEvent LAVA_TRAP_EVENT = register("lava_trap_event", LuckyEvent.createPlaceFeaturePlayer(LuckyWillyFeature.LAVA_TRAP_KEY, -1.0F));

    public static void initialize() {}

    private static LuckyEvent register(String id, LuckyEvent event) {
        return Registry.register(LuckyWillyRegistries.LUCKY_EVENT, Identifier.of(LuckyWilly.MOD_ID, id), event);
    }
}
