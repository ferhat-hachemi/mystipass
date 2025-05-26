package dev.hachemi.mystipass.service;

import dev.hachemi.mystipass.model.Mystipass;
import dev.hachemi.mystipass.security.EncryptionEngine;
import dev.hachemi.mystipass.security.PasswordHelper;
import dev.hachemi.mystipass.util.InitializationChecker;
import dev.hachemi.mystipass.util.MystipassBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static dev.hachemi.mystipass.util.Constant.*;

@Service
@RequiredArgsConstructor
public class MystipassServiceImpl implements MystipassService {

    private final FileService service;
    private final EncryptionEngine engine;
    private final PasswordEncoder encoder;

    @Override
    public String init(String master) {
        return service.init(master);
    }

    @Override
    public String add(String key, String username, String password, String master) {
        if (!InitializationChecker.initialized()) {
            return SHOULD_INIT;
        }
        if (!encoder.matches(master, PasswordHelper.readHash())) {
            return MASTER_PASSWORD;
        }

        try {
            if (service.keyAlreadyExists(key)) {
                return KEY_ENTRY_ALREADY_EXISTS;
            }
        } catch (IOException e) {
            return READ_ERROR;
        }

        try {
            var encrypted = engine.encrypt(password, master);
            Mystipass mystipass = Mystipass
                    .builder()
                    .key(key)
                    .username(username)
                    .password(encrypted)
                    .build();
            return service.write(mystipass);
        } catch (IOException e) {
            return WRITE_ERROR;
        }
    }

    @Override
    public String list(String master) {
        if (!InitializationChecker.initialized()) {
            return SHOULD_INIT;
        }
        if (!encoder.matches(master, PasswordHelper.readHash())) {
            return MASTER_PASSWORD;
        }
        try {
            List<String> all = service.readLines();
            if (all.isEmpty()) return NO_ENTRIES;
            var result = String.join("\n", all);
            return ENTRIES.concat(result);
        } catch (IOException e) {
            return READ_ERROR;
        }
    }

    @Override
    public String get(String key, String master) {
        if (!InitializationChecker.initialized()) {
            return SHOULD_INIT;
        }
        if (!encoder.matches(master, PasswordHelper.readHash())) {
            return MASTER_PASSWORD;
        }
        try {
            var opt = service.read(key);
            if (opt.isPresent()) {
                var object = MystipassBuilder.build(opt.get());
                object.setPassword(engine.decrypt(object.getPassword(), master));
                return ENTRY.concat(object.toString());
            }
            return NO_ENTRY;
        } catch (IOException e) {
            return READ_ERROR;
        }
    }
}