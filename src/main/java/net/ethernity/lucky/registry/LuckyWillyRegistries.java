package net.ethernity.lucky.registry;

import net.ethernity.lucky.event.LuckyEvent;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.*;

public class LuckyWillyRegistries {
    public static final Registry<LuckyEvent> LUCKY_EVENT =
            FabricRegistryBuilder.createSimple(LuckyWillyRegistryKeys.LUCKY_EVENT)
                    .buildAndRegister();

    public static void initialize() {}
}
