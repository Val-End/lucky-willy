package net.ethernity.lucky.client.renderer;

import net.ethernity.lucky.client.model.GoodPaintingModel;
import net.ethernity.lucky.client.model.PaintingModel;
import net.ethernity.lucky.entity.GoodPaintingEntity;
import net.ethernity.lucky.entity.PaintingEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GoodPaintingRenderer extends GeoEntityRenderer<GoodPaintingEntity> {
    public GoodPaintingRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new GoodPaintingModel());
    }

    @Override
    public boolean hasLabel(GoodPaintingEntity animatable) {
        return false;
    }
}
