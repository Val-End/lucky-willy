package net.ethernity.lucky.client;

import net.ethernity.lucky.client.renderer.PaintingRenderer;
import net.ethernity.lucky.client.renderer.ScreenRenderer;
import net.ethernity.lucky.client.renderer.TrotumanRenderer;
import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.ladysnake.satin.api.event.ShaderEffectRenderCallback;
import org.ladysnake.satin.api.managed.ManagedShaderEffect;
import org.ladysnake.satin.api.managed.ShaderEffectManager;

public class LuckyWillyClient implements ClientModInitializer {
	private static final ManagedShaderEffect INVERT = ShaderEffectManager.getInstance()
			.manage(Identifier.ofVanilla("shaders/post/flip.json"));

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(LuckyWillyEntities.TROTUMAN, TrotumanRenderer::new);
		EntityRendererRegistry.register(LuckyWillyEntities.PAINTING, PaintingRenderer::new);
		ScreenRenderer.init();

		ShaderEffectRenderCallback.EVENT.register(tickDelta -> {
			if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living) {
				if(living.hasStatusEffect(LuckyWillyEffects.INVERT))
					INVERT.render(tickDelta);
			}
		});
	}
}