package net.ethernity.lucky.data;

import net.ethernity.lucky.LuckyWilly;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntryType;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.function.Consumer;

public class IdentifierLootEntry extends LeafEntry {
    public static final MapCodec<IdentifierLootEntry> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(Identifier.CODEC.fieldOf("name").forGetter(entry -> entry.itemId))
                    .and(addLeafFields(instance))
                    .apply(instance, IdentifierLootEntry::new));

    private final Identifier itemId;

    protected IdentifierLootEntry(Identifier itemId, int weight, int quality, List<LootCondition> conditions, List<LootFunction> functions) {
        super(weight, quality, conditions, functions);
        this.itemId = itemId;
    }

    @Override
    protected void generateLoot(Consumer<ItemStack> lootConsumer, LootContext context) {
        Item item = Registries.ITEM.get(itemId);
        if (item != null)
            lootConsumer.accept(new ItemStack(item));
    }

    @Override
    public LootPoolEntryType getType() {
        return Registries.LOOT_POOL_ENTRY_TYPE.get(Identifier.of(LuckyWilly.MOD_ID, "identifier"));
    }

    public static LeafEntry.Builder<?> builder(Identifier itemId) {
        return builder((weight, quality, conditions, functions) -> new IdentifierLootEntry(itemId, weight, quality, conditions, functions));
    }
}
