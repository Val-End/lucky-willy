package net.ethernity.lucky.block;

import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.event.LuckyEvents;

import java.util.List;

public class WillyLuckyBlock extends AbstractLuckyBlock {
    @Override
    protected List<LuckyEvent> getEvents() {
        return List.of(
                LuckyEvents.TROTUMAN_EVENT
        );
    }
}
