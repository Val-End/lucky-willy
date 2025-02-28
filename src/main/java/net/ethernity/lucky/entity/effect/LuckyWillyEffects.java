package net.ethernity.lucky.entity.effect;

import net.ethernity.lucky.LuckyWilly;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class LuckyWillyEffects {
    public static final RegistryEntry<StatusEffect> TINY = register("tiny", new ScaleStatusEffect(0x83a6ff, -0.1f, "effect.tiny"));
    public static final RegistryEntry<StatusEffect> GIANT = register("giant", new ScaleStatusEffect(0xff463f, 0.5f, "effect.giant"));
    public static final RegistryEntry<StatusEffect> INVERT = register("invert", new InvertStatusEffect(0xff463));

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(LuckyWilly.MOD_ID, id), statusEffect);
    }

    public static void initialize() {}
}
