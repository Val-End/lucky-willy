package net.ethernity.lucky.client;

import net.ethernity.lucky.client.renderer.BaseSonicRenderer;
import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class LuckyWillyClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(LuckyWillyEntities.SONIC,
				(context) -> new BaseSonicRenderer<>(context, "sonic")
		);
		EntityRendererRegistry.register(LuckyWillyEntities.TAILS,
				(context) -> new BaseSonicRenderer<>(context, "tails")
		);
		EntityRendererRegistry.register(LuckyWillyEntities.KNUCKLES,
				(context) -> new BaseSonicRenderer<>(context, "knuckles")
		);
		EntityRendererRegistry.register(LuckyWillyEntities.AMY,
				(context) -> new BaseSonicRenderer<>(context, "amy")
		);
		EntityRendererRegistry.register(LuckyWillyEntities.SILVER,
				(context) -> new BaseSonicRenderer<>(context, "silver")
		);
		EntityRendererRegistry.register(LuckyWillyEntities.SHADOW,
				(context) -> new BaseSonicRenderer<>(context, "shadow")
		);
		EntityRendererRegistry.register(LuckyWillyEntities.METAL_SONIC,
				(context) -> new BaseSonicRenderer<>(context, "sonic_robot")
		);
		EntityRendererRegistry.register(LuckyWillyEntities.EGGMAN,
				(context) -> new BaseSonicRenderer<>(context, "eggman")
		);
	}
}