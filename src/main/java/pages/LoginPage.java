package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object Model class for the Login page.
 * This class encapsulates all the elements and actions related to the login functionality.
 * It follows the Page Object Model pattern to separate page interactions from test logic.
 */
public class LoginPage extends BasePage {
    // Page element locators using By class
    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login");

    /**
     * Constructor for LoginPage.
     * 
     * @param driver WebDriver instance to be used for page interactions
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters the username in the username field.
     * 
     * @param user The username to enter
     * @return LoginPage for method chaining
     */
    public LoginPage enterUsername(String user) {
        type(username, user);
        return this;
    }

    /**
     * Enters the password in the password field.
     * 
     * @param pass The password to enter
     * @return LoginPage for method chaining
     */
    public LoginPage enterPassword(String pass) {
        type(password, pass);
        return this;
    }

    /**
     * Clicks the login button to submit the login form.
     * 
     * @return The next page after login (could be customized to return appropriate page)
     */
    public void clickLogin() {
        click(loginBtn);
        waitForPageToLoad();
    }
    
    /**
     * Performs login with the given credentials.
     * 
     * @param user The username to enter
     * @param pass The password to enter
     * @return The next page after login (could be customized to return appropriate page)
     */
    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }
    
    /**
     * Checks if the login page is displayed.
     * 
     * @return true if login page is displayed, false otherwise
     */
    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(username) && 
               isElementDisplayed(password) && 
               isElementDisplayed(loginBtn);
    }
}