package net.ethernity.lucky.event.willy;

import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.ethernity.lucky.entity.TrotumanEntity;
import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

public class TrotumanEvent extends LuckyEvent {
    public TrotumanEvent() {
        super(1);
    }

    @Override
    public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
        TrotumanEntity trotuman = LuckyWillyEntities.TROTUMAN.create(world);
        trotuman.setOwner(player);
        this.spawnMob(trotuman, world, pos);

        String msg = "Hola " + player.getName().getString() + ", dame una espada asi te ayudo";
        player.sendMessage(Text.literal("<")
                .append(Text.literal("Trotuman").setStyle(Style.EMPTY.withColor(Formatting.AQUA)))
                .append(Text.literal("> "))
                .append(msg));
    }
}
