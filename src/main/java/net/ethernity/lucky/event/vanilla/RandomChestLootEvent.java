package net.ethernity.lucky.event.vanilla;

import net.ethernity.lucky.event.LuckyEvent;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class RandomChestLootEvent extends LuckyEvent {
    public RandomChestLootEvent() {
        super(0);
    }

    @Override
    public void execute(BlockPos pos, World world, PlayerEntity player) {
        world.setBlockState(pos, Blocks.CHEST.getDefaultState());
        ChestBlockEntity chest = (ChestBlockEntity) world.getBlockEntity(pos);
        if (chest == null)
            return;

        List<RegistryKey<LootTable>> lootTableList = LootTables.getAll()
                .stream()
                .filter(key -> key.getValue().getPath().startsWith("chests"))
                .toList();

        if (lootTableList.isEmpty())
            return;

        chest.setLootTable(lootTableList.get(world.random.nextInt(lootTableList.size())));
    }
}
