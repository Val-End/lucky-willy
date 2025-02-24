package net.ethernity.lucky.event;

import net.ethernity.lucky.event.armor.ArmorLuckyEvents;
import net.ethernity.lucky.event.vanilla.VanillaLuckyEvents;
import net.ethernity.lucky.event.weapon.WeaponLuckyEvents;
import net.ethernity.lucky.event.willy.WillyLuckyEvents;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.minecraft.util.Identifier;

public class LuckyEvents {
    public static Identifier getId(LuckyEvent type) {
        return LuckyWillyRegistries.LUCKY_EVENT.getId(type);
    }

    public static void initialize() {
        VanillaLuckyEvents.initialize();
        WeaponLuckyEvents.initialize();
        ArmorLuckyEvents.initialize();
        WillyLuckyEvents.initialize();
    }
}
