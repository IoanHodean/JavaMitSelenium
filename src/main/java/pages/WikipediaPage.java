package pages;

import org.openqa.selenium.WebDriver;

/**
 * WikipediaPage represents the Wikipedia homepage and its functionality
 */
public class WikipediaPage extends BasePage {
    private final String URL = "https://www.wikipedia.org";
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public WikipediaPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigate to Wikipedia homepage
     */
    public void goToHomepage() {
        navigateTo(URL);
    }
} 