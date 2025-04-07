package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Utility class for loading and accessing properties from config files.
 */
public class ConfigProperties {
    private static final Logger LOGGER = Logger.getLogger(ConfigProperties.class.getName());
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "config.properties";
    private static boolean isLoaded = false;
    
    /**
     * Load properties from the config file.
     */
    private static void loadProperties() {
        if (isLoaded) {
            return;
        }
        
        try (InputStream input = ConfigProperties.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                LOGGER.warning("Unable to find " + CONFIG_FILE);
                return;
            }
            
            properties.load(input);
            isLoaded = true;
            LOGGER.info("Loaded configuration from " + CONFIG_FILE);
        } catch (IOException ex) {
            LOGGER.severe("Error loading properties file: " + ex.getMessage());
        }
    }
    
    /**
     * Get property value with a default value if not found.
     * 
     * @param key Property key
     * @param defaultValue Default value if property not found
     * @return Property value or default value
     */
    public static String getProperty(String key, String defaultValue) {
        loadProperties();
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Get property value.
     * 
     * @param key Property key
     * @return Property value or null if not found
     */
    public static String getProperty(String key) {
        return getProperty(key, null);
    }
    
    /**
     * Get boolean property value.
     * 
     * @param key Property key
     * @param defaultValue Default value if property not found
     * @return Boolean property value
     */
    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key);
        return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
    }
    
    /**
     * Get integer property value.
     * 
     * @param key Property key
     * @param defaultValue Default value if property not found
     * @return Integer property value
     */
    public static int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key);
        try {
            return (value != null) ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            LOGGER.warning("Invalid integer property value for " + key + ": " + value);
            return defaultValue;
        }
    }
} 