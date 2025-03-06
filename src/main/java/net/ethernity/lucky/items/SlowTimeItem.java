package net.ethernity.lucky.items;

import net.ethernity.lucky.network.LuckyWillyNetwork;
import net.ethernity.lucky.network.packet.s2c.ZawarudoPayload;
import net.ethernity.lucky.server.LuckyWillyServer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SlowTimeItem extends Item {
    private static final int EFFECT_DURATION = 600;
    private static final float SLOW_FACTOR = 0.25f;
    private PlayerEntity user;

    public SlowTimeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World wrld, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if(!(wrld instanceof ServerWorld world) || wrld.isClient)
            return TypedActionResult.success(stack);

        this.setUser(user);
        world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE.value(), SoundCategory.PLAYERS, 1.0f, 0.5f);

        EquipmentSlot equipmentSlot = stack.equals(user.getEquippedStack(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
        stack.damage(1, user, equipmentSlot);

        float originalTickRate = world.getTickManager().getTickRate();
        world.getTickManager().setTickRate(originalTickRate * SLOW_FACTOR);

        this.sendEveryoneShader(true, world);
        LuckyWillyServer.queueWork(Math.round(EFFECT_DURATION * SLOW_FACTOR), () -> {
            this.sendEveryoneShader(false, world);
            world.getTickManager().setTickRate(originalTickRate);
            world.playSound(null, this.getUser().getBlockPos(), SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE.value(), SoundCategory.PLAYERS, 1.0f, 1.0f);
        });

        return TypedActionResult.success(stack);
    }

    private void sendEveryoneShader(boolean active, ServerWorld world) {
        for (ServerPlayerEntity player : world.getPlayers()) {
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
