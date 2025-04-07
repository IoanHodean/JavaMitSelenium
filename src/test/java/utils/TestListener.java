package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import hooks.Hooks;

/**
 * TestNG Listener for capturing screenshots on test failures and other test lifecycle events.
 * This listener helps with debugging by automatically capturing screenshots when tests fail.
 */
public class TestListener implements ITestListener {
    private static final Logger LOGGER = Logger.getLogger(TestListener.class.getName());
    private static final String SCREENSHOTS_DIR = "target/SparkReport/screenshots/";

    static {
        // Create the screenshots directory if it doesn't exist
        new File(SCREENSHOTS_DIR).mkdirs();
    }

    /**
     * Captures screenshot when a test fails.
     * 
     * @param result The test result containing test information
     */
    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("Test failed: " + result.getName() + " - Taking screenshot");
        WebDriver driver = Hooks.driver;
        
        if (driver == null) {
            LOGGER.warning("WebDriver is null, cannot take screenshot");
            return;
        }
        
        try {
            // Take screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            
            // Generate unique filename
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filename = result.getTestClass().getName() + "_" + 
                              result.getName() + "_" + 
                              timestamp + ".png";
            
            // Save the screenshot
            Path destination = Paths.get(SCREENSHOTS_DIR + filename);
            Files.copy(screenshot.toPath(), destination);
            
            LOGGER.info("Screenshot saved to: " + destination.toString());
        } catch (IOException e) {
            LOGGER.severe("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("Starting test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Test passed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.info("Test skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        LOGGER.info("Starting test suite: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LOGGER.info("Finished test suite: " + context.getName());
        LOGGER.info("Passed tests: " + context.getPassedTests().size());
        LOGGER.info("Failed tests: " + context.getFailedTests().size());
        LOGGER.info("Skipped tests: " + context.getSkippedTests().size());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implementing this method
    }
} 