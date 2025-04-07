package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import hooks.Hooks;
import pages.BasePage;
import pages.GooglePage;
import pages.WikipediaPage;

/**
 * WebSteps contains step definitions for web interactions in feature files.
 * It uses page objects to interact with web elements.
 */
public class WebSteps {
    private WebDriver driver = Hooks.driver;
    
    // Page Objects
    private BasePage currentPage;
    private GooglePage googlePage;
    private WikipediaPage wikipediaPage;
    
    public WebSteps() {
        // Initialize page objects
        googlePage = new GooglePage(driver);
        wikipediaPage = new WikipediaPage(driver);
        currentPage = googlePage; // Default page
    }
    
    @Given("I open the browser")
    public void i_open_the_browser() {
        // Driver is already initialized in Hooks, just maximize the window
        driver.manage().window().maximize();
    }
    
    @When("I navigate to {string}")
    public void i_navigate_to(String url) {
        // Determine which page we're navigating to and set currentPage
        if (url.contains("google.com")) {
            googlePage.navigateTo(url);
            currentPage = googlePage;
        } else if (url.contains("wikipedia.org")) {
            wikipediaPage.navigateTo(url);
            currentPage = wikipediaPage;
        } else {
            // For any other URL, use the base page
            currentPage.navigateTo(url);
        }
    }
    
    @Then("the page title should contain {string}")
    public void the_page_title_should_contain(String expectedTitle) {
        Assert.assertTrue(currentPage.pageTitleContains(expectedTitle), 
            "Expected title to contain '" + expectedTitle + "' but was '" + currentPage.getPageTitle() + "'");
    }
    
    @And("I search for {string}")
    public void i_search_for(String searchTerm) {
        // Currently we only support searching on Google
        if (currentPage instanceof GooglePage) {
            ((GooglePage) currentPage).search(searchTerm);
        } else {
            throw new RuntimeException("Search functionality not implemented for the current page");
        }
    }
    
    @Then("the search results should contain {string}")
    public void the_search_results_should_contain(String expectedText) {
        // Currently we only support verifying search results on Google
        if (currentPage instanceof GooglePage) {
            Assert.assertTrue(((GooglePage) currentPage).searchResultsContain(expectedText),
                "Expected search results to contain '" + expectedText + "' but did not find it.");
        } else {
            throw new RuntimeException("Search results verification not implemented for the current page");
        }
    }
} 