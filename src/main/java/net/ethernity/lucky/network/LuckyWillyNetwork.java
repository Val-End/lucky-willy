package net.ethernity.lucky.network;

import net.ethernity.lucky.client.renderer.ScreenRenderer;
import net.ethernity.lucky.client.shader.ZawarudoShader;
import net.ethernity.lucky.network.packet.s2c.ScreamerPayload;
import net.ethernity.lucky.network.packet.s2c.SwitchCameraPayload;
import net.ethernity.lucky.network.packet.s2c.ZawarudoPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;

public class LuckyWillyNetwork {
    private static PlayerEntity playerEntity = null;

    public static void initialize() {
        PayloadTypeRegistry.playS2C().register(ScreamerPayload.ID, ScreamerPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ZawarudoPayload.ID, ZawarudoPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(SwitchCameraPayload.ID, SwitchCameraPayload.CODEC);
    }

    public static void initializeClient() {
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

    public static void clearPlayerEntity() {
        LuckyWillyNetwork.playerEntity = null;
    }

    public static PlayerEntity getPlayerEntity() {
        return LuckyWillyNetwork.playerEntity;
    }

    public static void s2c(ServerPlayerEntity player, CustomPayload payload) {
        ServerPlayNetworking.send(player, payload);
    }
}
