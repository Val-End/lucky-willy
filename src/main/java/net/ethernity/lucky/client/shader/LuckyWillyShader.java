package net.ethernity.lucky.client.shader;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.ladysnake.satin.api.event.ShaderEffectRenderCallback;
import org.ladysnake.satin.api.managed.ManagedShaderEffect;
import org.ladysnake.satin.api.managed.ShaderEffectManager;

import java.util.ArrayList;

public class LuckyWillyShader {
    private static final ArrayList<ManagedShaderEffect> ACTIVE_SHADER = new ArrayList<>();

    public static final ManagedShaderEffect INVERT = ShaderEffectManager.getInstance()
            .manage(Identifier.ofVanilla("shaders/post/flip.json"));
    public static final ManagedShaderEffect ZAWARUDO = ShaderEffectManager.getInstance()
            .manage(Identifier.of(LuckyWilly.MOD_ID, "shaders/post/zawarudo.json"));

    public static void initialize() {
        ShaderEffectRenderCallback.EVENT.register(LuckyWillyShader::render);
    }

    public static void applyShader(ManagedShaderEffect shader) {
        LuckyWillyShader.ACTIVE_SHADER.add(shader);
    }

    public static void removeShader(ManagedShaderEffect shader) {
        LuckyWillyShader.ACTIVE_SHADER.remove(shader);
    }

    private static void render(float tickDelta) {
        if (!(MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living))
            return;

        if (living.hasStatusEffect(LuckyWillyEffects.INVERT))
            INVERT.render(tickDelta);

        for (ManagedShaderEffect shaderEffect : ACTIVE_SHADER) {
            shaderEffect.render(tickDelta);
        }
    }
}
