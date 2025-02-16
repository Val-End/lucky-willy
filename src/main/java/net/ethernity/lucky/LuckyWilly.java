package net.ethernity.lucky;

import net.ethernity.lucky.blocks.LuckyWilllyBlocks;
import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.ethernity.lucky.items.LuckyWillyItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuckyWilly implements ModInitializer {
	public static final String MOD_ID = "lucky-willy";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LuckyWilllyBlocks.initialize();
		LuckyWillyEntities.initialize();
		LuckyWillyItems.initialize();
	}
}