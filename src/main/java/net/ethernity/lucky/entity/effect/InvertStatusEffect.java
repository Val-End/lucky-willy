package net.ethernity.lucky.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class InvertStatusEffect extends StatusEffect {
    protected InvertStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x2f2f30);
    }
}
