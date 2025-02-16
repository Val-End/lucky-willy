package net.ethernity.lucky.data;

import net.ethernity.lucky.blocks.LuckyWilllyBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.*;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class LuckyWillyLootProvider extends FabricBlockLootTableProvider {
    protected LuckyWillyLootProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(LuckyWilllyBlocks.WEAPON_LUCKY_BLOCK, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(Items.IRON_SWORD).weight(1))
                        .with(ItemEntry.builder(Items.DIAMOND_SWORD).weight(1))
                        .with(ItemEntry.builder(Items.NETHERITE_SWORD).weight(1))

                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "iron_dagger")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "diamond_dagger")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "netherite_dagger")).weight(1))

                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "iron_hammer")).weight(6))
                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "diamond_hammer")).weight(6))
                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "netherite_hammer")).weight(6))

                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "iron_club")).weight(6))
                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "diamond_club")).weight(6))
                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "netherite_club")).weight(6))

                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "iron_spear")).weight(5))
                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "diamond_spear")).weight(5))
                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "netherite_spear")).weight(5))

                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "iron_quarterstaff")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "golden_quarterstaff")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "diamond_quarterstaff")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "netherite_quarterstaff")).weight(1))

                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "iron_glaive")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "golden_glaive")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "diamond_glaive")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("basicweapons", "netherite_glaive")).weight(1))

                        .with(ItemEntry.builder(Items.TRIDENT).weight(5))
                        .with(ItemEntry.builder(Items.MACE).weight(5))
                        .with(ItemEntry.builder(Items.BOW).weight(5))
                        .with(ItemEntry.builder(Items.CROSSBOW).weight(5))
                        .with(ItemEntry.builder(Items.ARROW).weight(8).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(16))))
                        .with(ItemEntry.builder(Items.TOTEM_OF_UNDYING).weight(5))

                        .with(IdentifierLootEntry.builder(Identifier.of("moretotems", "explosive_totem_of_undying")).weight(5))
                        .with(IdentifierLootEntry.builder(Identifier.of("moretotems", "stinging_totem_of_undying")).weight(5))
                        .with(IdentifierLootEntry.builder(Identifier.of("moretotems", "teleporting_totem_of_undying")).weight(5))
                        .with(IdentifierLootEntry.builder(Identifier.of("moretotems", "ghastly_totem_of_undying")).weight(5))
                        .with(IdentifierLootEntry.builder(Identifier.of("moretotems", "skeletal_totem_of_undying")).weight(5))
                        .with(IdentifierLootEntry.builder(Identifier.of("moretotems", "rotting_totem_of_undying")).weight(5))

                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_sword")).weight(5))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_sword_nether")).weight(4))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_sword_end")).weight(3))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_sword_sniper")).weight(3))

                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_arrow")).weight(5).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(8))))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_arrow_nether")).weight(4).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(8))))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_arrow_end")).weight(3).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(8))))

                        .apply(EnchantWithLevelsLootFunction.builder(this.registryLookup, UniformLootNumberProvider.create(0.0F, 9.0F)))
                )
        );

        addDrop(LuckyWilllyBlocks.ARMOR_LUCKY_BLOCK, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(Items.IRON_HELMET).weight(6))
                        .with(ItemEntry.builder(Items.IRON_CHESTPLATE).weight(6))
                        .with(ItemEntry.builder(Items.IRON_LEGGINGS).weight(6))
                        .with(ItemEntry.builder(Items.IRON_BOOTS).weight(6))

                        .with(ItemEntry.builder(Items.DIAMOND_HELMET).weight(6))
                        .with(ItemEntry.builder(Items.DIAMOND_CHESTPLATE).weight(6))
                        .with(ItemEntry.builder(Items.DIAMOND_LEGGINGS).weight(6))
                        .with(ItemEntry.builder(Items.DIAMOND_BOOTS).weight(6))

                        .with(ItemEntry.builder(Items.NETHERITE_HELMET).weight(6))
                        .with(ItemEntry.builder(Items.NETHERITE_CHESTPLATE).weight(6))
                        .with(ItemEntry.builder(Items.NETHERITE_LEGGINGS).weight(6))
                        .with(ItemEntry.builder(Items.NETHERITE_BOOTS).weight(6))

                        .with(IdentifierLootEntry.builder(Identifier.of("dragonloot", "dragon_helmet")).weight(6))
                        .with(IdentifierLootEntry.builder(Identifier.of("dragonloot", "upgraded_dragon_chestplate")).weight(6))
                        .with(IdentifierLootEntry.builder(Identifier.of("dragonloot", "dragon_leggings")).weight(6))
                        .with(IdentifierLootEntry.builder(Identifier.of("dragonloot", "dragon_boots")).weight(6))

                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_helmet")).weight(5))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_chestplate")).weight(5))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_leggings")).weight(5))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_boots")).weight(5))

                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_helmet_nether")).weight(4))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_chestplate_nether")).weight(4))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_leggings_nether")).weight(4))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_boots_nether")).weight(4))

                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_helmet_end")).weight(3))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_chestplate_end")).weight(3))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_leggings_end")).weight(3))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_boots_end")).weight(3))

                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_helmet_dragon")).weight(2))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_chestplate_dragon")).weight(2))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_leggings_dragon")).weight(2))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_boots_dragon")).weight(2))

                        .apply(EnchantWithLevelsLootFunction.builder(this.registryLookup, UniformLootNumberProvider.create(0.0F, 9.0F)))
                ));

        addDrop(LuckyWilllyBlocks.WILLY_LUCKY_BLOCK, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_helmet")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_chestplate")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_leggings")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_boots")).weight(1))

                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_helmet_nether")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_chestplate_nether")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_leggings_nether")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_boots_nether")).weight(1))

                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_helmet_end")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_chestplate_end")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_leggings_end")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_boots_end")).weight(1))

                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_helmet_dragon")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_chestplate_dragon")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_leggings_dragon")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_boots_dragon")).weight(1))

                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_sword")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_sword_nether")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_sword_end")).weight(1))
                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_sword_sniper")).weight(1))

                        .with(IdentifierLootEntry.builder(Identifier.of("gobber2", "gobber2_staff_sound")).weight(1))

                        .apply(EnchantWithLevelsLootFunction.builder(this.registryLookup, UniformLootNumberProvider.create(20.0F, 39.0F)))
        ));
    }
}
