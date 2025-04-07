package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.logging.Logger;

/**
 * WebDriverFactory is responsible for creating new WebDriver instances based on the browser type.
 * 
 * This factory class:
 * - Creates different types of WebDriver instances (Chrome, Firefox)
 * - Uses WebDriverManager to automatically download and configure browser drivers
 * - Selects browser type based on the "browser" system property or config file
 * - Uses Chrome as the default browser if no browser is specified
 * - Configures timeouts and other browser settings
 * 
 * Usage example:
 * WebDriver driver = WebDriverFactory.createWebDriver();
 * 
 * With system property:
 * mvn test -Dbrowser=firefox
 */
public class WebDriverFactory {
    private static final Logger LOGGER = Logger.getLogger(WebDriverFactory.class.getName());
    
    // Default timeout values in seconds
    private static final int DEFAULT_PAGE_LOAD_TIMEOUT = 30;
    private static final int DEFAULT_IMPLICIT_WAIT = 0; // Best practice: use explicit waits instead
    private static final int DEFAULT_SCRIPT_TIMEOUT = 30;
    
    /**
     * Creates and returns a new WebDriver instance based on browser config or system property.
     * 
     * @return WebDriver instance for the requested browser
     * @throws RuntimeException if an unsupported browser is specified
     */
    public static WebDriver createWebDriver() {
        String webdriver = ConfigProperties.getProperty("browser", "chrome");
        // System property can override the config file
        webdriver = System.getProperty("browser", webdriver);
        
        LOGGER.info("Creating WebDriver instance for browser: " + webdriver);
        
        WebDriver driver;
        
        try {
            // Setup appropriate WebDriver manager
            switch(webdriver.toLowerCase()) {
                case "firefox":
                    LOGGER.info("Setting up Firefox driver");
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    configureFirefoxOptions(firefoxOptions);
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                    
                case "chrome":
                default:
                    LOGGER.info("Setting up Chrome driver");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    configureChromeOptions(chromeOptions);
                    
                    // First try direct ChromeDriver instantiation
                    try {
                        LOGGER.info("Attempting direct ChromeDriver instantiation");
                        driver = new ChromeDriver(chromeOptions);
                    } catch (Exception e) {
                        // If that fails, try WebDriverManager
                        LOGGER.info("Direct instantiation failed, trying WebDriverManager: " + e.getMessage());
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver(chromeOptions);
                    }
                    break;
            }
            
            // Configure timeouts
            configureDriverTimeouts(driver);
            
            return driver;
        } catch (Exception e) {
            LOGGER.severe("Exception in WebDriverFactory: " + e.getMessage());
            throw new RuntimeException("Failed to create WebDriver: " + e.getMessage(), e);
        }
    }
    
    /**
     * Configure Chrome options.
     * 
     * @param options ChromeOptions to configure
     */
    private static void configureChromeOptions(ChromeOptions options) {
        // Common Chrome settings
        options.addArguments("--remote-allow-origins=*");
        
        // Optional arguments based on configuration
        if (ConfigProperties.getBooleanProperty("chrome.headless", false)) {
            options.addArguments("--headless=new");
        }
        
        if (ConfigProperties.getBooleanProperty("chrome.incognito", false)) {
            options.addArguments("--incognito");
        }
        
        // Add any additional Chrome arguments from config
        String additionalArgs = ConfigProperties.getProperty("chrome.args", "");
        if (!additionalArgs.isEmpty()) {
            for (String arg : additionalArgs.split(",")) {
                options.addArguments(arg.trim());
            }
        }
    }
    
    /**
     * Configure Firefox options.
     * 
     * @param options FirefoxOptions to configure
     */
    private static void configureFirefoxOptions(FirefoxOptions options) {
        // Optional arguments based on configuration
        if (ConfigProperties.getBooleanProperty("firefox.headless", false)) {
            options.addArguments("-headless");
        }
        
        if (ConfigProperties.getBooleanProperty("firefox.private", false)) {
            options.addArguments("-private");
        }
        
        // Add any additional Firefox arguments from config
        String additionalArgs = ConfigProperties.getProperty("firefox.args", "");
        if (!additionalArgs.isEmpty()) {
            for (String arg : additionalArgs.split(",")) {
                options.addArguments(arg.trim());
            }
        }
    }
    
    /**
     * Configure timeouts for the WebDriver.
     * 
     * @param driver WebDriver to configure
     */
    private static void configureDriverTimeouts(WebDriver driver) {
        // Read timeout values from configuration
        int pageLoadTimeout = ConfigProperties.getIntProperty(
            "webdriver.timeouts.pageLoad", DEFAULT_PAGE_LOAD_TIMEOUT);
        
        int implicitWait = ConfigProperties.getIntProperty(
            "webdriver.timeouts.implicitWait", DEFAULT_IMPLICIT_WAIT);
        
        int scriptTimeout = ConfigProperties.getIntProperty(
            "webdriver.timeouts.script", DEFAULT_SCRIPT_TIMEOUT);
        
        // Set timeouts on the driver
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(scriptTimeout));
    }
} 