package net.ethernity.lucky.client;

import net.ethernity.lucky.client.renderer.LuckyWillyRenderer;
import net.ethernity.lucky.client.renderer.ScreenRenderer;
import net.ethernity.lucky.client.shader.LuckyWillyShader;
import net.ethernity.lucky.network.LuckyWillyNetwork;
import net.fabricmc.api.ClientModInitializer;

public class LuckyWillyClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LuckyWillyRenderer.initialize();
        LuckyWillyNetwork.initializeClient();
        LuckyWillyShader.initialize();

        ScreenRenderer.init();
    }
}