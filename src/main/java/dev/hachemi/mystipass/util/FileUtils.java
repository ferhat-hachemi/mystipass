package dev.hachemi.mystipass.util;

import java.io.File;

import static dev.hachemi.mystipass.util.Constant.CONFIG_FILE;
import static dev.hachemi.mystipass.util.Constant.CREDENTIALS_FILE;

public class FileUtils {

    public static boolean initialized() {
        File config = new File(CONFIG_FILE);
        File credential = new File(CREDENTIALS_FILE);
        return config.exists() && credential.exists();
    }
}
