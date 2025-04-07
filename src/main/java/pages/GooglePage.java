package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GooglePage extends BasePage {
    
    private By searchBox = By.name("q");
    
    public GooglePage(WebDriver driver) {
        super(driver);
    }
    
    public void navigateTo(String url) {
        driver.get(url);
        waitForPageToLoad();
    }
    
    public void search(String searchTerm) {
        // Wait for the search box to be visible and interactable
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        
        // Clear any existing text and enter the search term
        WebElement element = driver.findElement(searchBox);
        element.clear();
        element.sendKeys(searchTerm);
        element.sendKeys(Keys.RETURN);
        
        // Wait for the page to load after search
        waitForPageToLoad();
    }
    
    public boolean searchResultsContain(String text) {
        return driver.getPageSource().contains(text);
    }
} 