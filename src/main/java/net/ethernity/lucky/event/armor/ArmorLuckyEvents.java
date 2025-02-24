package net.ethernity.lucky.event.armor;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ArmorLuckyEvents {
    public static final String ID = "armor";

    private static LuckyEvent register(String id, LuckyEvent event) {
        return Registry.register(LuckyWillyRegistries.LUCKY_EVENT, Identifier.of(LuckyWilly.MOD_ID, ID + "/" + id), event);
    }

    public static void initialize() {}
}
