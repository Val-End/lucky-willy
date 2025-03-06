package net.ethernity.lucky.client;

import net.ethernity.lucky.client.network.LuckyWillyClientNetwork;
import net.ethernity.lucky.client.renderer.LuckyWillyRenderer;
import net.ethernity.lucky.client.renderer.LuckyWillyScreenRenderer;
import net.ethernity.lucky.client.shader.LuckyWillyShader;
import net.fabricmc.api.ClientModInitializer;

public class LuckyWillyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        LuckyWillyClientNetwork.initialize();
        LuckyWillyRenderer.initialize();
        LuckyWillyShader.initialize();
        LuckyWillyScreenRenderer.initialize();
    }
}