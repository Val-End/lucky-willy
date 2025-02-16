package net.ethernity.lucky.entity.sonic;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

public class CubotEntity extends AbstractSonicEntity {
    public CubotEntity(EntityType<CubotEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar.setColor(BossBar.Color.YELLOW);
    }

    public static DefaultAttributeContainer.Builder createCubotAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23F)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0);
    }
}
