package dev.hachemi.mystipass.service;

import dev.hachemi.mystipass.model.Mystipass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MystipassServiceImpl implements MystipassService {

    private final FileService service;

    @Override
    public String init() {
        return service.init();
    }

    @Override
    public String add(String key, String username, String password) {
        Mystipass mystipass = Mystipass
                .builder()
                .key(key)
                .username(username)
                .password(password)
                .build();
        return service.write(mystipass);
    }

    @Override
    public void list() {

    }
}
