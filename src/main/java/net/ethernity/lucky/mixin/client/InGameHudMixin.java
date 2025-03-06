package net.ethernity.lucky.mixin.client;

import net.ethernity.lucky.client.network.LuckyWillyClientNetwork;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Inject(method = "getCameraPlayer", at = @At("HEAD"), cancellable = true)
    private void getCameraPlayer(CallbackInfoReturnable<PlayerEntity> cir) {
        if (LuckyWillyClientNetwork.getPlayerEntity() != null)
            cir.setReturnValue(LuckyWillyClientNetwork.getPlayerEntity());
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void renderCrosshair(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (LuckyWillyClientNetwork.getPlayerEntity() != null)
            ci.cancel();
    }
}