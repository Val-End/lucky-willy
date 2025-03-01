package net.ethernity.lucky.entity.effect;

import net.ethernity.lucky.client.renderer.ScreenRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class ScreamerStatusEffect extends StatusEffect {
    protected ScreamerStatusEffect(int color) {
        super(StatusEffectCategory.NEUTRAL, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        super.onApplied(entity, amplifier);

        if (FabricLoader.getInstance().getEnvironmentType() != EnvType.CLIENT)
            return;
        if (entity.equals(MinecraftClient.getInstance().player))
            ScreenRenderer.startScreamer();
    }
}
