package net.ethernity.lucky.event.willy;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class WillyLuckyEvents {
    public static final String ID = "willy";

    public static final LuckyEvent TROTUMAN_EVENT = register(
            "trotuman_event", new TrotumanEvent()
    );

    private static LuckyEvent register(String id, LuckyEvent event) {
        return Registry.register(LuckyWillyRegistries.LUCKY_EVENT, Identifier.of(LuckyWilly.MOD_ID, ID + "/" + id), event);
    }

    public static void initialize() {}
}
