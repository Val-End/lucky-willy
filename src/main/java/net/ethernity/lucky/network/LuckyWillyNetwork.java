package net.ethernity.lucky.network;

import net.ethernity.lucky.network.packet.s2c.ScreamerPayload;
import net.ethernity.lucky.network.packet.s2c.SwitchCameraPayload;
import net.ethernity.lucky.network.packet.s2c.ZawarudoPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;

public class LuckyWillyNetwork {

    public static void initialize() {
        PayloadTypeRegistry.playS2C().register(ScreamerPayload.ID, ScreamerPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ZawarudoPayload.ID, ZawarudoPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(SwitchCameraPayload.ID, SwitchCameraPayload.CODEC);
    }

    public static void s2c(ServerPlayerEntity player, CustomPayload payload) {
        ServerPlayNetworking.send(player, payload);
    }
}
