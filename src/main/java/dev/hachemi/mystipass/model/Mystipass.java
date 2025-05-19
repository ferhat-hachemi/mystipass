package dev.hachemi.mystipass.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mystipass {

    private String key;
    private String username;
    private String password;

    @Override
    public String toString() {
        return key + ":" + username + ":" + password;
    }
}
