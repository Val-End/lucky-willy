package net.ethernity.lucky.network;

import net.ethernity.lucky.client.renderer.ScreenRenderer;
import net.ethernity.lucky.client.shader.ZawarudoShader;
import net.ethernity.lucky.network.packet.s2c.ScreamerPayload;
import net.ethernity.lucky.network.packet.s2c.ZawarudoPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;

public class LuckyWillyNetwork {

    public static void initialize() {
        PayloadTypeRegistry.playS2C().register(ScreamerPayload.ID, ScreamerPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ZawarudoPayload.ID, ZawarudoPayload.CODEC);
    }

    public static void initializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(ScreamerPayload.ID, (payload, context) -> {
            context.client().execute(ScreenRenderer::startScreamer);
        });

        ClientPlayNetworking.registerGlobalReceiver(ZawarudoPayload.ID, ZawarudoShader::handle);
    }

    public static void s2c(ServerPlayerEntity player, CustomPayload payload) {
        ServerPlayNetworking.send(player, payload);
    }
}
