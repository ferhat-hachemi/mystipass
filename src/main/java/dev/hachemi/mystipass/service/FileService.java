package dev.hachemi.mystipass.service;

import dev.hachemi.mystipass.model.Mystipass;
import dev.hachemi.mystipass.security.PasswordHelper;
import dev.hachemi.mystipass.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;
import java.util.Optional;

import static dev.hachemi.mystipass.util.Constant.*;

@Service
@RequiredArgsConstructor
public class FileService {

    private final PasswordEncoder encoder;

    public String init(String password) {
        if (FileUtils.initialized()) {
            return INIT_ALREADY_EXISTS;
        }
        File dir = new File(MYSTIPASS_HIDDEN_FOLDER);
        File file = new File(CREDENTIALS_FILE);
        File config = new File(CONFIG_FILE);
        var salt = PasswordHelper.generateSalt();
        try {
            boolean dirCreated = dir.mkdir();
            boolean fileCreated = file.createNewFile();
            boolean configCreated = config.createNewFile();

            if (dirCreated && fileCreated && configCreated) {
                FileWriter fw = new FileWriter(config, true);
                BufferedWriter writer = new BufferedWriter(fw);
                writer.append(encoder.encode(password).concat(":").concat(String.valueOf(salt)));
                Files.setPosixFilePermissions(file.toPath(), PosixFilePermissions.fromString(PERMISSIONS));
                Files.setPosixFilePermissions(config.toPath(), PosixFilePermissions.fromString(PERMISSIONS));
                writer.close();
                return INIT_SUCCESS;
            } else {
                return INIT_ERROR;
            }
        } catch (IOException e) {
            return EXCEPTION;
        }
    }

    public String write(Mystipass mystipass) throws IOException {
        File file = new File(CREDENTIALS_FILE);
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter writer = new BufferedWriter(fw);
        writer.append(mystipass.toString());
        writer.close();
        return String.format(ENTRY_ADDED, mystipass.getKey());
    }

    public List<String> readLines() throws IOException {
        File file = new File(CREDENTIALS_FILE);
        FileReader fr = new FileReader(file);
        BufferedReader writer = new BufferedReader(fr);
        return writer.lines().toList();
    }

    public Optional<String> read(String key) throws IOException {
        File file = new File(CREDENTIALS_FILE);
        FileReader fr = new FileReader(file);
        BufferedReader writer = new BufferedReader(fr);
        return writer.lines().filter(line -> line.split(":")[0].equals(key)).findFirst();
    }

    public boolean keyAlreadyExists(String key) throws IOException {
        return read(key).isPresent();
    }
}
