package dev.hachemi.mystipass.security;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static dev.hachemi.mystipass.util.Constant.CONFIG_FILE;
import static dev.hachemi.mystipass.util.Constant.READ_ERROR;

public class PasswordHelper {

    public static String readHash() {
        return readConfig().split(":")[0];
    }

    public static String readSalt() {
        return readConfig().split(":")[1];
    }

    private static String readConfig() {
        File file = new File(CONFIG_FILE);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            var line = reader.lines().toList().getFirst();
            reader.close();
            return line;
        } catch (IOException e) {
            return READ_ERROR;
        }
    }
}
