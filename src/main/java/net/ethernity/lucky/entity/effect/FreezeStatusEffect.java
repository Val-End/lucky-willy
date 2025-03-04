package net.ethernity.lucky.entity.effect;

import net.ethernity.lucky.LuckyWilly;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

public class FreezeStatusEffect extends StatusEffect {
    protected FreezeStatusEffect(String id) {
        super(StatusEffectCategory.HARMFUL, 0x65d6ff);
        Identifier identifier = Identifier.of(LuckyWilly.MOD_ID, id);
        this.addAttributeModifier(
                EntityAttributes.GENERIC_MOVEMENT_SPEED, identifier, -0.11, EntityAttributeModifier.Operation.ADD_VALUE
        ).addAttributeModifier(
                EntityAttributes.GENERIC_JUMP_STRENGTH, identifier, -0.41999998688697815, EntityAttributeModifier.Operation.ADD_VALUE
        );
    }
}
