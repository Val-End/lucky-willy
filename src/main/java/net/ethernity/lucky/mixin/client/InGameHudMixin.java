package net.ethernity.lucky.mixin.client;

import net.ethernity.lucky.network.LuckyWillyNetwork;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
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
        if(LuckyWillyNetwork.getPlayerEntity() != null)
            cir.setReturnValue(LuckyWillyNetwork.getPlayerEntity());
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void renderCrosshair(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if(LuckyWillyNetwork.getPlayerEntity() != null)
            ci.cancel();
    }
}