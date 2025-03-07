package net.ethernity.lucky.entity;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.ethernity.lucky.util.ItemUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.*;

public class GoodPaintingEntity extends LivingEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private UUID player;
    private int attackCooldown = 0;

    public GoodPaintingEntity(EntityType<? extends LivingEntity> entityType, World world) {
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

        if (this.attackCooldown > 518 && this.attackCooldown % 4 == 0) {
            SoundEvent[] noteBlockSounds = {
                    SoundEvents.BLOCK_NOTE_BLOCK_BELL.value(),
                    SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value(),
                    SoundEvents.BLOCK_NOTE_BLOCK_FLUTE.value(),
                    SoundEvents.BLOCK_NOTE_BLOCK_GUITAR.value(),
                    SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE.value()
            };
            SoundEvent randomSound = noteBlockSounds[this.getRandom().nextInt(noteBlockSounds.length)];
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), randomSound, SoundCategory.AMBIENT, 0.3f, 1.2f);
        }

        if (this.attackCooldown > 520)
            return;

        if (this.attackCooldown == 518) {
            if (player == null) return;

            PlayerEntity playerEntity = getWorld().getPlayerByUuid(player);

            if (playerEntity != null) {
                this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_NOTE_BLOCK_HAT, SoundCategory.AMBIENT, 0.4f, 1f);

                ItemStack weapon = Registries.ITEM.get(Identifier.of("pointblank", "glock17")).getDefaultStack();
                ItemEntity weaponEntity = new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), weapon);
                weaponEntity.setPickupDelay(20);
                weaponEntity.setVelocity(0, 0.5, 0);
                this.getWorld().spawnEntity(weaponEntity);

                ItemStack ammo = Registries.ITEM.get(Identifier.of("pointblank", "ammo9mm")).getDefaultStack().copyWithCount(20);
                ItemEntity ammoEntity = new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), ammo);
                ammoEntity.setPickupDelay(20);
                ammoEntity.setVelocity(0, 0.7, 0);
                this.getWorld().spawnEntity(ammoEntity);
            }
        }

        if (this.attackCooldown == 500) {
            remove(RemovalReason.DISCARDED);
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
        player.addStatusEffect(new StatusEffectInstance(LuckyWillyEffects.FREEZE, 20 * 5, 2));

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

        this.player = player.getUuid();
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
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
