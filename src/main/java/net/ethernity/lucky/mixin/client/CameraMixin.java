package net.ethernity.lucky.mixin.client;

import net.ethernity.lucky.network.LuckyWillyNetwork;
import net.minecraft.client.render.*;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public abstract class CameraMixin {
    @Inject(method = "isThirdPerson", at = @At("HEAD"), cancellable = true)
    private void isThirdPerson(CallbackInfoReturnable<Boolean> cir) {
        if(LuckyWillyNetwork.getPlayerEntity() != null)
            cir.setReturnValue(true);
    }

    @Inject(method = "getFocusedEntity", at = @At("HEAD"), cancellable = true)
    private void getFocusedEntity(CallbackInfoReturnable<Entity> cir) {
        if(LuckyWillyNetwork.getPlayerEntity() != null)
            cir.setReturnValue(LuckyWillyNetwork.getPlayerEntity());
    }
}