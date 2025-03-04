package net.ethernity.lucky.items;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.network.LuckyWillyNetwork;
import net.ethernity.lucky.network.packet.s2c.ZawarudoPayload;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SlowTimeItem extends Item {
    private static final int EFFECT_DURATION = 600;
    private static final int COOLDOWN_DURATION = 3600;
    private static final float SLOW_FACTOR = 0.5f;
    private PlayerEntity user;

    public SlowTimeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (world.isClient)
            return TypedActionResult.success(stack);

        this.setUser(user);
        world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE.value(), SoundCategory.PLAYERS, 1.0f, 0.5f);
        user.getItemCooldownManager().set(this, SlowTimeItem.COOLDOWN_DURATION);

        float originalTickRate = world.getServer().getTickManager().getTickRate();
        world.getServer().getTickManager().setTickRate(originalTickRate * SLOW_FACTOR);

        this.sendEveryoneShader(true, world);
        LuckyWilly.queueServerWork(Math.round(EFFECT_DURATION * SLOW_FACTOR), () -> {
            this.sendEveryoneShader(false, world);
            world.getServer().getTickManager().setTickRate(originalTickRate);
            world.playSound(null, this.getUser().getBlockPos(), SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE.value(), SoundCategory.PLAYERS, 1.0f, 1.0f);
        });

        return TypedActionResult.success(stack);
    }

    private void sendEveryoneShader(boolean active, World world) {
        for (ServerPlayerEntity player : world.getServer().getPlayerManager().getPlayerList()) {
            LuckyWillyNetwork.s2c(player, new ZawarudoPayload(active));
        }
    }

    private void setUser(PlayerEntity user) {
        this.user = user;
    }

    private PlayerEntity getUser() {
        return this.user;
    }
}
