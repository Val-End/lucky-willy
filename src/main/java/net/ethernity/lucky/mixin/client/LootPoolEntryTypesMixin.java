package net.ethernity.lucky.mixin.client;

import com.mojang.serialization.MapCodec;
import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.data.IdentifierLootEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootPoolEntryType;
import net.minecraft.loot.entry.LootPoolEntryTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LootPoolEntryTypes.class)
public class LootPoolEntryTypesMixin {
	@Inject(at = @At("HEAD"), method = "register", cancellable = true)
	private static void register(String id, MapCodec<? extends LootPoolEntry> codec, CallbackInfoReturnable<LootPoolEntryType> cir) {
		if(id.equals("item")) {
			Registry.register(Registries.LOOT_POOL_ENTRY_TYPE, Identifier.of(LuckyWilly.MOD_ID, "identifier"), new LootPoolEntryType(IdentifierLootEntry.CODEC));
			LuckyWilly.LOGGER.info("FWAE");
		}

		cir.setReturnValue(Registry.register(Registries.LOOT_POOL_ENTRY_TYPE, Identifier.ofVanilla(id), new LootPoolEntryType(codec)));
	}
}