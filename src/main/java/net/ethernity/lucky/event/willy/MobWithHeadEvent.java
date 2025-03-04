package net.ethernity.lucky.event.willy;

import com.mojang.authlib.properties.PropertyMap;
import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class MobWithHeadEvent<T extends LivingEntity> extends LuckyEvent {
    private static final List<String> PLAYERS = List.of(
            "TheWillyrex",
            "VegettaGaymer",
            "elrubius",
            "xFaRgAnxYT",
            "Mangel_",
            "alexby11",
            "LuzuVlogs",
            "LOLiTOFDEZ"
    );

    private static String lastPlayer = "";
    private final EntityType<T> entityType;

    public MobWithHeadEvent(EntityType<T> entityType) {
        super(-1);
        this.entityType = entityType;
    }

    @Override
    public void execute(BlockPos pos, World world, PlayerEntity player) {
        ItemStack playerHead = Items.PLAYER_HEAD.getDefaultStack();

        String name = MobWithHeadEvent.getName(world);
        ProfileComponent profile = new ProfileComponent(Optional.ofNullable(name), Optional.empty(), new PropertyMap());
        playerHead.set(DataComponentTypes.PROFILE, profile);

        LivingEntity mob = entityType.create(world);
        if (mob instanceof SpiderEntity) {
            mob = this.getSpiderBoss(name, world);
            mob.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(100.0d);
            mob.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5d);
            mob.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(12.0d);
            mob.getAttributeInstance(EntityAttributes.GENERIC_SCALE).setBaseValue(3.0d);
            mob.setHealth(mob.getMaxHealth());
        }

        mob.equipStack(EquipmentSlot.HEAD, playerHead);
        this.spawnMob(mob, world, pos);
    }

    private SpiderEntity getSpiderBoss(String name, World world) {
        return new SpiderEntity(EntityType.SPIDER, world) {
            final ServerBossBar bossBar = new ServerBossBar(Text.of(name), BossBar.Color.RED, BossBar.Style.PROGRESS);

            @Override
            protected void mobTick() {
                super.mobTick();
                this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
            }

            @Override
            public void onStartedTrackingBy(ServerPlayerEntity player) {
                super.onStartedTrackingBy(player);
                this.bossBar.addPlayer(player);
            }

            @Override
            public void onStoppedTrackingBy(ServerPlayerEntity player) {
                super.onStoppedTrackingBy(player);
                this.bossBar.removePlayer(player);
            }
        };
    }

    private static String getName(World world) {
        String player = lastPlayer;
        while (player.equals(lastPlayer)) {
            player = MobWithHeadEvent.PLAYERS.get(world.random.nextInt(MobWithHeadEvent.PLAYERS.size()));
        }

        return lastPlayer = player;
    }
}
