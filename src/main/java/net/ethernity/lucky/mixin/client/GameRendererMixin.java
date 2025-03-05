package net.ethernity.lucky.mixin.client;

import net.ethernity.lucky.client.LuckyWillyClient;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Final
    @Shadow
    MinecraftClient client;

    @Shadow
    private boolean blockOutlineEnabled;

    @Shadow
    private static HitResult ensureTargetInRange(HitResult hitResult, Vec3d cameraPos, double interactionRange) {
        return null;
    }

    @Inject(method = "shouldRenderBlockOutline", at = @At("HEAD"), cancellable = true)
    private void shouldRenderBlockOutline(CallbackInfoReturnable<Boolean> cir) {
        if (!this.blockOutlineEnabled) {
            cir.setReturnValue(false);
        }

        PlayerEntity entity = LuckyWillyClient.getPlayerEntity() == null ? (PlayerEntity) this.client.getCameraEntity() : LuckyWillyClient.getPlayerEntity();
        boolean bl = !this.client.options.hudHidden;
        if (bl && !entity.getAbilities().allowModifyWorld) {
            ItemStack itemStack = entity.getMainHandStack();
            HitResult hitResult = this.client.crosshairTarget;
            if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos blockPos = ((BlockHitResult)hitResult).getBlockPos();
                BlockState blockState = this.client.world.getBlockState(blockPos);
                if (this.client.interactionManager.getCurrentGameMode() != GameMode.SPECTATOR) {
                    CachedBlockPosition cachedBlockPosition = new CachedBlockPosition(this.client.world, blockPos, false);
                    Registry<Block> registry = this.client.world.getRegistryManager().get(RegistryKeys.BLOCK);
                    bl = !itemStack.isEmpty() && (itemStack.canBreak(cachedBlockPosition) || itemStack.canPlaceOn(cachedBlockPosition));
                }
                else
                    bl = blockState.createScreenHandlerFactory(this.client.world, blockPos) != null;
            }
        }

        cir.setReturnValue(bl);
    }

    @Inject(method = "renderHand", at = @At("HEAD"), cancellable = true)
    private void findCrosshairTarget(Camera camera, float tickDelta, Matrix4f matrix4f, CallbackInfo ci) {
        if(LuckyWillyClient.getPlayerEntity() != null)
            ci.cancel();
    }

    @Inject(method = "findCrosshairTarget", at = @At("HEAD"), cancellable = true)
    private void findCrosshairTarget(Entity camera, double blockInteractionRange, double entityInteractionRange, float tickDelta, CallbackInfoReturnable<HitResult> cir) {
        if(LuckyWillyClient.getPlayerEntity() == null)
            return;

        camera = LuckyWillyClient.getPlayerEntity();
        double d = Math.max(blockInteractionRange, entityInteractionRange);
        double e = MathHelper.square(d);
        Vec3d vec3d = camera.getCameraPosVec(tickDelta);
        HitResult hitResult = camera.raycast(d, tickDelta, false);
        double f = hitResult.getPos().squaredDistanceTo(vec3d);
        if (hitResult.getType() != HitResult.Type.MISS) {
            e = f;
            d = Math.sqrt(f);
        }

        Vec3d vec3d2 = camera.getRotationVec(tickDelta);
        Vec3d vec3d3 = vec3d.add(vec3d2.x * d, vec3d2.y * d, vec3d2.z * d);
        float g = 1.0F;
        Box box = camera.getBoundingBox().stretch(vec3d2.multiply(d)).expand(1.0, 1.0, 1.0);
        EntityHitResult entityHitResult = ProjectileUtil.raycast(camera, vec3d, vec3d3, box, entity -> !entity.isSpectator() && entity.canHit(), e);
        HitResult result = entityHitResult != null && entityHitResult.getPos().squaredDistanceTo(vec3d) < f
                ? ensureTargetInRange(entityHitResult, vec3d, entityInteractionRange)
                : ensureTargetInRange(hitResult, vec3d, blockInteractionRange);

        cir.setReturnValue(result);
    }
}
