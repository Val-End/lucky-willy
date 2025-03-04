package net.ethernity.lucky.client.renderer;

import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class LuckyWillyRenderer {
    public static void initialize() {
        EntityRendererRegistry.register(LuckyWillyEntities.TROTUMAN, TrotumanRenderer::new);
        EntityRendererRegistry.register(LuckyWillyEntities.PAINTING, PaintingRenderer::new);
    }
}
