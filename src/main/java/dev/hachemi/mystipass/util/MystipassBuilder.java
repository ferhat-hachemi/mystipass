package dev.hachemi.mystipass.util;

import dev.hachemi.mystipass.model.Mystipass;

public class MystipassBuilder {

    public static Mystipass build(String line) {
        return Mystipass
                .builder()
                .key(line.split(":")[0])
                .username(line.split(":")[1])
                .password(line.split(":")[2])
                .build();
    }
}