package net.ethernity.lucky.event.vanilla;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.event.LuckyEventPreset;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.ethernity.lucky.world.gen.LuckyWillyFeature;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VanillaLuckyEvents {
    public static final String ID = "vanilla";

    public static final LuckyEvent CHARGED_CREEPER_EVENT = register("charged_creeper_event", new ChargedCreeperBlockEvent());
    public static final LuckyEvent DIAMOND_BLOCK_EVENT = register("diamond_block_event", new DiamondBlockEvent());

    public static final LuckyEvent ANVIL_TRAP_EVENT = register(
            "anvil_trap_event", LuckyEventPreset.placeFeature(-1 , LuckyWillyFeature.ANVIL_TRAP_KEY, true)
    );
    public static final LuckyEvent LAVA_TRAP_EVENT = register(
            "lava_trap_event", LuckyEventPreset.placeFeature(-1 , LuckyWillyFeature.LAVA_TRAP_KEY, true)
    );
    public static final LuckyEvent GIANT_EVENT = register(
            "giant_event", LuckyEventPreset.spawnMob(-1 , EntityType.GIANT)
    );
    public static final LuckyEvent DROP_TNT_EVENT = register(
            "drop_tnt_event", LuckyEventPreset.dropStack(0, Blocks.TNT.asItem().getDefaultStack().copyWithCount(8))
    );
    public static final LuckyEvent FOUR_LUCKY_PYRAMID_EVENT = register(
            "four_lucky_pyramid_event", LuckyEventPreset.placeFeature(0 , LuckyWillyFeature.FOUR_LUCKY_PYRAMID_KEY, false)
    );
    public static final LuckyEvent BEACON_EVENT = register(
            "beacon_event", LuckyEventPreset.dropStack(1, Blocks.BEACON.asItem().getDefaultStack())
    );
    public static final LuckyEvent RANDOM_CHEST_LOOT_EVENT = register(
            "random_chest_loot_event", new RandomChestLootEvent()
    );

    public static final LuckyEvent EXPLOSION_EVENT = register("explosion_event", new ExplosionEvent());
    public static final LuckyEvent BOB_EVENT = register("bob_event", new BobEvent());
    public static final LuckyEvent STONE_KIT_EVENT = register("stone_kit_event", new StoneKitEvent());
    public static final LuckyEvent HORSE_ARMOR_EVENT = register("horse_armor_event", new HorseArmorEvent());
    public static final LuckyEvent GOLDEN_APPLES_EVENT = register("golden_apples_event", new GoldenApplesEvent());
    public static final LuckyEvent LAPIZ_BLOCK_EVENT = register("lapiz_block_event", new LapizBlockEvent());


    private static LuckyEvent register(String id, LuckyEvent event) {
        return Registry.register(LuckyWillyRegistries.LUCKY_EVENT, Identifier.of(LuckyWilly.MOD_ID, ID + "/" + id), event);
    }

    public static void initialize() {}
}
