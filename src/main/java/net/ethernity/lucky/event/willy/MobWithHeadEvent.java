package net.ethernity.lucky.event.willy;

import com.mojang.authlib.properties.PropertyMap;
import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.*;

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
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        ItemStack playerHead = Items.PLAYER_HEAD.getDefaultStack();

        ProfileComponent profile = new ProfileComponent(Optional.ofNullable(MobWithHeadEvent.getName(world)), Optional.empty(), new PropertyMap());
        playerHead.set(DataComponentTypes.PROFILE, profile);

        LivingEntity spider = entityType.spawn(world, pos, SpawnReason.EVENT);
        spider.equipStack(EquipmentSlot.HEAD, playerHead);
    }

    private static String getName(ServerWorld world) {
        String player = lastPlayer;
        while(player.equals(lastPlayer)) {
            player = MobWithHeadEvent.PLAYERS.get(world.random.nextInt(MobWithHeadEvent.PLAYERS.size()));
        }

        return lastPlayer = player;
    }
}
