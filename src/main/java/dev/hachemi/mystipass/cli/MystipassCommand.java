package dev.hachemi.mystipass.cli;

import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.context.InteractionMode;

@Command
public class MystipassCommand {

    @Command(command = "init", description = "Initialize Mystipass for first-time use", interactionMode = InteractionMode.ALL)
    public void init() {
        // TODO
    }

    @Command(command = "add", description = "Store a new password entry with its associated key and username", interactionMode =  InteractionMode.ALL)
    public void add(
            @Option(description = "A unique key to identify this password entry", shortNames = 'k', longNames = "key", required = true) String key,
            @Option(description = "The username or email linked to this entry", shortNames = 'u', longNames = "username", required = true) String username,
            @Option(description = "The password you want to securely store", shortNames = 'p', longNames = "password", required = true) String password
    ) {
       // TODO
    }

    @Command(description = "Display all stored password entries", interactionMode =  InteractionMode.NONINTERACTIVE)
    public void list() {
        // TODO
    }
}
