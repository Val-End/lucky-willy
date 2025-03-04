package net.ethernity.lucky.client.model;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.entity.PaintingEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class PaintingModel extends GeoModel<PaintingEntity> {
    @Override
    public Identifier getModelResource(PaintingEntity paintingEntity) {
        return Identifier.of(LuckyWilly.MOD_ID, "geo/entity/painting.geo.json");
    }

    @Override
    public Identifier getTextureResource(PaintingEntity paintingEntity) {
        return Identifier.of(LuckyWilly.MOD_ID, "textures/entity/painting.png");
    }

    @Override
    public Identifier getAnimationResource(PaintingEntity paintingEntity) {
        return Identifier.of(LuckyWilly.MOD_ID, "animations/entity/painting.animation.json");
    }

    @Override
    public void setCustomAnimations(PaintingEntity animatable, long instanceId, AnimationState<PaintingEntity> animationState) {
    }
}
