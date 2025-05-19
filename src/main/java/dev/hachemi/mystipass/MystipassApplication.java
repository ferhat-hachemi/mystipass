package dev.hachemi.mystipass;

import dev.hachemi.mystipass.cli.MystipassCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.EnableCommand;

@SpringBootApplication
@EnableCommand(MystipassCommand.class)
public class MystipassApplication {

	public static void main(String[] args) {
		SpringApplication.run(MystipassApplication.class, args);
	}

}
