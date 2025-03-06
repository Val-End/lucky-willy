package net.ethernity.lucky.mixin.client;

import net.ethernity.lucky.client.network.LuckyWillyClientNetwork;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerMixin {
    @Shadow
    @Final
    protected MinecraftClient client;

    @Inject(method = "isCamera", at = @At("HEAD"), cancellable = true)
    private void isCamera(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

    @Inject(method = "tickNewAi", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        boolean isAlive = client.getCameraEntity().isAlive();

        if (!isAlive)
            client.setCameraEntity(LuckyWillyClientNetwork.getPlayerEntity());
        if (client.getCameraEntity().equals(LuckyWillyClientNetwork.getPlayerEntity()))
            LuckyWillyClientNetwork.clearPlayerEntity();
    }
}
