package net.ethernity.lucky.entity;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.*;

public class PaintingEntity extends LivingEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Map<UUID, Integer> grabbedPlayers = new HashMap<>();
    private int attackCooldown = 0;

    public PaintingEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected boolean isImmobile() {
        return true;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0F)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_SCALE, 1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.1);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 4, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "attackController", 4, this::predicateAttack));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {
        state.getController().setAnimation(RawAnimation.begin().thenLoop("idle"));
        return PlayState.CONTINUE;
    }

    private <T extends GeoAnimatable> PlayState predicateAttack(AnimationState<T> state) {
        if (this.handSwinging && state.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            state.getController().forceAnimationReset();
            state.getController().setAnimation(RawAnimation.begin().then("absotion", Animation.LoopType.PLAY_ONCE));
            this.handSwinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient)
            return;

        if (this.attackCooldown > 0)
            this.attackCooldown--;

        if (this.attackCooldown == 520) {
            SoundEvent sound = SoundEvent.of(Identifier.of(LuckyWilly.MOD_ID, "painting_jumpscare"));
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), sound, SoundCategory.AMBIENT, 0.4f, 1f);
        }

        if (this.attackCooldown > 520)
            return;

        Iterator<Map.Entry<UUID, Integer>> iterator = this.grabbedPlayers.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, Integer> entry = iterator.next();
            UUID playerId = entry.getKey();
            int progress = entry.getValue();

            PlayerEntity player = this.getWorld().getPlayerByUuid(playerId);
            if (player == null || !player.isAlive() || !this.isAlive() || player.getBlockPos().getSquaredDistance(this.getBlockPos()) > 5) {
                iterator.remove();
                continue;
            }

            double targetY = this.getY() - (progress / 20.0) * 3;

            float yaw = this.getYaw();
            double factor = (20.0 - progress) / 15.0;
            double offsetX = -Math.sin(Math.toRadians(yaw)) * factor * 0.9;
            double offsetZ = Math.cos(Math.toRadians(yaw)) * factor * 0.9;

            player.teleport(
                    (ServerWorld) this.getWorld(),
                    getX() + offsetX,
                    targetY,
                    getZ() + offsetZ,
                    EnumSet.noneOf(PositionFlag.class),
                    player.getYaw(),
                    player.getPitch()
            );

            this.grabbedPlayers.put(playerId, progress + 1);

            if (progress >= 20)
                iterator.remove();
        }
    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        if (player.getWorld().isClient || this.attackCooldown > 0)
            return;
        if (player.isCreative() || player.isSpectator())
            return;

        this.attackCooldown = 600;
        this.swingHand(Hand.MAIN_HAND);
        SoundEvent sound = SoundEvent.of(Identifier.of(LuckyWilly.MOD_ID, "painting_voice"));
        getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), sound, SoundCategory.AMBIENT, 0.3f, 1f);
        player.addStatusEffect(new StatusEffectInstance(LuckyWillyEffects.FREEZE, 20 * 10, 2));

        float yaw = this.getYaw();
        double offsetX = -Math.sin(Math.toRadians(yaw)) * 0.9;
        double offsetZ = Math.cos(Math.toRadians(yaw)) * 0.9;

        player.teleport(
                (ServerWorld) this.getWorld(),
                this.getX() + offsetX,
                player.getY(),
                this.getZ() + offsetZ,
                EnumSet.noneOf(PositionFlag.class),
                player.getYaw(),
                player.getPitch()
        );

        this.grabbedPlayers.put(player.getUuid(), 0);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        this.grabbedPlayers.clear();
    }

    @Override
    public void pushAwayFrom(Entity entity) {
    }

    @Override
    protected void pushAway(Entity entity) {
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return List.of();
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
    }
}
