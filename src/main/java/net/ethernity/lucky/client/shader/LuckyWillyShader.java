package net.ethernity.lucky.client.shader;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.ladysnake.satin.api.event.ShaderEffectRenderCallback;
import org.ladysnake.satin.api.managed.ManagedShaderEffect;
import org.ladysnake.satin.api.managed.ShaderEffectManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LuckyWillyShader {
    public static final ManagedShaderEffect INVERT = ShaderEffectManager.getInstance()
            .manage(Identifier.ofVanilla("shaders/post/flip.json"));
    public static final ManagedShaderEffect ZAWARUDO = ShaderEffectManager.getInstance()
            .manage(Identifier.of(LuckyWilly.MOD_ID, "shaders/post/zawarudo.json"));

    private static final ArrayList<ManagedShaderEffect> ACTIVE_SHADER = new ArrayList<>();
    private static final Map<RegistryEntry<StatusEffect>, ManagedShaderEffect> EFFECT_SHADER = new HashMap<>();


    public static void initialize() {
        LuckyWillyShader.EFFECT_SHADER.put(LuckyWillyEffects.INVERT, LuckyWillyShader.INVERT);
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

        for (Map.Entry<RegistryEntry<StatusEffect>, ManagedShaderEffect> entry : LuckyWillyShader.EFFECT_SHADER.entrySet()) {
            if (living.hasStatusEffect(entry.getKey()))
                entry.getValue().render(tickDelta);
        }

        for (ManagedShaderEffect shaderEffect : ACTIVE_SHADER) {
            shaderEffect.render(tickDelta);
        }
    }
}
