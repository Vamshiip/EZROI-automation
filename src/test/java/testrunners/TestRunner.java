package testrunners;

//import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", // Path to your feature files
    glue = "stepdefinitions", // Path to your step definitions
    plugin = {
        "pretty", // Pretty console output
        "html:target/cucumber-reports/cucumber.html", // HTML Report
        "json:target/cucumber-reports/cucumber.json", // JSON Report
        "junit:target/cucumber-reports/cucumber.xml" // JUnit Report (for CI tools like Jenkins)
    },
    publish = true //enables publishing
//    monochrome = true, // Makes the output more readable in console
//    dryRun = false // Set to false to actually run the tests
)
public class TestRunner {
}

