package net.ethernity.lucky.mixin;

import net.ethernity.lucky.client.managers.CameraManager;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public class CameraMixin {

    @Shadow
    private float pitch;
    @Shadow private float yaw;

    @Shadow @Final
    private Quaternionf rotation;

    @Shadow @Final private static Vector3f HORIZONTAL;

    @Shadow @Final private static Vector3f VERTICAL;

    @Shadow @Final private static Vector3f DIAGONAL;

    @Shadow @Final private Vector3f horizontalPlane;

    @Shadow @Final private Vector3f verticalPlane;

    @Shadow @Final private Vector3f diagonalPlane;

    @Shadow private Entity focusedEntity;

    @Shadow private float lastCameraY;

    @Shadow private float cameraY;

    @Unique
    public void cameraSet(float yaw, float pitch, float roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.rotation.rotationYXZ((float)Math.PI - yaw * ((float)Math.PI / 180F), -pitch * ((float)Math.PI / 180F), (float)Math.PI - roll * ((float)Math.PI / 180F));
        HORIZONTAL.rotate(this.rotation, this.horizontalPlane);
        VERTICAL.rotate(this.rotation, this.verticalPlane);
        DIAGONAL.rotate(this.rotation, this.diagonalPlane);
    }

    @Inject(method = "setRotation", at = @At("HEAD"), cancellable = true)
    public void inject(float yaw, float pitch, CallbackInfo ci) {
        float cacheYaw = CameraManager.yaw;
        float cachePitch = CameraManager.pitch;
        float cacheRoll = CameraManager.getRoll();

        cameraSet(yaw + cacheYaw, pitch + cachePitch, cacheRoll);
        ci.cancel();
    }
}
