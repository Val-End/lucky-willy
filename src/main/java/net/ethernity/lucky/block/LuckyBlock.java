package net.ethernity.lucky.block;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.event.LuckyEvents;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LuckyBlock extends Block {
    private static final Set<LuckyEvent> executedEvents = new HashSet<>();
    private final String luckyID;

    public LuckyBlock(String id) {
        super(AbstractBlock.Settings.create()
                .sounds(BlockSoundGroup.METAL)
                .strength(0.2F, 1200.0F));

        this.luckyID = id;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        player.incrementStat(Stats.MINED.getOrCreateStat(this));
        player.addExhaustion(0.005F);
        if(world instanceof ServerWorld svWorld)
            this.executeEvent(pos, svWorld, player);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        this.isPowered(state, world, pos);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean notify) {
        if (!world.isClient)
            this.isPowered(state, world, pos);

        super.neighborUpdate(state, world, pos, neighborBlock, fromPos, notify);
    }

    private void isPowered(BlockState state, World world, BlockPos pos) {
        if (!world.isReceivingRedstonePower(pos))
            return;

        world.breakBlock(pos, true);

        Vec3d pos3d = pos.toCenterPos();
        PlayerEntity player = world.getClosestPlayer(pos3d.getX(), pos3d.getY(), pos3d.getZ(), 32, false);
        if(player != null)
            this.afterBreak(world, player, pos, state, null, ItemStack.EMPTY);
    }

    private void executeEvent(BlockPos pos, ServerWorld world, PlayerEntity player) {
        List<LuckyEvent> events = this.getEvents();
        if(events.isEmpty())
            return;

        List<LuckyEvent> availableEvents = events
                .stream()
                .filter(event -> !executedEvents.contains(event))
                .toList();

        if (availableEvents.isEmpty())
            this.cleanEvents(availableEvents, events);

        double playerLuck = player.getAttributeInstance(EntityAttributes.GENERIC_LUCK).getBaseValue();
        LuckyWilly.LOGGER.info("{} lucky: {}", player.getName().getLiteralString(), playerLuck);

        List<LuckyEvent> filteredEvents = availableEvents.stream().filter(event -> luckySystem(event, playerLuck, world)).toList();
        if(filteredEvents.isEmpty()){
            this.cleanEvents(availableEvents, events);
            filteredEvents = availableEvents.stream().filter(event -> luckySystem(event, playerLuck, world)).toList();
        }

        List<LuckyEvent> finalEvents = filteredEvents.isEmpty() ? availableEvents : filteredEvents;
        LuckyEvent event = finalEvents.get(world.random.nextInt(finalEvents.size()));

        double luck = 0.1d * event.getLucky();
        player.getAttributeInstance(EntityAttributes.GENERIC_LUCK).setBaseValue(Math.clamp(playerLuck + luck, -1, 1));

        LuckyWilly.LOGGER.info("New Player Lucky: {}", playerLuck + luck);
        LuckyWilly.LOGGER.info("Firing ({}): {}", event.getLucky(), LuckyWillyRegistries.LUCKY_EVENT.getId(event));

        event.execute(pos, world, player);
        executedEvents.add(event);
    }

    private boolean luckySystem(LuckyEvent event, double playerLuck, ServerWorld world) {
        if ((playerLuck > 0 && event.getLucky() > 0) || (playerLuck < 0 && event.getLucky() < 0)) {
            double removalChance = Math.min(1.0, Math.abs(playerLuck));
            boolean remove = world.random.nextDouble() <= removalChance;

            if(remove)
                LuckyWilly.LOGGER.info("Removing {} Luck Event", event.getLucky());

            return !remove;
        }

        return true;
    }

    private void cleanEvents(List<LuckyEvent> availableEvents, List<LuckyEvent> events) {
        executedEvents.clear();
        availableEvents = List.copyOf(events);
    }

    public List<LuckyEvent> getEvents() {
        return LuckyWillyRegistries.LUCKY_EVENT.stream().filter(key -> {
            String path = LuckyEvents.getId(key).getPath();
            return path.startsWith(this.luckyID) || path.startsWith(LuckyEvents.ID);
        }).toList();
    }

    @Override
    protected List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        return List.of();
    }
}
