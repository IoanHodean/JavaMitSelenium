package utils;

import org.openqa.selenium.WebDriver;

/**
 * DriverManager implements the Singleton pattern to manage WebDriver instances.
 * 
 * This class ensures that:
 * - Only one WebDriver instance is created throughout the test execution
 * - The WebDriver is properly initialized when needed
 * - The WebDriver is properly terminated when tests complete
 * - All tests use the same WebDriver instance
 * 
 * This pattern helps with:
 * - Reducing resource usage by preventing multiple browser instances
 * - Sharing browser state across test steps
 * - Centralizing WebDriver management logic
 */
public class DriverManager {
    // Single WebDriver instance shared across all tests
    private static WebDriver driver;

    /**
     * Gets the singleton WebDriver instance, creating it if necessary.
     * 
     * @return The WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            driver = WebDriverFactory.createWebDriver();
        }
        return driver;
    }

    /**
     * Quits the WebDriver instance if it exists and sets it to null.
     * Should be called after tests complete to clean up resources.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}