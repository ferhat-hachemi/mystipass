package dev.hachemi.mystipass.util;

import java.io.File;

public class Constant {
    public static final String USER_WORKING_DIRECTORY = System.getProperty("user.home");
    public static final String MYSTIPASS_HIDDEN_FOLDER = USER_WORKING_DIRECTORY + File.separator + ".mystipass";
    public static final String CREDENTIALS_FILE_PATH = MYSTIPASS_HIDDEN_FOLDER + File.separator + "credentials";
    public static final String INIT_ALREADY_EXISTS = "MystiPass structure is already initialized.";
    public static final String INIT_SUCCESS = "MystiPass structure initialized successfully.";
    public static final String INIT_ERROR = "Failed to create the MystiPass structure.";
    public static final String INIT_FILE_EXCEPTION = "An error occurred while creating the credentials file";
    public static final String WRITE_ERROR = "An error occurred while writing your credentials.";
    public static final String SHOULD_INIT = "Please use the 'init' command to initialize the cli structure!";
    public static final String READ_ERROR = "An error occurred while reading entries.";
    public static final String NO_ENTRY = "No entry found for the given key.";
    public static final String KEY_ENTRY_ALREADY_EXISTS = "An entry with that key already exists.";
}