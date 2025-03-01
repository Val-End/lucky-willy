package net.ethernity.lucky.client.renderer;

import net.ethernity.lucky.client.model.PaintingModel;
import net.ethernity.lucky.entity.PaintingEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PaintingRenderer extends GeoEntityRenderer<PaintingEntity> {
    public PaintingRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new PaintingModel());
    }

    @Override
    protected void renderLabelIfPresent(PaintingEntity entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float tickDelta) {

    }
}
