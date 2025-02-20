package net.ethernity.lucky.registry;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.blocks.events.LuckyEvent;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class LuckyWillyRegistryKeys {
    public static final RegistryKey<Registry<LuckyEvent>> LUCKY_EVENT = of("block");

    private static <T> RegistryKey<Registry<T>> of(String id) {
        return RegistryKey.ofRegistry(Identifier.of(LuckyWilly.MOD_ID, id));
    }
}
