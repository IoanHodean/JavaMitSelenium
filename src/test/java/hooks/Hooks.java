package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

/**
 * Cucumber Hooks class for managing test lifecycle.
 * This class contains methods that run before and after each scenario.
 * It handles WebDriver initialization and cleanup.
 */
public class Hooks {

    /**
     * Static WebDriver instance shared across all step definitions.
     * This allows step definitions to access the same browser instance.
     */
    public static WebDriver driver;

    /**
     * Setup method that runs before each scenario.
     * Initializes the WebDriver using the DriverManager singleton.
     */
    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
    }

    /**
     * Cleanup method that runs after each scenario.
     * Quits the WebDriver to release browser resources.
     */
    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}