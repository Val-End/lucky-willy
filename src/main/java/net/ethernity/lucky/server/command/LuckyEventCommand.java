package net.ethernity.lucky.server.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.ethernity.lucky.command.argument.LuckyWillyArgumentType;
import net.ethernity.lucky.command.suggestion.LuckyWillySuggestionProvider;
import net.ethernity.lucky.event.LuckyEvent;
import net.ethernity.lucky.registry.LuckyWillyRegistryKeys;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.RegistryEntryReferenceArgumentType;
import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.Collections;

public class LuckyEventCommand {
    private static final SimpleCommandExceptionType INVALID_POSITION_EXCEPTION = new SimpleCommandExceptionType(
            Text.translatable("commands.lucky-event.invalidPosition")
    );

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, CommandManager.RegistrationEnvironment environment){
        dispatcher.register(
            CommandManager.literal("lucky-event")
            .requires(source -> source.hasPermissionLevel(2))
            .then(
                CommandManager.argument("event", RegistryEntryReferenceArgumentType.registryEntry(access, LuckyWillyRegistryKeys.LUCKY_EVENT))
                .suggests(LuckyWillySuggestionProvider.EXECUTABLE_EVENTS)
                .executes(
                    context -> execute(
                        context.getSource(),
                        LuckyWillyArgumentType.getExecutableEvent(context, "event"),
                        context.getSource().getPosition(),
                        Collections.singleton(context.getSource().getPlayerOrThrow())
                    )
                )
                .then(CommandManager.argument("pos", Vec3ArgumentType.vec3())
                    .executes(
                        context -> execute(
                            context.getSource(),
                            LuckyWillyArgumentType.getExecutableEvent(context, "event"),
                            Vec3ArgumentType.getVec3(context, "pos"),
                            Collections.singleton(context.getSource().getPlayerOrThrow())
                        )
                    )
                    .then(CommandManager.argument("target", EntityArgumentType.players())
                        .executes(
                            context -> execute(
                                context.getSource(),
                                LuckyWillyArgumentType.getExecutableEvent(context, "event"),
                                Vec3ArgumentType.getVec3(context, "pos"),
                                EntityArgumentType.getPlayers(context, "target")
                            )
                        )
                    )
                )
            )
        );
    }


    private static int execute(ServerCommandSource source, RegistryEntry.Reference<LuckyEvent> event, Vec3d pos, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {
        BlockPos blockPos = BlockPos.ofFloored(pos);
        if (!World.isValid(blockPos))
            throw INVALID_POSITION_EXCEPTION.create();

        for (ServerPlayerEntity entity : targets) {
            event.value().execute(blockPos, source.getWorld(), source.getPlayer());
            source.sendFeedback(() -> Text.translatable("commands.lucky-event.success", entity.getDisplayName()), true);
        }

        return 1;
    }
}
