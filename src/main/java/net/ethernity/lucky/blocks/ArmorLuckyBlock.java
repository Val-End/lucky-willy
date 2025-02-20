package net.ethernity.lucky.blocks;

import net.ethernity.lucky.blocks.events.LuckyEvent;
import net.ethernity.lucky.blocks.events.LuckyEvents;

import java.util.List;

public class ArmorLuckyBlock extends AbstractLuckyBlock {
    @Override
    protected List<LuckyEvent> getEvents() {
        return List.of(
                LuckyEvents.ANVIL_TRAP_EVENT,
                LuckyEvents.LAVA_TRAP_EVENT
        );
    }
}
