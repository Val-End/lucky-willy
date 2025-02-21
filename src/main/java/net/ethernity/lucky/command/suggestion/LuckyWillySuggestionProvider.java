package net.ethernity.lucky.command.suggestion;

import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.event.LuckyEvents;
import net.ethernity.lucky.registry.LuckyWillyRegistries;
import net.minecraft.command.CommandSource;
import net.minecraft.command.suggestion.SuggestionProviders;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class LuckyWillySuggestionProvider extends SuggestionProviders {
    public static final SuggestionProvider<ServerCommandSource> EXECUTABLE_EVENTS = register(
            Identifier.of(LuckyWilly.MOD_ID, "executable_events"),
            (context, builder) -> CommandSource.suggestFromIdentifier(
                    LuckyWillyRegistries.LUCKY_EVENT.stream().filter(LuckyEvent::isExecutable),
                    builder,
                    LuckyEvents::getId,
                    event -> Text.translatable(Util.createTranslationKey("entity", LuckyEvents.getId(event)))
            )
    );
}
