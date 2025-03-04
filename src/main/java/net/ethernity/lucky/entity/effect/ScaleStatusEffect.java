package net.ethernity.lucky.entity.effect;

import net.ethernity.lucky.LuckyWilly;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

public class ScaleStatusEffect extends StatusEffect {
    protected ScaleStatusEffect(int color, float amount, String id) {
        super(StatusEffectCategory.NEUTRAL, color);
        Identifier identifier = Identifier.of(LuckyWilly.MOD_ID, id);
        this.addAttributeModifier(
                EntityAttributes.GENERIC_SCALE, identifier, amount, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        ).addAttributeModifier(
                EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, identifier, amount, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        ).addAttributeModifier(
                EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE, identifier, amount, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }
}
