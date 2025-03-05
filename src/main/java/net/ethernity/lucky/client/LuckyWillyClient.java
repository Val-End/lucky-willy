package net.ethernity.lucky.client;

import net.ethernity.lucky.client.renderer.LuckyWillyRenderer;
import net.ethernity.lucky.client.renderer.ScreenRenderer;
import net.ethernity.lucky.client.shader.LuckyWillyShader;
import net.ethernity.lucky.client.shader.ZawarudoShader;
import net.ethernity.lucky.network.LuckyWillyNetwork;
import net.ethernity.lucky.network.packet.s2c.ScreamerPayload;
import net.ethernity.lucky.network.packet.s2c.SwitchCameraPayload;
import net.ethernity.lucky.network.packet.s2c.ZawarudoPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class LuckyWillyClient implements ClientModInitializer {
    public static PlayerEntity playerEntity = null;

    public static void clearPlayerEntity() {
        playerEntity = null;
    }

    public static PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    @Override
    public void onInitializeClient() {
        LuckyWillyRenderer.initialize();
        LuckyWillyClient.initializeNetwork();
        LuckyWillyShader.initialize();

        ScreenRenderer.init();
    }

    public static void initializeNetwork() {
        ClientPlayNetworking.registerGlobalReceiver(ScreamerPayload.ID, (payload, context) -> {
            context.client().execute(ScreenRenderer::startScreamer);
        });

        ClientPlayNetworking.registerGlobalReceiver(ZawarudoPayload.ID, ZawarudoShader::handle);

        ClientPlayNetworking.registerGlobalReceiver(SwitchCameraPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                LivingEntity entity = (LivingEntity) context.player().getWorld().getEntityById(payload.target());
                if(entity != null && !MinecraftClient.getInstance().getCameraEntity().equals(entity)) {
                    playerEntity = context.player();
                    MinecraftClient.getInstance().setCameraEntity(entity);
                }
            });
        });
    }
}