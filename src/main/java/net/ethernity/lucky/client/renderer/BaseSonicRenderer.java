package net.ethernity.lucky.client.renderer;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.entity.sonic.AbstractSonicEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BaseSonicRenderer<T extends AbstractSonicEntity> extends GeoEntityRenderer<T> {
    public BaseSonicRenderer(EntityRendererFactory.Context renderManager, String id) {
        super(renderManager, new DefaultedEntityGeoModel<>(Identifier.of(LuckyWilly.MOD_ID, id)));
    }
}
