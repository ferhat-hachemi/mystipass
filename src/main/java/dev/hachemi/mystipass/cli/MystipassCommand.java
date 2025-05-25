package dev.hachemi.mystipass.cli;

import dev.hachemi.mystipass.service.MystipassService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.context.InteractionMode;

import java.io.Console;

@Command
@RequiredArgsConstructor
public class MystipassCommand {

    private final MystipassService service;

    @Command(command = "init", description = "Initialize cli for first-time use", interactionMode = InteractionMode.ALL)
    public String init() {
        System.out.print("Enter master password: ");
        Console console = System.console();
        var read = console.readPassword();
        String master = new String(read);
        System.out.print("Password is: " + master);
        return service.init(master);
    }

    @Command(command = "add", description = "Store a new password entry with its associated key and username", interactionMode = InteractionMode.ALL)
    public String add(
            @Option(description = "A unique key to identify this password entry", shortNames = 'k', longNames = "key", required = true) String key,
            @Option(description = "The username or email linked to this entry", shortNames = 'u', longNames = "username", required = true) String username,
            @Option(description = "The password you want to securely store", shortNames = 'p', longNames = "password", required = true) String password
    ) {
        System.out.print("Enter master password: ");
        Console console = System.console();
        var read = console.readPassword();
        String master = new String(read);
        return service.add(key, username, password, master);
    }

    @Command(command = "list", description = "Display all stored password entries", interactionMode = InteractionMode.ALL)
    public String list() {
        System.out.print("Enter master password: ");
        Console console = System.console();
        var read = console.readPassword();
        String master = new String(read);
        return service.list(master);
    }

    @Command(command = "get", description = "Retrieve a specific password entry by its unique key.", interactionMode = InteractionMode.ALL)
    public String get(@Option(description = "The key used to identify the password entry", shortNames = 'k', longNames = "key", required = true) String key) {
        System.out.print("Enter master password: ");
        Console console = System.console();
        var read = console.readPassword();
        String master = new String(read);
        return service.get(key, master);
    }
}
