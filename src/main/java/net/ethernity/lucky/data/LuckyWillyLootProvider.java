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
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        addDrop(LuckyWilllyBlocks.WEAPON_LUCKY_BLOCK, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(EmptyEntry.builder())
                ));

        addDrop(LuckyWilllyBlocks.ARMOR_LUCKY_BLOCK, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(Items.GOLDEN_BOOTS).weight(1))
                        .with(ItemEntry.builder(Items.GOLDEN_LEGGINGS).weight(1))
                        .with(ItemEntry.builder(Items.GOLDEN_CHESTPLATE).weight(1))
                        .with(ItemEntry.builder(Items.GOLDEN_HELMET).weight(1))
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
