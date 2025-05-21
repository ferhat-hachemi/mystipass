package dev.hachemi.mystipass.service;

/**
 * Interface for managing mystipass cli operations.
 */
public interface MystipassService {


    /**
     * Initializes the credential storage or any required setup.
     * @return A message indicating the result of the initialization.
     */
    String init();

    /**
     * Adds a new credential entry with the given key, username, and password.
     * @param key A unique key to identify this password entry
     * @param username The username or email linked to this entry
     * @param password The password you want to securely store
     * @return A message indicating the result of the operation.
     */
    String add(String key, String username, String password);

    /**
     * Lists all stored entries
     * @return A formatted string containing all stored entries.
     */
    String list();

    /**
     * Retrieve entry with the given key
     * @param key The key used to identify the password entry
     * @return The entry information as a formatted string, or an error message if not found.
     */
    String get(String key);
}
