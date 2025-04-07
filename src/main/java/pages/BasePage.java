package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverUtils;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

/**
 * Base class for Page Objects.
 * This class provides common functionality for all page objects,
 * ensuring consistent behavior across all pages.
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger logger = Logger.getLogger(getClass().getName());
    protected static final int DEFAULT_TIMEOUT = 10;
    
    /**
     * Constructor for the BasePage.
     * 
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Get the page title.
     * 
     * @return The page title
     */
    public String getTitle() {
        return driver.getTitle();
    }
    
    /**
     * Wait for page to load completely.
     */
    public void waitForPageToLoad() {
        WebDriverUtils.waitForPageToLoad(driver);
    }
    
    /**
     * Wait for an element to be visible.
     * 
     * @param locator Element locator
     * @return WebElement that is now visible
     */
    protected WebElement waitForElementVisible(By locator) {
        return WebDriverUtils.waitForElementToBeVisible(driver, locator);
    }
    
    /**
     * Wait for an element to be clickable.
     * 
     * @param locator Element locator
     * @return WebElement that is now clickable
     */
    protected WebElement waitForElementClickable(By locator) {
        return WebDriverUtils.waitForElementToBeClickable(driver, locator);
    }
    
    /**
     * Click on an element safely.
     * 
     * @param locator Element locator
     */
    protected void click(By locator) {
        WebDriverUtils.safeClick(driver, locator);
    }
    
    /**
     * Type text into an input field safely.
     * 
     * @param locator Element locator
     * @param text Text to enter
     */
    protected void type(By locator, String text) {
        WebDriverUtils.safeType(driver, locator, text);
    }
    
    /**
     * Check if an element is displayed.
     * 
     * @param locator Element locator
     * @return true if the element is displayed, false otherwise
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get text from an element.
     * 
     * @param locator Element locator
     * @return Text content of the element
     */
    protected String getText(By locator) {
        return waitForElementVisible(locator).getText();
    }
    
    /**
     * Find all elements matching a locator.
     * 
     * @param locator Element locator
     * @return List of matching WebElements
     */
    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }
} 