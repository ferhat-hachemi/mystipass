package dev.hachemi.mystipass.util;

import dev.hachemi.mystipass.model.Mystipass;

public class MystipassBuilder {

    public static Mystipass build(String line) {
        var parts = line.split(":");
        return Mystipass
                .builder()
                .key(parts[0])
                .username(parts[1])
                .password(parts[2])
                .build();
    }
}