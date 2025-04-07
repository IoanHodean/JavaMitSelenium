package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility class providing WebDriver helper methods including explicit waits.
 * This helps make test automation more stable by properly waiting for elements.
 */
public class WebDriverUtils {
    private static final int DEFAULT_TIMEOUT = 10; // Default timeout in seconds
    
    /**
     * Wait for an element to be clickable.
     * 
     * @param driver The WebDriver instance
     * @param locator The By locator for the element
     * @param timeoutInSeconds Maximum time to wait in seconds
     * @return The WebElement once it is clickable
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for an element to be clickable with default timeout.
     * 
     * @param driver The WebDriver instance
     * @param locator The By locator for the element
     * @return The WebElement once it is clickable
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
        return waitForElementToBeClickable(driver, locator, DEFAULT_TIMEOUT);
    }
    
    /**
     * Wait for an element to be visible.
     * 
     * @param driver The WebDriver instance
     * @param locator The By locator for the element
     * @param timeoutInSeconds Maximum time to wait in seconds
     * @return The WebElement once it is visible
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for an element to be visible with default timeout.
     * 
     * @param driver The WebDriver instance
     * @param locator The By locator for the element
     * @return The WebElement once it is visible
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator) {
        return waitForElementToBeVisible(driver, locator, DEFAULT_TIMEOUT);
    }
    
    /**
     * Wait for page to load completely using document.readyState.
     * 
     * @param driver The WebDriver instance
     * @param timeoutInSeconds Maximum time to wait in seconds
     */
    public static void waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(pageLoadCondition);
    }
    
    /**
     * Wait for page to load completely using document.readyState with default timeout.
     * 
     * @param driver The WebDriver instance
     */
    public static void waitForPageToLoad(WebDriver driver) {
        waitForPageToLoad(driver, DEFAULT_TIMEOUT);
    }
    
    /**
     * Safely click on an element after waiting for it to be clickable.
     * 
     * @param driver The WebDriver instance
     * @param locator The By locator for the element
     */
    public static void safeClick(WebDriver driver, By locator) {
        WebElement element = waitForElementToBeClickable(driver, locator);
        element.click();
    }
    
    /**
     * Safely enter text into an input field after waiting for it to be visible.
     * 
     * @param driver The WebDriver instance
     * @param locator The By locator for the element
     * @param text The text to enter
     */
    public static void safeType(WebDriver driver, By locator, String text) {
        WebElement element = waitForElementToBeVisible(driver, locator);
        element.clear();
        element.sendKeys(text);
    }
} 