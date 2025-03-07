package net.ethernity.lucky.event.global;

import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.util.ItemUtil;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GiantsBattleEvent extends LuckyEvent {
    private static final double ATTACK_RANGE = Math.sqrt(2.04F) - 0.6F;

    public GiantsBattleEvent() {
        super(-1);
    }

    @Override
    public void execute(BlockPos pos, World world, PlayerEntity player) {
        ZombieEntity bob = new ZombieEntity(world) {
            @Override
            protected Box getAttackBox() {
                Entity entity = this.getVehicle();
                Box box3;
                if (entity != null) {
                    Box box = entity.getBoundingBox();
                    Box box2 = this.getBoundingBox();
                    box3 = new Box(
                            Math.min(box2.minX, box.minX), box2.minY, Math.min(box2.minZ, box.minZ), Math.max(box2.maxX, box.maxX), box2.maxY, Math.max(box2.maxZ, box.maxZ)
                    );
                } else {
                    box3 = this.getBoundingBox();
                }

                return box3.expand(ATTACK_RANGE * 2, 0.0, ATTACK_RANGE * 2);
            }

            @Override
            public void tick() {
                super.tick();
                if(!player.isAlive())
                    this.kill();
                if(!this.isAlive() && player.hasStatusEffect(LuckyWillyEffects.GIANT))
                    player.removeStatusEffect(LuckyWillyEffects.GIANT);
            }
        };

        bob.equipStack(EquipmentSlot.HEAD, this.createEnchantedItem(Items.DIAMOND_HELMET, world));
        bob.equipStack(EquipmentSlot.CHEST, this.createEnchantedItem(Items.DIAMOND_CHESTPLATE, world));
        bob.equipStack(EquipmentSlot.LEGS, this.createEnchantedItem(Items.DIAMOND_LEGGINGS, world));
        bob.equipStack(EquipmentSlot.FEET, this.createEnchantedItem(Items.DIAMOND_BOOTS, world));
        bob.equipStack(EquipmentSlot.MAINHAND, this.createEnchantedWeapon(world));

        bob.setCustomName(Text.of("Bobi").copy().setStyle(Style.EMPTY.withBold(true).withColor(Formatting.YELLOW)));
        bob.setCanPickUpLoot(false);
        bob.setPersistent();
        this.spawnMob(bob, world, pos);

        this.applyEffect(player, LuckyWillyEffects.GIANT, -1, 6);
        bob.addStatusEffect(new StatusEffectInstance(LuckyWillyEffects.GIANT, -1, 6));
    }

    private ItemStack createEnchantedItem(net.minecraft.item.Item item, World world) {
        ItemStack stack = new ItemStack(item);
        ItemUtil.enchant(stack, Enchantments.PROTECTION, 4, world);
        ItemUtil.enchant(stack, Enchantments.UNBREAKING, 3, world);
        ItemUtil.enchant(stack, Enchantments.MENDING, 1, world);
        if (item == Items.DIAMOND_BOOTS)
            ItemUtil.enchant(stack, Enchantments.FEATHER_FALLING, 4, world);

        return stack;
    }

    private ItemStack createEnchantedWeapon(World world) {
        ItemStack sword = new ItemStack(Items.DIAMOND_SWORD);
        ItemUtil.enchant(sword, Enchantments.SHARPNESS, 5, world);
        ItemUtil.enchant(sword, Enchantments.SWEEPING_EDGE, 3, world);
        ItemUtil.enchant(sword, Enchantments.UNBREAKING, 3, world);
        ItemUtil.enchant(sword, Enchantments.MENDING, 1, world);
        ItemUtil.enchant(sword, Enchantments.FIRE_ASPECT, 2, world);
        ItemUtil.enchant(sword, Enchantments.LOOTING, 3, world);
        ItemUtil.enchant(sword, Enchantments.KNOCKBACK, 2, world);
        return sword;
    }
}
