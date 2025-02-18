package net.ethernity.lucky.data;

import net.ethernity.lucky.blocks.LuckyWilllyBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class LuckyWillyLootProvider extends FabricBlockLootTableProvider {
    protected LuckyWillyLootProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(LuckyWilllyBlocks.WEAPON_LUCKY_BLOCK, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .apply(EnchantWithLevelsLootFunction.builder(this.registryLookup, UniformLootNumberProvider.create(0.0F, 9.0F)))
                )
        );

        addDrop(LuckyWilllyBlocks.ARMOR_LUCKY_BLOCK, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .apply(EnchantWithLevelsLootFunction.builder(this.registryLookup, UniformLootNumberProvider.create(0.0F, 9.0F)))
                ));

        addDrop(LuckyWilllyBlocks.WILLY_LUCKY_BLOCK, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .apply(EnchantWithLevelsLootFunction.builder(this.registryLookup, UniformLootNumberProvider.create(20.0F, 39.0F)))
        ));
    }
}
