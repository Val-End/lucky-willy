package net.ethernity.lucky;

import net.ethernity.lucky.blocks.LuckyWilllyBlocks;
import net.ethernity.lucky.blocks.events.LuckyEvents;
import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.ethernity.lucky.items.LuckyWillyItems;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.ethernity.lucky.world.gen.feature.LuckyWillyFeature;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuckyWilly implements ModInitializer {
	public static final String MOD_ID = "lucky-willy";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LuckyWillyRegistries.initialize();
		LuckyEvents.initialize();
		LuckyWilllyBlocks.initialize();
		LuckyWillyEntities.initialize();
		LuckyWillyItems.initialize();
		LuckyWillyFeature.initialize();
	}
}