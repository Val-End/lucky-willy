package net.ethernity.lucky.block;

import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.event.LuckyEvents;

import java.util.List;

public class LuckyBlock extends AbstractLuckyBlock {
    @Override
    protected List<LuckyEvent> getEvents() {
        return List.of(
                LuckyEvents.LUCKY_PYRAMID_EVENT,
                LuckyEvents.ANVIL_TRAP_EVENT,
                LuckyEvents.LAVA_TRAP_EVENT,
                LuckyEvents.CHARGED_CREEPER_EVENT,
                LuckyEvents.DIAMOND_BLOCK_EVENT,
                LuckyEvents.EXPLOSION_EVENT
        );
    }
}
