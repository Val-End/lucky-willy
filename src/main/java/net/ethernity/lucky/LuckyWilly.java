package net.ethernity.lucky;

import net.ethernity.lucky.block.LuckyWilllyBlocks;
import net.ethernity.lucky.command.LuckyWillyComands;
import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.ethernity.lucky.event.LuckyEvents;
import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.ethernity.lucky.items.LuckyWillyItems;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.ethernity.lucky.world.gen.LuckyWillyFeature;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LuckyWilly implements ModInitializer {
	public static final String MOD_ID = "lucky-willy";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

	@Override
	public void onInitialize() {
		LuckyWillyRegistries.initialize();
		LuckyEvents.initialize();
		LuckyWilllyBlocks.initialize();
		LuckyWillyEntities.initialize();
		LuckyWillyItems.initialize();
		LuckyWillyFeature.initialize();
		LuckyWillyComands.initialize();
		LuckyWillyEffects.initialize();

		ServerTickEvents.END_SERVER_TICK.register(this::tick);
	}

	public static void queueServerWork(int tick, Runnable action) {
		workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
	}

	private void tick(MinecraftServer server) {
		List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
		workQueue.forEach(work -> {
			work.setValue(work.getValue() - 1);
			if (work.getValue() == 0)
				actions.add(work);
		});
		actions.forEach(e -> e.getKey().run());
		workQueue.removeAll(actions);
	}
}
