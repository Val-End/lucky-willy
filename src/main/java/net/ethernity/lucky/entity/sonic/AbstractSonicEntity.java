package net.ethernity.lucky.entity.sonic;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

abstract public class AbstractSonicEntity extends PassiveEntity implements GeoEntity {
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("idle");
    protected static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("walk");
    protected static final RawAnimation ATTACK_ANIM = RawAnimation.begin().thenPlay("attack");

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    protected AbstractSonicEntity(EntityType<? extends AbstractSonicEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::handler));
        controllerRegistrar.add(new AnimationController<>(this, "attack_controller", 0, this::attackHandler));
    }

    private <T extends GeoAnimatable> PlayState handler(AnimationState<T> state) {
        return state.setAndContinue(state.isMoving() ? AbstractSonicEntity.WALK_ANIM : AbstractSonicEntity.IDLE_ANIM);
    }

    private <T extends GeoAnimatable> PlayState attackHandler(AnimationState<T> state) {
        if (this.handSwinging && state.getController().getAnimationState() == AnimationController.State.STOPPED) {
            state.getController().forceAnimationReset();
            state.getController().setAnimation(AbstractSonicEntity.ATTACK_ANIM);
            this.handSwinging = false;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
