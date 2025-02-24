package net.ethernity.lucky.event.weapon;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.event.LuckyEventPreset;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class WeaponLuckyEvents {
    public static final String ID = "weapon";

    public static final LuckyEvent SHOTGUN_EVENT = register(
            "shotgun_event", LuckyEventPreset.dropStack(1, Identifier.of("pointblank", "m870"))
    );

    public static final LuckyEvent SMASH_COD_EVENT = register(
            "smash_cod_event", new SmashCodEvent()
    );

    private static LuckyEvent register(String id, LuckyEvent event) {
        return Registry.register(LuckyWillyRegistries.LUCKY_EVENT, Identifier.of(LuckyWilly.MOD_ID, ID + "/" + id), event);
    }

    public static void initialize() {}
}
