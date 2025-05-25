package dev.hachemi.mystipass.util;

import java.io.File;

public class Constant {
    public static final String USER_WORKING_DIRECTORY = System.getProperty("user.home");
    public static final String MYSTIPASS_HIDDEN_FOLDER = USER_WORKING_DIRECTORY + File.separator + ".mystipass";
    public static final String CREDENTIALS_FILE = MYSTIPASS_HIDDEN_FOLDER + File.separator + "credentials";
    public static final String CONFIG_FILE = MYSTIPASS_HIDDEN_FOLDER + File.separator + "mystipass.conf";
    public static final String PERMISSIONS = "rw-------";
    public static final String INIT_ALREADY_EXISTS = "MystiPass structure is already initialized.";
    public static final String INIT_SUCCESS = "MystiPass structure initialized successfully.";
    public static final String INIT_ERROR = "Failed to create the MystiPass structure.";
    public static final String SHOULD_INIT = "Please use the 'init' command to initialize the cli structure!";
    public static final String EXCEPTION = "An exception occurred while initializing the MystiPass structure.";
    public static final String WRITE_ERROR = "An error occurred while writing to the file.";
    public static final String READ_ERROR = "An error occurred while reading the file.";
    public static final String NO_ENTRY = "No entry found for the given key.";
    public static final String NO_ENTRIES = "No entries yet. Start by adding your first one!";
    public static final String KEY_ENTRY_ALREADY_EXISTS = "An entry with that key already exists.";
    public static final String CRYPTO_ALGORITHM = "AES";
    public static final String KEY_DERIVATION_ALGORITHM = "PBKDF2WithHmacSHA256";
    public static final String MASTER_PASSWORD = "The master password is incorrect. Please try again";
    public static final String ENTRY_ADDED =  "✅  Password entry for '%s' added successfully.";
    public static final String ENTRY =
            """
            ✅  Password entry
            -------------------------------------------------------------------------------
            """;
    public static final String ENTRIES =
            """
            🔐 Stored Password Entries
            (Use the 'get -k <key>' command to view a specific entry)
            -------------------------------------------------------------------------------
            """;

}