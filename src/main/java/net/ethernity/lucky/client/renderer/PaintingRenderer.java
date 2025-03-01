package net.ethernity.lucky.client.renderer;

import net.ethernity.lucky.client.model.PaintingModel;
import net.ethernity.lucky.entity.PaintingEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PaintingRenderer extends GeoEntityRenderer<PaintingEntity> {
    public PaintingRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new PaintingModel());
    }
}
