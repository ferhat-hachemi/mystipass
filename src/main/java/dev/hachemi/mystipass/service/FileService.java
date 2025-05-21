package dev.hachemi.mystipass.service;

import dev.hachemi.mystipass.model.Mystipass;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.stream.Collectors;

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
        if (!findEntryByKey(mystipass.getKey()).isEmpty()) {
            return KEY_ENTRY_ALREADY_EXISTS;
        }
        return writeToFile(mystipass.toString(), CREDENTIALS_FILE_PATH, String.format("Entry added successfully for %s.", mystipass.getKey()));
    }

    public String readLines() {
        File file = new File(CREDENTIALS_FILE_PATH);
        if (file.exists()) {
            try (
                    FileReader fr = new FileReader(file);
                    BufferedReader writer = new BufferedReader(fr)
            ) {
                return writer.lines().collect(Collectors.joining("\n"));
            } catch (IOException e) {
                return READ_ERROR;
            }
        } else {
            return SHOULD_INIT;
        }
    }

    public String read(String key) {
        File file = new File(CREDENTIALS_FILE_PATH);
        if (file.exists()) {
            try (
                    FileReader fr = new FileReader(file);
                    BufferedReader writer = new BufferedReader(fr)
            ) {
                String all = writer.lines().filter(line -> line.split(":")[0].equals(key)).findFirst().orElse(NO_ENTRY);
                if (all.isEmpty()) return NO_ENTRY;
                return all;
            } catch (IOException e) {
                return READ_ERROR;
            }
        } else {
            return SHOULD_INIT;
        }
    }

    private String findEntryByKey(String key) {
        File file = new File(CREDENTIALS_FILE_PATH);
        try (
                FileReader fr = new FileReader(file);
                BufferedReader writer = new BufferedReader(fr)
        ) {
            return writer.lines().filter(line -> line.split(":")[0].equals(key)).findFirst().orElse("");
        } catch (IOException e) {
            return READ_ERROR;
        }
    }

    private String writeToFile(String content, String filePath, String message) {
        File file = new File(filePath);
        if (file.exists()) {
            try (
                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter writer = new BufferedWriter(fw)
            ) {
                writer.append(content);
                return message;
            } catch (IOException e) {
                return WRITE_ERROR;
            }
        } else {
            return SHOULD_INIT;
        }
    }
}
