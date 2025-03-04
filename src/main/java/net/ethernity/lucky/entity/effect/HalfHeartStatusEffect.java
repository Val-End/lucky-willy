package net.ethernity.lucky.entity.effect;

import net.ethernity.lucky.entity.damage.LuckyWillyDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class HalfHeartStatusEffect extends StatusEffect {
    protected HalfHeartStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x7e0400);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        float damage = 1.0f;
        if (entity.getHealth() - damage > 1)
            entity.damage(LuckyWillyDamageTypes.of(entity.getWorld(), LuckyWillyDamageTypes.LUCKY_DAMAGE_TYPE), 1f);

        return super.applyUpdateEffect(entity, amplifier);
    }
}
