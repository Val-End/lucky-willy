package net.ethernity.lucky.event;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.util.ItemUtil;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class LuckyEventBuilder {
    private final int lucky;
    private final List<Consumer<ExecutionContext>> actions = new ArrayList<>();

    private LuckyEventBuilder(int lucky) {
        this.lucky = lucky;
    }

    public static LuckyEventBuilder create(int lucky) {
        return new LuckyEventBuilder(lucky);
    }

    public static LuckyEvent empty() {
        return create(0).build();
    }

    public LuckyEventBuilder withAction(Consumer<ExecutionContext> action) {
        this.actions.add(action);
        return this;
    }

    public LuckyEventBuilder giveStack(Identifier id) {
        return this.giveStack(ItemUtil.modStack(id));
    }

    public LuckyEventBuilder giveStack(Item item) {
        return this.giveStack(item.getDefaultStack());
    }

    public LuckyEventBuilder giveStack(ItemStack stack) {
        return this.withAction(ctx -> ctx.player().giveItemStack(stack));
    }

    public LuckyEventBuilder dropStack(Identifier id) {
        return this.dropStack(ItemUtil.modStack(id));
    }

    public LuckyEventBuilder dropStack(Item item) {
        return this.dropStack(item.getDefaultStack());
    }

    public LuckyEventBuilder dropStack(ItemStack stack) {
        return this.withAction(ctx -> Block.dropStack(ctx.world(), ctx.pos(), stack));
    }

    public LuckyEventBuilder spawnMob(Identifier id) {
        if (!FabricLoader.getInstance().isModLoaded(id.getNamespace())) {
            LuckyWilly.LOGGER.warn("Mod {} is not loaded, cannot spawn entity", id.getNamespace());
            return this;
        }

        return this.spawnMob(Registries.ENTITY_TYPE.get(id));
    }

    public <T extends Entity> LuckyEventBuilder spawnMob(EntityType<T> type) {
        return this.withAction(ctx -> {
            T entity = type.create(ctx.world());
            if (entity == null)
                return;

            BlockPos pos = ctx.pos();
            entity.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
            ctx.world().spawnEntity(entity);
        });
    }

    public LuckyEventBuilder placeFeature(RegistryKey<Feature<?>> key) {
        return this.placeFeature(key, false);
    }

    public LuckyEventBuilder placeFeature(RegistryKey<Feature<?>> key, boolean placeOnPlayer) {
        return this.withAction(ctx -> {
            BlockPos pos = placeOnPlayer ? ctx.player().getBlockPos() : ctx.pos();
            ServerWorld world = ctx.world();

            Optional<Feature<?>> feature = world.getRegistryManager().get(RegistryKeys.FEATURE)
                    .getEntry(key)
                    .flatMap(entry -> Optional.ofNullable(entry.value()));

            feature.ifPresent(f -> f.generate(new FeatureContext<>(
                    Optional.empty(),
                    world,
                    world.getChunkManager().getChunkGenerator(),
                    world.getRandom(),
                    pos,
                    null
            )));
        });
    }

    public LuckyEventBuilder applyEffect(RegistryEntry<StatusEffect> effect) {
        return this.applyEffect(effect, 200, 0);
    }

    public LuckyEventBuilder applyEffect(RegistryEntry<StatusEffect> effect, int duration) {
        return this.applyEffect(effect, duration, 0);
    }

    public LuckyEventBuilder applyEffect(RegistryEntry<StatusEffect> effect, int duration, int amplifier) {
        return this.withAction(ctx -> ctx.player().addStatusEffect(new StatusEffectInstance(effect, duration, amplifier)));
    }

    public LuckyEvent build() {
        return new LuckyEvent(lucky) {
            @Override
            public void execute(BlockPos pos, ServerWorld world, PlayerEntity player) {
                ExecutionContext context = new ExecutionContext(pos, world, player);
                for (Consumer<ExecutionContext> action : actions) {
                    action.accept(context);
                }
            }
        };
    }

    public record ExecutionContext(BlockPos pos, ServerWorld world, PlayerEntity player) {}
}