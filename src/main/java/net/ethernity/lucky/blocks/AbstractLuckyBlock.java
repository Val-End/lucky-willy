package net.ethernity.lucky.blocks;

import net.ethernity.lucky.blocks.events.LuckyEvent;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractLuckyBlock extends Block {
    public AbstractLuckyBlock() {
        super(AbstractBlock.Settings.create()
                .sounds(BlockSoundGroup.METAL)
                .strength(0.2F, 1200.0F));
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        player.incrementStat(Stats.MINED.getOrCreateStat(this));
        player.addExhaustion(0.005F);
        if(world instanceof ServerWorld svWorld)
            this.executeEvent(pos, svWorld, player);
    }

    private void executeEvent(BlockPos pos, ServerWorld world, PlayerEntity player) {
        List<LuckyEvent> events = this.getEvents();
        LuckyEvent event = events.get(world.random.nextInt(events.size()));
        event.execute(pos, world, player);
    }

    protected abstract List<LuckyEvent> getEvents();

    @Override
    protected List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        return List.of();
    }
}
