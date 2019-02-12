package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"stepDefinitions"},
        features = {"src/test/resources/features/"},
        tags = {"@test"}
)

public class CucumberRunner {
}
