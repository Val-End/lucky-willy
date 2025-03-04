package net.ethernity.lucky.client.renderer;

import net.ethernity.lucky.LuckyWilly;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ScreenRenderer {
    private static int tick = 0;
    private static boolean started = false;
    private static final List<Identifier> screamer = new ArrayList<>();
    private static final int TEXTURE_WIDTH = 800;
    private static final int TEXTURE_HEIGHT = 873;

    public static void init() {
        for (int i = 0; i <= 30; i++) {
            Identifier identifier = Identifier.of(LuckyWilly.MOD_ID, "textures/screamer/frame_%s_delay-0.03s.png".formatted(i));
            screamer.add(identifier);
        }

        HudRenderCallback.EVENT.register(ScreenRenderer::render);
        ClientTickEvents.END_CLIENT_TICK.register(ScreenRenderer::tick);
    }

    private static void render(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        if (!started)
            return;

        int screenWidth = drawContext.getScaledWindowWidth();
        int screenHeight = drawContext.getScaledWindowHeight();

        drawContext.fill(0, 0, screenWidth, screenHeight, 0xff000000);

        float scale = Math.min((float) screenWidth / TEXTURE_WIDTH, (float) screenHeight / TEXTURE_HEIGHT);
        int renderWidth = (int) (TEXTURE_WIDTH * scale);
        int renderHeight = (int) (TEXTURE_HEIGHT * scale);

        int x = (screenWidth - renderWidth) / 2;
        int y = (screenHeight - renderHeight) / 2;
        drawContext.drawTexture(screamer.get(tick), x, y, 0, 0, renderWidth, renderHeight, renderWidth, renderHeight);
    }

    private static void tick(MinecraftClient client) {
        if (!started)
            return;

        tick++;
        started = tick < 30;
        if (!started)
            tick = 0;
    }

    public static void startScreamer() {
        started = true;
        SoundEvent sound = SoundEvent.of(Identifier.of(LuckyWilly.MOD_ID, "screamer"));
        MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(sound, 1.0F));
    }
}
