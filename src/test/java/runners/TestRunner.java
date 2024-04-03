package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/functionalTests", 
glue = {"stepDefinition" }, 
monochrome = true,
stepNotifications = true,
plugin = { "pretty", "html:target/cucumber-reports.html",
			"pretty","json:target/cucumber-reports/Cucumber.json",
			"pretty","junit:target/cucumber-reports/Cucumber.xml"}


)

public class TestRunner {

}