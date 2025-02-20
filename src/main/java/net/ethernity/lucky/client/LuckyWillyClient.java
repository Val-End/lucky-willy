package net.ethernity.lucky.client;

import net.ethernity.lucky.client.renderer.TrotumanRenderer;
import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class LuckyWillyClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(LuckyWillyEntities.TROTUMAN, TrotumanRenderer::new);
	}
}