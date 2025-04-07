package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for loading and accessing configuration properties.
 * This class provides a central access point for configuration values
 * stored in properties files.
 */
public class ConfigProperties {
    private static final Logger LOGGER = Logger.getLogger(ConfigProperties.class.getName());
    private static final String DEFAULT_CONFIG_FILE = "config.properties";
    private static final Properties properties = new Properties();
    private static boolean isInitialized = false;
    
    /**
     * Initialize the properties by loading the default config file.
     * This method is automatically called when the class is loaded.
     */
    static {
        init();
    }
    
    /**
     * Initialize the properties by loading the default config file.
     */
    public static void init() {
        loadProperties(DEFAULT_CONFIG_FILE);
    }
    
    /**
     * Load properties from a specific file.
     * 
     * @param fileName The name of the file to load
     */
    public static void loadProperties(String fileName) {
        try (InputStream input = ConfigProperties.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                LOGGER.warning("Unable to find " + fileName);
                return;
            }
            
            properties.load(input);
            isInitialized = true;
            LOGGER.info("Loaded configuration from " + fileName);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading properties file: " + fileName, e);
        }
    }
    
    /**
     * Get a property value as a string.
     * 
     * @param key The property key
     * @return The property value, or null if not found
     */
    public static String getProperty(String key) {
        if (!isInitialized) {
            init();
        }
        
        String value = properties.getProperty(key);
        if (value == null) {
            // Check if a system property with the same key exists
            value = System.getProperty(key);
        }
        return value;
    }
    
    /**
     * Get a property value as a string, with a default value if not found.
     * 
     * @param key The property key
     * @param defaultValue The default value to return if the key is not found
     * @return The property value, or the default value if not found
     */
    public static String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return value != null ? value : defaultValue;
    }
    
    /**
     * Get a property value as an integer.
     * 
     * @param key The property key
     * @param defaultValue The default value to return if the key is not found or not a valid integer
     * @return The property value as an integer, or the default value if not found or not a valid integer
     */
    public static int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            LOGGER.warning("Property " + key + " is not a valid integer: " + value);
            return defaultValue;
        }
    }
    
    /**
     * Get a property value as a boolean.
     * 
     * @param key The property key
     * @param defaultValue The default value to return if the key is not found
     * @return The property value as a boolean, or the default value if not found
     */
    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        
        return Boolean.parseBoolean(value);
    }
    
    /**
     * Set a property value.
     * 
     * @param key The property key
     * @param value The property value
     */
    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
} 