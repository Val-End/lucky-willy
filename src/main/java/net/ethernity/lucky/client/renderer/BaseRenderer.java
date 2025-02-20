package net.ethernity.lucky.client.renderer;

import net.ethernity.lucky.LuckyWilly;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BaseRenderer<T extends LivingEntity & GeoAnimatable> extends GeoEntityRenderer<T> {
    public BaseRenderer(EntityRendererFactory.Context renderManager, String id, boolean turnsHead) {
        super(renderManager, new DefaultedEntityGeoModel<>(Identifier.of(LuckyWilly.MOD_ID, id), turnsHead));
    }
}
