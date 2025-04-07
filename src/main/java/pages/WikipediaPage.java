package pages;

import org.openqa.selenium.WebDriver;

public class WikipediaPage extends BasePage {
    
    public WikipediaPage(WebDriver driver) {
        super(driver);
    }
    
    public void navigateTo(String url) {
        driver.get(url);
    }
} 