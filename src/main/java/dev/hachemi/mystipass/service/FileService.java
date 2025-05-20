package dev.hachemi.mystipass.service;

import dev.hachemi.mystipass.model.Mystipass;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static dev.hachemi.mystipass.util.Constant.*;

@Service
public class FileService {

    public String init() {
        File dir = new File(MYSTIPASS_HIDDEN_FOLDER);
        File file = new File(CREDENTIALS_FILE_PATH);

        try {
            if (file.exists() && dir.exists()) {
                return INIT_ALREADY_EXISTS;
            }
            boolean dirCreated = dir.mkdir();
            boolean fileCreated = file.createNewFile();

            if (dirCreated && fileCreated) {
                return INIT_SUCCESS;
            } else {
                return INIT_ERROR;
            }
        } catch (IOException e) {
            return INIT_FILE_EXCEPTION;
        }
    }

    public String write(Mystipass mystipass) {
        File file = new File(CREDENTIALS_FILE_PATH);
        if (file.exists()) {
            try (
                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter writer = new BufferedWriter(fw)
            ) {
                writer.write(mystipass.toString());
                return String.format("Entry added successfully for %s.", mystipass.getKey());
            } catch (IOException e) {
                return WRITE_ERROR;
            }
        } else {
            return SHOULD_INIT;
        }
    }
}
