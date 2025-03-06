package net.ethernity.lucky.client.network;

import net.ethernity.lucky.client.renderer.LuckyWillyScreenRenderer;
import net.ethernity.lucky.client.shader.ZawarudoShader;
import net.ethernity.lucky.network.packet.s2c.ScreamerPayload;
import net.ethernity.lucky.network.packet.s2c.SwitchCameraPayload;
import net.ethernity.lucky.network.packet.s2c.ZawarudoPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class LuckyWillyClientNetwork {
    public static PlayerEntity playerEntity = null;

    public static void initialize() {
        ClientPlayNetworking.registerGlobalReceiver(ScreamerPayload.ID, (payload, context) -> {
            context.client().execute(LuckyWillyScreenRenderer::startScreamer);
        });

        ClientPlayNetworking.registerGlobalReceiver(ZawarudoPayload.ID, ZawarudoShader::handle);

        ClientPlayNetworking.registerGlobalReceiver(SwitchCameraPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                LivingEntity entity = (LivingEntity) context.player().getWorld().getEntityById(payload.target());
                if (entity != null && !MinecraftClient.getInstance().getCameraEntity().equals(entity)) {
                    playerEntity = context.player();
                    MinecraftClient.getInstance().setCameraEntity(entity);
                }
            });
        });
    }

    public static void clearPlayerEntity() {
        playerEntity = null;
    }

    public static PlayerEntity getPlayerEntity() {
        return playerEntity;
    }
}
