package net.ethernity.lucky.event.vanilla;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.event.LuckyEventBuilder;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.ethernity.lucky.world.gen.LuckyWillyFeature;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public final class VanillaLuckyEvents {
    public static final String ID = "vanilla";

    public static final LuckyEvent ANVIL_TRAP_EVENT = register(
            "anvil_trap_event", LuckyEventBuilder.create(-1)
                    .placeFeature(LuckyWillyFeature.ANVIL_TRAP_KEY, true)
    );
    public static final LuckyEvent LAVA_TRAP_EVENT = register(
            "lava_trap_event", LuckyEventBuilder.create(-1)
                    .placeFeature(LuckyWillyFeature.LAVA_TRAP_KEY, true)
    );
    public static final LuckyEvent GIANT_EVENT = register(
            "giant_event", LuckyEventBuilder.create(-1)
                    .spawnMob(EntityType.GIANT)
    );
    public static final LuckyEvent DROP_TNT_EVENT = register(
            "drop_tnt_event", LuckyEventBuilder.create(0)
                    .dropStack(Blocks.TNT.asItem().getDefaultStack().copyWithCount(8))
    );
    public static final LuckyEvent FOUR_LUCKY_PYRAMID_EVENT = register(
            "four_lucky_pyramid_event", LuckyEventBuilder.create(0)
                    .placeFeature(LuckyWillyFeature.FOUR_LUCKY_PYRAMID_KEY)
    );
    public static final LuckyEvent BEACON_EVENT = register(
            "beacon_event", LuckyEventBuilder.create(0)
                    .dropStack(Blocks.BEACON.asItem())
    );
    public static final LuckyEvent CHARGED_CREEPER_EVENT = register(
            "charged_creeper_event", LuckyEventBuilder.create(-1)
                    .spawnMob(EntityType.CREEPER)
                    .spawnMob(EntityType.LIGHTNING_BOLT)
    );
    public static final LuckyEvent GOLDEN_APPLES_EVENT = register(
            "golden_apples_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.ENCHANTED_GOLDEN_APPLE)
                    .dropStack(Items.GOLDEN_APPLE.getDefaultStack().copyWithCount(3))
    );
    public static final LuckyEvent STONE_KIT_EVENT = register(
            "stone_kit_event", LuckyEventBuilder.create(1)
                    .dropStack(Items.STONE_AXE)
                    .dropStack(Items.STONE_HOE)
                    .dropStack(Items.STONE_PICKAXE)
                    .dropStack(Items.STONE_SHOVEL)
                    .dropStack(Items.STONE_SWORD)
    );
    public static final LuckyEvent HORSE_ARMOR_EVENT = register(
            "horse_armor_event", LuckyEventBuilder.create(0)
                    .dropStack(Items.DIAMOND_HORSE_ARMOR)
                    .dropStack(Items.IRON_HORSE_ARMOR)
                    .dropStack(Items.GOLDEN_HORSE_ARMOR)
    );

    public static final LuckyEvent BOB_EVENT = register("bob_event", BobEvent::new);
    public static final LuckyEvent DIAMOND_BLOCK_EVENT = register("diamond_block_event", DiamondBlockEvent::new);
    public static final LuckyEvent EXPLOSION_EVENT = register("explosion_event", ExplosionEvent::new);
    public static final LuckyEvent LAPIZ_BLOCK_EVENT = register("lapiz_block_event", LapizBlockEvent::new);
    public static final LuckyEvent RANDOM_CHEST_LOOT_EVENT = register("random_chest_loot_event", RandomChestLootEvent::new);

    private static LuckyEvent register(String id, LuckyEvent event) {
        return Registry.register(LuckyWillyRegistries.LUCKY_EVENT, Identifier.of(LuckyWilly.MOD_ID, ID + "/" + id), event);
    }

    private static LuckyEvent register(String id, LuckyEventBuilder builder) {
        return register(id, builder.build());
    }

    private static LuckyEvent register(String id, Supplier<LuckyEvent> supplier) {
        return register(id, supplier.get());
    }

    public static void initialize() {}

    private VanillaLuckyEvents() {}
}
