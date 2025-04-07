# Test Automation Framework

A comprehensive test automation framework built with Selenium WebDriver, TestNG, Cucumber, and REST Assured for both UI and API testing.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Usage Examples](#usage-examples)
  - [Running UI Tests](#running-ui-tests)
  - [Running API Tests](#running-api-tests)
  - [Creating New Tests](#creating-new-tests)
- [Configuration](#configuration)
- [Reporting](#reporting)
- [Best Practices](#best-practices)
- [Troubleshooting](#troubleshooting)

## Overview

This framework provides a foundation for creating maintainable and scalable automated tests for both web applications (using Selenium) and APIs (using REST Assured). It implements the Page Object Model design pattern along with BDD capabilities through Cucumber.

## Features

- **Cross-browser Testing**: Support for Chrome and Firefox
- **Page Object Model**: Organized page objects for better maintainability
- **BDD Support**: Cucumber integration with Gherkin syntax
- **API Testing**: REST Assured integration for API testing
- **Parallel Execution**: Support for parallel test execution (configurable)
- **Reporting**: Detailed HTML reports with screenshots using Extent Reports
- **Configuration Management**: Centralized configuration via properties files
- **Logging**: Comprehensive logging with Log4j
- **CI/CD Ready**: Compatible with Jenkins and other CI/CD tools

## Project Structure

```
automation-framework/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── api/                  # API testing components
│   │       │   ├── clients/          # REST Assured API clients
│   │       │   └── models/           # API data models
│   │       ├── pages/                # Page Objects (LoginPage, etc.)
│   │       └── utils/                # Helpers (DriverManager, etc.)
│   │
│   ├── test/
│   │   ├── java/
│   │   │   ├── api/                  # API tests
│   │   │   │   └── tests/            # API test classes
│   │   │   ├── features/             # Cucumber Feature Files
│   │   │   ├── hooks/                # Cucumber hooks
│   │   │   ├── stepdefinitions/      # Step definitions
│   │   │   ├── testrunner/           # TestNG runners
│   │   │   └── utils/                # Test utilities
│   │   └── resources/
│   │       ├── extent.properties     # Extent Reports configuration
│   │       └── config.properties     # Test configuration properties
│
├── testng.xml                        # TestNG Suite Configuration for UI tests
├── testng-api.xml                    # TestNG Suite Configuration for API tests
├── pom.xml                           # Maven Build Config
└── extent-config.xml                 # Extent Report Customization
```

## Setup Instructions

### Prerequisites

- Java JDK 17 or higher
- Maven 3.8.x or higher
- Chrome and/or Firefox browsers
- IDE with Maven support (IntelliJ IDEA, Eclipse, VS Code)

### Installation

1. **Clone the repository**

   ```bash
   git clone <repository-url>
   cd automation-framework
   ```

2. **Install dependencies**

   ```bash
   mvn clean install -DskipTests
   ```

3. **Configure browser settings** (optional)

   Edit the `src/test/resources/config.properties` file to set your browser preferences:

   ```properties
   browser=chrome
   chrome.headless=false
   firefox.headless=false
   webdriver.timeouts.pageLoad=30
   webdriver.timeouts.implicitWait=0
   ```

4. **Configure API endpoints** (optional)

   For API testing, update the base URL in your test or via system property:

   ```bash
   mvn test -Dapi.base.url=https://your-api-endpoint.com
   ```

## Usage Examples

### Running UI Tests

Run all UI tests with the default browser:

```bash
mvn test
```

Run UI tests with a specific browser:

```bash
mvn test -Dbrowser=firefox
```

Run UI tests in headless mode:

```bash
mvn test -Dchrome.headless=true
```

### Running API Tests

Run API tests using the dedicated test suite:

```bash
mvn test -DsuiteXmlFile=testng-api.xml
```

Run API tests with a custom base URL:

```bash
mvn test -DsuiteXmlFile=testng-api.xml -Dapi.base.url=https://custom-api.example.com
```

### Creating New Tests

#### Add a new UI test using Cucumber

1. Create a new feature file in `src/test/java/features/`

```gherkin
Feature: Login Functionality

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter username "user" and password "pass"
    And I click the login button
    Then I should be redirected to the dashboard
```

2. Implement the step definitions in `src/test/java/stepdefinitions/`

```java
@Given("I am on the login page")
public void i_am_on_the_login_page() {
    driver.get("https://example.com/login");
}

@When("I enter username {string} and password {string}")
public void i_enter_username_and_password(String username, String password) {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.enterUsername(username);
    loginPage.enterPassword(password);
}

@And("I click the login button")
public void i_click_the_login_button() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.clickLoginButton();
}

@Then("I should be redirected to the dashboard")
public void i_should_be_redirected_to_the_dashboard() {
    String expectedTitle = "Dashboard";
    Assert.assertTrue(driver.getTitle().contains(expectedTitle));
}
```

3. Create/update the corresponding Page Object:

```java
public class LoginPage {
    private WebDriver driver;
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
```

#### Add a new API test

1. Create a new test class in `src/test/java/api/tests/`

```java
public class ProductApiTest extends BaseApiTest {
    private ProductApiClient productClient;
    
    @BeforeMethod
    public void setUp() {
        productClient = new ProductApiClient();
    }
    
    @Test
    public void testGetAllProducts() {
        Response response = productClient.getAllProducts();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.getBody().asString());
    }
}
```

2. Create the corresponding API client:

```java
public class ProductApiClient extends BaseApiClient {
    private static final String PRODUCTS_ENDPOINT = "/products";
    
    public Response getAllProducts() {
        logger.info("Fetching all products");
        return get(PRODUCTS_ENDPOINT);
    }
}
```

## Configuration

### WebDriver Configuration

The framework supports various browser configurations through properties:

- **Browser Selection**: Set `browser` property to `chrome` or `firefox`
- **Headless Mode**: Set `chrome.headless` or `firefox.headless` to `true`
- **Timeouts**: Configure page load, implicit wait, and script timeouts

### Report Configuration

Customize Extent Reports in `extent.properties` and `extent-config.xml`:

- Report title and theme
- Screenshot settings
- System information

## Reporting

The framework generates detailed HTML reports using Extent Reports:

- Reports are saved in `target/SparkReport/TestReport_[timestamp].html`
- Failed test screenshots are captured automatically
- Reports include test steps, status, and execution time
- System information and environment details are included

## Best Practices

- **Page Objects**: Keep page objects focused on UI interactions
- **Test Independence**: Ensure tests can run independently
- **Test Data**: Externalize test data in resource files
- **Assertions**: Use meaningful assertions with proper error messages
- **Waits**: Prefer explicit waits over implicit waits
- **Configuration**: Use properties for environment-specific settings

## Troubleshooting

### Common Issues and Solutions

1. **WebDriver Not Found**
   - Ensure WebDriverManager is properly set up
   - Check browser version compatibility

2. **Element Not Found Exceptions**
   - Use explicit waits instead of thread sleep
   - Verify locators are correct and stable

3. **API Connection Issues**
   - Verify base URL is correct
   - Check network connectivity and proxy settings

4. **Report Generation Failures**
   - Ensure proper write permissions to target directory
   - Check extent.properties configuration 