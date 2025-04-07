Feature: Sample Web Testing

  Scenario: Navigate to a website
    Given I open the browser
    When I navigate to "https://www.google.com"
    Then the page title should contain "Google" 