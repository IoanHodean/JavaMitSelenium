package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import hooks.Hooks;

public class WebSteps {
    private WebDriver driver = Hooks.driver;
    
    @Given("I open the browser")
    public void i_open_the_browser() {
        // Driver is already initialized in Hooks
        driver.manage().window().maximize();
    }
    
    @When("I navigate to {string}")
    public void i_navigate_to(String url) {
        driver.get(url);
    }
    
    @Then("the page title should contain {string}")
    public void the_page_title_should_contain(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle), 
            "Expected title to contain '" + expectedTitle + "' but was '" + actualTitle + "'");
    }
} 