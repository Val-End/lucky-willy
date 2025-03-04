package net.ethernity.lucky.block;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.component.LuckyDataComponentTypes;
import net.ethernity.lucky.component.type.LuckysComponent;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class LuckyBlock extends Block {
    private static final Set<LuckyEvent> executedEvents = new LinkedHashSet<>();
    private static final double LUCK_MULTIPLIER = 0.1d;
    private static final int SEARCH_RADIUS = 32;

    public static final byte[] LUCKY_VALUES = new byte[]{-1, 0, 1};
    public static final IntProperty LUCKY = IntProperty.of("lucky", 0, 2);
    private final String luckyID;

    public LuckyBlock(String id) {
        super(AbstractBlock.Settings.create()
                .sounds(BlockSoundGroup.METAL)
                .strength(0.2F, 1200.0F));

        this.luckyID = id;
        this.setDefaultState(getStateManager().getDefaultState().with(LUCKY, 1));
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        LuckysComponent component = stack.get(LuckyDataComponentTypes.LUCKYS);
        if (component != null)
            component.appendTooltip(context, tooltip::add, options);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LUCKY);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        player.incrementStat(Stats.MINED.getOrCreateStat(this));
        player.addExhaustion(0.005F);
        this.executeEvent(state, pos, world, player);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        int luckyValue = 0;
        LuckysComponent luckysComponent = itemStack.get(LuckyDataComponentTypes.LUCKYS);
        if (luckysComponent != null)
            luckyValue = luckysComponent.lucky();

        world.setBlockState(pos, state.with(LUCKY, luckyValue));
        this.checkPowered(state, world, pos);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean notify) {
        if (!world.isClient)
            this.checkPowered(state, world, pos);

        super.neighborUpdate(state, world, pos, neighborBlock, fromPos, notify);
    }

    private void checkPowered(BlockState state, World world, BlockPos pos) {
        if (!world.isReceivingRedstonePower(pos))
            return;

        world.breakBlock(pos, false);
        PlayerEntity player = this.findNearestPlayer(world, pos);
        if (player != null)
            this.executeEvent(state, pos, world, player);
    }

    @Nullable
    private PlayerEntity findNearestPlayer(World world, BlockPos pos) {
        Vec3d center = pos.toCenterPos();
        return world.getClosestPlayer(center.getX(), center.getY(), center.getZ(), SEARCH_RADIUS, false);
    }

    private void executeEvent(BlockState state, BlockPos pos, World world, PlayerEntity player) {
        List<LuckyEvent> availableEvents = this.availableEvents(state, world, player);
        if (availableEvents.isEmpty())
            return;

        LuckyEvent selectedEvent = availableEvents.get(world.random.nextInt(availableEvents.size()));
        executedEvents.add(selectedEvent);

        double playerLuck = player.getAttributeInstance(EntityAttributes.GENERIC_LUCK).getBaseValue();
        double luckModifier = LUCK_MULTIPLIER * selectedEvent.getLucky();
        double newLuck = Math.clamp(playerLuck + luckModifier, -1.0, 1.0);

        player.getAttributeInstance(EntityAttributes.GENERIC_LUCK).setBaseValue(newLuck);
        LuckyWilly.LOGGER.info("Player {} luck changed: {} -> {}", player.getName().getString(), playerLuck, newLuck);
        LuckyWilly.LOGGER.info("Executing event: {} (luck: {})", LuckyWillyRegistries.LUCKY_EVENT.getId(selectedEvent), selectedEvent.getLucky());
        selectedEvent.execute(pos, world, player);
    }

    private List<LuckyEvent> availableEvents(BlockState state, World world, PlayerEntity player) {
        List<LuckyEvent> allEvents = this.getEvents(state);
        if (allEvents.isEmpty())
            return Collections.emptyList();

        // Repeat Filter
        List<LuckyEvent> availableEvents = allEvents.stream().filter(event -> !executedEvents.contains(event)).toList();
        if (availableEvents.isEmpty()) {
            LuckyWilly.LOGGER.info("Resetting lucky history");
            executedEvents.clear();
            availableEvents = allEvents;
        }

        double playerLuck = player.getAttributeInstance(EntityAttributes.GENERIC_LUCK).getBaseValue();
        List<LuckyEvent> luckFilteredEvents = this.applyLuckFilter(availableEvents, playerLuck, world);

        // If luck filtering removed all events, reset and try again
        if (luckFilteredEvents.isEmpty()) {
            LuckyWilly.LOGGER.info("Luck filtering removed all events, resetting");
            executedEvents.clear();
            luckFilteredEvents = this.applyLuckFilter(allEvents, playerLuck, world);
        }

        return luckFilteredEvents.isEmpty() ? availableEvents : luckFilteredEvents;
    }

    private List<LuckyEvent> applyLuckFilter(List<LuckyEvent> events, double playerLuck, World world) {
        return events.stream().filter(event -> this.includeEvent(event, playerLuck, world)).toList();
    }

    private boolean includeEvent(LuckyEvent event, double playerLuck, World world) {
        if ((playerLuck > 0 && event.getLucky() > 0) || (playerLuck < 0 && event.getLucky() < 0)) {
            double removalChance = Math.min(1.0, Math.abs(playerLuck));
            boolean remove = world.random.nextDouble() <= removalChance;
            if (remove)
                LuckyWilly.LOGGER.info("Removing event with {} luck on player with {} luck", event.getLucky(), playerLuck);

            return !remove;
        }

        return true;
    }

    private List<LuckyEvent> getEvents(BlockState state) {
        int luckyValue = this.getLucky(state);
        LuckyWilly.LOGGER.info("Getting events for block {} with luck {}", luckyID, luckyValue);

        return LuckyWillyRegistries.LUCKY_EVENT.stream()
                .filter(event -> {
                    String path = LuckyEvents.getId(event).getPath();
                    boolean matchesPrefix = path.startsWith(this.luckyID) || path.startsWith(LuckyEvents.ID);

                    boolean matchesLuck = true;
                    if (luckyValue < 0)
                        matchesLuck = event.getLucky() <= 0;
                    else if (luckyValue > 0)
                        matchesLuck = event.getLucky() >= 0;

                    return matchesPrefix && matchesLuck;
                })
                .toList();
    }

    private int getLucky(BlockState state) {
        return state.get(LUCKY) - 1;
    }

    @Override
    protected List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        return Collections.emptyList();
    }
}
