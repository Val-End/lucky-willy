package net.ethernity.lucky.world.gen;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.world.gen.feature.AnvilTrapFeature;
import net.ethernity.lucky.world.gen.feature.LavaTrapFeature;
import net.ethernity.lucky.world.gen.feature.LuckyPyramidFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

public class LuckyWillyFeature {
    public static final Feature<DefaultFeatureConfig> ANVIL_TRAP = new AnvilTrapFeature(DefaultFeatureConfig.CODEC);
    public static final RegistryKey<Feature<?>> ANVIL_TRAP_KEY = RegistryKey.of(RegistryKeys.FEATURE, Identifier.of(LuckyWilly.MOD_ID, "anvil_trap"));
    public static final Feature<DefaultFeatureConfig> LAVA_TRAP = new LavaTrapFeature(DefaultFeatureConfig.CODEC);
    public static final RegistryKey<Feature<?>> LAVA_TRAP_KEY = RegistryKey.of(RegistryKeys.FEATURE, Identifier.of(LuckyWilly.MOD_ID, "lava_trap"));
    public static final Feature<DefaultFeatureConfig> LUCKY_PYRAMID = new LuckyPyramidFeature(DefaultFeatureConfig.CODEC);
    public static final RegistryKey<Feature<?>> LUCKY_PYRAMID_KEY = RegistryKey.of(RegistryKeys.FEATURE, Identifier.of(LuckyWilly.MOD_ID, "lucky_pyramid"));

    public static void initialize() {
        Registry.register(Registries.FEATURE, ANVIL_TRAP_KEY.getValue(), ANVIL_TRAP);
        Registry.register(Registries.FEATURE, LAVA_TRAP_KEY.getValue(), LAVA_TRAP);
        Registry.register(Registries.FEATURE, LUCKY_PYRAMID_KEY.getValue(), LUCKY_PYRAMID);
    }
}
