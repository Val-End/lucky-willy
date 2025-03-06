package net.ethernity.lucky;

import net.ethernity.lucky.block.LuckyWilllyBlocks;
import net.ethernity.lucky.command.LuckyWillyComands;
import net.ethernity.lucky.component.LuckyDataComponentTypes;
import net.ethernity.lucky.entity.LuckyWillyEntities;
import net.ethernity.lucky.entity.effect.LuckyWillyEffects;
import net.ethernity.lucky.event.LuckyEvents;
import net.ethernity.lucky.items.LuckyWillyItems;
import net.ethernity.lucky.network.LuckyWillyNetwork;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.ethernity.lucky.server.LuckyWillyServer;
import net.ethernity.lucky.world.gen.LuckyWillyFeature;
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
        LuckyDataComponentTypes.initialize();
        LuckyWilllyBlocks.initialize();
        LuckyWillyEntities.initialize();
        LuckyWillyItems.initialize();
        LuckyWillyFeature.initialize();
        LuckyWillyComands.initialize();
        LuckyWillyEffects.initialize();
        LuckyWillyServer.initialize();
        LuckyWillyNetwork.initialize();
    }
}
