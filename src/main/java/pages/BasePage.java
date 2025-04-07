package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * BasePage contains common functionality for all page objects.
 * All page-specific classes should extend this class.
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Navigate to a URL
     * @param url The URL to navigate to
     */
    public void navigateTo(String url) {
        driver.get(url);
    }
    
    /**
     * Get the current page title
     * @return The page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Check if the page title contains specific text
     * @param text The text to check for
     * @return True if the title contains the text, false otherwise
     */
    public boolean pageTitleContains(String text) {
        return driver.getTitle().contains(text);
    }
} 