package net.ethernity.lucky.event.global;

import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.world.gen.LuckyWillyFeature;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MiniSpidersEvent extends LuckyEvent {
    public MiniSpidersEvent() {
        super(-1);
    }

    @Override
    public void execute(BlockPos pos, World world, PlayerEntity player) {
        int spiderCount = world.getRandom().nextBetweenExclusive(30, 50);
        for(int i = 0; i <= spiderCount; i++) {
            SpiderEntity spiderEntity = new SpiderEntity(EntityType.SPIDER, world);
            spiderEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(1.0d);
            spiderEntity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(1.0d);
            spiderEntity.getAttributeInstance(EntityAttributes.GENERIC_SCALE).setBaseValue(0.1d);
            spiderEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.2d);
            spiderEntity.setHealth(spiderEntity.getMaxHealth());
            spiderEntity.refreshPositionAndAngles(
                    pos.getX() + world.getRandom().nextFloat() * 2,
                    pos.getY(),
                    pos.getZ() + world.getRandom().nextFloat() * 2,
                    0, 0
            );
            world.spawnEntity(spiderEntity);
        }
    }
}
