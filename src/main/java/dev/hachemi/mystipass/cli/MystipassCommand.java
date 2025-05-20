package dev.hachemi.mystipass.cli;

import dev.hachemi.mystipass.service.MystipassService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.context.InteractionMode;

@Command
@RequiredArgsConstructor
public class MystipassCommand {

    private final MystipassService service;

    @Command(command = "init", description = "Initialize cli for first-time use", interactionMode = InteractionMode.ALL)
    public String init() {
        return service.init();
    }

    @Command(command = "add", description = "Store a new password entry with its associated key and username", interactionMode = InteractionMode.ALL)
    public String add(
            @Option(description = "A unique key to identify this password entry", shortNames = 'k', longNames = "key", required = true) String key,
            @Option(description = "The username or email linked to this entry", shortNames = 'u', longNames = "username", required = true) String username,
            @Option(description = "The password you want to securely store", shortNames = 'p', longNames = "password", required = true) String password
    ) {
        return service.add(key, username, password);
    }

    @Command(command = "list", description = "Display all stored password entries", interactionMode = InteractionMode.ALL)
    public String list() {
        return service.list();
    }

    @Command(command = "get", description = "Retrieve a specific password entry by its unique key.", interactionMode = InteractionMode.ALL)
    public String get(@Option(description = "The key used to identify the password entry", shortNames = 'k', longNames = "key", required = true) String key) {
        return service.get(key);
    }
}
