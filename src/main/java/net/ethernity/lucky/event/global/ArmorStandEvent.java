package net.ethernity.lucky.event.global;

import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.items.LuckyWillyItems;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ArmorStandEvent extends LuckyEvent {
    public ArmorStandEvent(int lucky) {
        super(lucky);
    }

    @Override
    public void execute(BlockPos pos, World wrld, PlayerEntity player) {
        if (!(wrld instanceof ServerWorld world))
            return;

        ArmorStandEntity customArmorStand = this.getArmorStandEntity(world, pos.toCenterPos().subtract(0, 0.5, 0));
        ItemStack playerHead = Items.PLAYER_HEAD.getDefaultStack();
        playerHead.set(DataComponentTypes.PROFILE, new ProfileComponent(player.getGameProfile()));

        customArmorStand.equipStack(EquipmentSlot.HEAD, playerHead);
        customArmorStand.equipStack(EquipmentSlot.CHEST, Items.LEATHER_CHESTPLATE.getDefaultStack());
        customArmorStand.equipStack(EquipmentSlot.LEGS, Items.LEATHER_LEGGINGS.getDefaultStack());
        customArmorStand.equipStack(EquipmentSlot.FEET, Items.LEATHER_BOOTS.getDefaultStack());

        world.spawnEntity(customArmorStand);
    }

    private @NotNull ArmorStandEntity getArmorStandEntity(ServerWorld world, Vec3d pos) {
        ArmorStandEntity customArmorStand = new ArmorStandEntity(world, pos.getX(), pos.getY(), pos.getZ()) {
            private int ticksExistedCustom = 0;
            private float rotationSpeed = 0.0f;

            @Override
            public void tick() {
                super.tick();
                this.ticksExistedCustom++;
                if (ticksExistedCustom % 2 == 0)
                    world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1, ticksExistedCustom / 100f);

                this.rotationSpeed += 0.2f;
                this.setYaw(this.getYaw() + this.rotationSpeed * 2f);

                Vec3d pos = this.getPos();
                this.setPos(pos.getX(), pos.getY() + this.rotationSpeed / 250d, pos.getZ());
                world.spawnParticles(ParticleTypes.WAX_OFF, pos.getX(), pos.getY(), pos.getZ(), 5, 0.2, 0.2, 0.2, 2);

                if (ticksExistedCustom >= 100) {
                    world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.NEUTRAL);
                    world.spawnParticles(ParticleTypes.CLOUD, pos.getX(), pos.getY(), pos.getZ(), 50, 0.2, 0.2, 0.2, 0.2);
                    if (getLucky() == -1)
                        ArmorStandEvent.spawnTnt(world, pos);
                    else
                        Block.dropStack(world, this.getBlockPos(), LuckyWillyItems.SLOW_TIME_ITEM.getDefaultStack());

                    this.remove(RemovalReason.DISCARDED);
                }
            }
        };

        customArmorStand.setNoGravity(true);
        return customArmorStand;
    }

    private static void spawnTnt(ServerWorld world, Vec3d pos) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (x == 0 && z == 0)
                    continue;

                TntEntity tnt = new TntEntity(EntityType.TNT, world);
                tnt.setPosition(pos.getX() + x, pos.getY(), pos.getZ() + z);
                double d = world.random.nextDouble() * (double) ((float) Math.PI * 2F);
                tnt.setVelocity(0.2f * x, 0.6F, 0.2f * z);
                tnt.setFuse(80);
                world.spawnEntity(tnt);
            }
        }
    }
}
