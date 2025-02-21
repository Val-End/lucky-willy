package net.ethernity.lucky.command.argument;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.registry.LuckyWillyRegistryKeys;
import net.minecraft.command.argument.RegistryEntryReferenceArgumentType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class LuckyWillyArgumentType {
    private static final DynamicCommandExceptionType NOT_EXECUTABLE_EXCEPTION = new DynamicCommandExceptionType(
            id -> Text.stringifiedTranslatable("event.not_executable", id)
    );

    public static RegistryEntry.Reference<LuckyEvent> getExecutableEvent(CommandContext<ServerCommandSource> context, String name) throws CommandSyntaxException {
        RegistryEntry.Reference<LuckyEvent> reference = RegistryEntryReferenceArgumentType.getRegistryEntry(context, name, LuckyWillyRegistryKeys.LUCKY_EVENT);
        if (!reference.value().isExecutable())
            throw NOT_EXECUTABLE_EXCEPTION.create(reference.registryKey().getValue().toString());
        else
            return reference;
    }
}
