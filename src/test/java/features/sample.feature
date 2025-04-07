Feature: Sample Web Testing

  Scenario: Navigate to a website
    Given I open the browser
    When I navigate to "https://www.google.com"
    Then the page title should contain "Google"
    
  Scenario: Search on Google
    Given I open the browser
    When I navigate to "https://www.google.com"
    And I search for "Selenium WebDriver"
    Then the search results should contain "Selenium"
    
  Scenario: Visit multiple websites in sequence
    Given I open the browser
    When I navigate to "https://www.google.com"
    Then the page title should contain "Google"
    When I navigate to "https://www.wikipedia.org"
    Then the page title should contain "Wikipedia"
    
  Scenario Outline: Test multiple websites
    Given I open the browser
    When I navigate to "<website>"
    Then the page title should contain "<expected_title>"
    
    Examples:
      | website                 | expected_title |
      | https://www.google.com  | Google         |
      | https://www.github.com  | GitHub         |
      | https://www.wikipedia.org | Wikipedia    | 