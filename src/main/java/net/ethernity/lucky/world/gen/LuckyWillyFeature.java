package net.ethernity.lucky.world.gen;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.world.gen.feature.AnvilTrapFeature;
import net.ethernity.lucky.world.gen.feature.CreeperFallFeature;
import net.ethernity.lucky.world.gen.feature.FourLuckyPyramidFeature;
import net.ethernity.lucky.world.gen.feature.LavaTrapFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class LuckyWillyFeature {
    public static final RegistryKey<Feature<?>> ANVIL_TRAP_KEY = register(
            "anvil_trap", new AnvilTrapFeature(DefaultFeatureConfig.CODEC)
    );
    public static final RegistryKey<Feature<?>> LAVA_TRAP_KEY = register(
            "lava_trap", new LavaTrapFeature(DefaultFeatureConfig.CODEC)
    );
    public static final RegistryKey<Feature<?>> FOUR_LUCKY_PYRAMID_KEY = register(
            "four_lucky_pyramid", new FourLuckyPyramidFeature(DefaultFeatureConfig.CODEC)
    );
    public static final RegistryKey<Feature<?>> CREEPER_FALL_KEY = register(
            "creeper_fall", new CreeperFallFeature(DefaultFeatureConfig.CODEC)
    );

    private static RegistryKey<Feature<?>> register(String id, Feature<?> feature) {
        RegistryKey<Feature<?>> KEY = RegistryKey.of(RegistryKeys.FEATURE, Identifier.of(LuckyWilly.MOD_ID, id));
        Registry.register(Registries.FEATURE, KEY.getValue(), feature);
        return KEY;
    }

    public static void initialize() {
    }
}
