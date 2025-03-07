package net.ethernity.lucky.client.model;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.entity.GoodPaintingEntity;
import net.ethernity.lucky.entity.PaintingEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class GoodPaintingModel extends GeoModel<GoodPaintingEntity> {
    @Override
    public Identifier getModelResource(GoodPaintingEntity paintingEntity) {
        return Identifier.of(LuckyWilly.MOD_ID, "geo/entity/painting_good.geo.json");
    }

    @Override
    public Identifier getTextureResource(GoodPaintingEntity paintingEntity) {
        return Identifier.of(LuckyWilly.MOD_ID, "textures/entity/painting_good.png");
    }

    @Override
    public Identifier getAnimationResource(GoodPaintingEntity paintingEntity) {
        return Identifier.of(LuckyWilly.MOD_ID, "animations/entity/painting_good.animation.json");
    }
}
