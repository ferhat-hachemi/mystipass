package dev.hachemi.mystipass.service;

public interface MystipassService {

    /**
     * Initializes the credential storage or any required setup.
     */
    String init();

    /**
     * Adds a new credential entry with the given key, username, and password.
     * @param key
     * @param username
     * @param password
     */
    String add(String key, String username, String password);

    /**
     * Lists all stored entries
     */
    String list();

    /**
     * Retreive entires with the given key
     * @param key
     * @return
     */
    String get(String key);
}
