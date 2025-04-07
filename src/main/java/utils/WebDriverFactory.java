package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.logging.Logger;

/**
 * WebDriverFactory is responsible for creating new WebDriver instances based on the browser type.
 * 
 * This factory class:
 * - Creates different types of WebDriver instances (Chrome, Firefox)
 * - Uses WebDriverManager to automatically download and configure browser drivers
 * - Selects browser type based on the "browser" system property
 * - Uses Chrome as the default browser if no browser is specified
 * 
 * Usage example:
 * WebDriver driver = WebDriverFactory.createWebDriver();
 * 
 * With system property:
 * mvn test -Dbrowser=firefox
 */
public class WebDriverFactory {
    private static final Logger LOGGER = Logger.getLogger(WebDriverFactory.class.getName());
    
    /**
     * Creates and returns a new WebDriver instance based on browser system property.
     * 
     * @return WebDriver instance for the requested browser
     * @throws RuntimeException if an unsupported browser is specified
     */
    public static WebDriver createWebDriver() {
        String webdriver = System.getProperty("browser", "chrome");
        LOGGER.info("Creating WebDriver instance for browser: " + webdriver);
        
        try {
            // Setup appropriate WebDriver manager
            switch(webdriver.toLowerCase()) {
                case "firefox":
                    LOGGER.info("Setting up Firefox driver");
                    WebDriverManager.firefoxdriver().setup();
                    return new FirefoxDriver();
                    
                case "chrome":
                default:
                    LOGGER.info("Setting up Chrome driver");
                    // Use direct ChromeDriver setup instead of WebDriverManager when possible
                    try {
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--remote-allow-origins=*");
                        
                        // First try direct ChromeDriver instantiation
                        try {
                            LOGGER.info("Attempting direct ChromeDriver instantiation");
                            return new ChromeDriver(options);
                        } catch (Exception e) {
                            // If that fails, try WebDriverManager
                            LOGGER.info("Direct instantiation failed, trying WebDriverManager: " + e.getMessage());
                            WebDriverManager.chromedriver().setup();
                            return new ChromeDriver(options);
                        }
                    } catch (Exception e) {
                        LOGGER.severe("Failed to create Chrome driver: " + e.getMessage());
                        throw new RuntimeException("Failed to initialize ChromeDriver", e);
                    }
            }
        } catch (Exception e) {
            LOGGER.severe("Exception in WebDriverFactory: " + e.getMessage());
            throw new RuntimeException("Failed to create WebDriver: " + e.getMessage(), e);
        }
    }
} 