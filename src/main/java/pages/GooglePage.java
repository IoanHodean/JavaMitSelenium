package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * GooglePage represents the Google search page and its functionality
 */
public class GooglePage extends BasePage {
    // Locators
    private final By searchBoxLocator = By.name("q");
    private final By searchResultsLocator = By.id("search");
    
    private final String URL = "https://www.google.com";
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public GooglePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigate to Google homepage
     */
    public void goToHomepage() {
        navigateTo(URL);
    }
    
    /**
     * Perform a search on Google
     * @param searchTerm The term to search for
     */
    public void search(String searchTerm) {
        try {
            // Wait for the search box to be clickable
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchBoxLocator));
            
            // Focus on the search box and clear any existing text
            searchBox.click();
            searchBox.clear();
            
            // Type the search term and submit
            searchBox.sendKeys(searchTerm);
            searchBox.sendKeys(Keys.RETURN);
            
            // Wait for search results to appear
            wait.until(ExpectedConditions.titleContains(searchTerm));
            
        } catch (Exception e) {
            // If there are any issues with the standard approach, try alternative methods
            try {
                // Find search input and use JavaScript to set its value
                WebElement searchBox = driver.findElement(By.xpath("//input[@name='q']"));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1]", searchBox, searchTerm);
                js.executeScript("arguments[0].dispatchEvent(new KeyboardEvent('keypress', {'key':'Enter'}))", searchBox);
            } catch (Exception e2) {
                throw new RuntimeException("Failed to perform search: " + e2.getMessage(), e2);
            }
        }
    }
    
    /**
     * Check if search results contain specific text
     * @param text The text to check for
     * @return True if the results contain the text, false otherwise
     */
    public boolean searchResultsContain(String text) {
        try {
            // Wait for search results to fully load
            wait.until(ExpectedConditions.presenceOfElementLocated(searchResultsLocator));
            
            // Get the text of the search results
            String searchResultsText = driver.findElement(searchResultsLocator).getText();
            
            // Check if the results contain the expected text
            return searchResultsText.contains(text);
                
        } catch (Exception e) {
            // Alternative approach if the standard search results container is not found
            try {
                // Check if content is visible in the page
                String pageSource = driver.getPageSource();
                return pageSource.contains(text);
            } catch (Exception e2) {
                throw new RuntimeException("Failed to verify search results: " + e2.getMessage(), e2);
            }
        }
    }
} 