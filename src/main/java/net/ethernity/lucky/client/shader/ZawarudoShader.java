package net.ethernity.lucky.client.shader;

import net.ethernity.lucky.network.packet.s2c.ZawarudoPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ZawarudoShader {
    public static void handle(ZawarudoPayload payload, ClientPlayNetworking.Context context) {
        context.client().execute(() -> {
            if (payload.active())
                LuckyWillyShader.applyShader(LuckyWillyShader.ZAWARUDO);
            else
                LuckyWillyShader.removeShader(LuckyWillyShader.ZAWARUDO);
        });
    }
}
