package dev.hachemi.mystipass.service;

public interface MystipassService {

    /**
     * Initializes the credential storage or any required setup.
     */
    void init();

    /**
     * Adds a new credential entry with the given key, username, and password.
     * @param key
     * @param username
     * @param password
     */
    void add(String key, String username, String password);

    /**
     * Lists all stored entries
     */
    void list();
}
