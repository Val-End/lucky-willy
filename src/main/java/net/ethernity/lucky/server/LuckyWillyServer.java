package net.ethernity.lucky.server;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LuckyWillyServer {
    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public static void initialize() {
        ServerTickEvents.END_SERVER_TICK.register(LuckyWillyServer::tick);
    }

    public static void queueWork(int tick, Runnable action) {
        LuckyWillyServer.workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
    }

    private static void tick(MinecraftServer server) {
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
