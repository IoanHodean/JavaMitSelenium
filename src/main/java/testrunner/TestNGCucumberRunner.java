package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/features",
    glue = {"stepdefinitions", "hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    }
)
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {}