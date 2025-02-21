package net.ethernity.lucky.command;

import net.ethernity.lucky.server.command.LuckyEventCommand;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class LuckyWillyComands {
    public static void initialize() {
        CommandRegistrationCallback.EVENT.register(LuckyEventCommand::register);
    }
}
