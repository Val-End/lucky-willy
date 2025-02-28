package net.ethernity.lucky.client.managers;

import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.minecraft.client.MinecraftClient;

public class CameraManager {
    public static float pitch = 0;
    public static float yaw = 0;
    public static float roll = 180;

    public static float getRoll() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.player.hasStatusEffect(LuckyWillyEffects.INVERT)) {
            return 0;
        }
        return 180;
    }
}
