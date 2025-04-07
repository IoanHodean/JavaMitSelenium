package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * TestNG Cucumber Runner class that configures and executes Cucumber tests.
 * This class extends AbstractTestNGCucumberTests to integrate Cucumber with TestNG.
 * 
 * The CucumberOptions annotation configures:
 * - Feature file locations
 * - Step definition and hook package locations
 * - Report generation plugins
 */
@CucumberOptions(
    // Path to feature files containing Gherkin scenarios
    features = "src/test/java/features",
    
    // Packages containing step definitions and hooks
    glue = {"stepdefinitions", "hooks"},
    
    // Report generation plugins
    plugin = {
        "pretty", // Console output formatting
        "html:target/cucumber-reports.html", // HTML report generation
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", // Extent Reports integration
        "json:target/cucumber-reports/cucumber.json",
        "rerun:target/failed_scenarios.txt"
    }
)
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {}