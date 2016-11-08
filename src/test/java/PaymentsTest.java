import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources",
        format = {"pretty", "json:target/cucumber-html-report/Payments.json"},
        tags = {"@payments", "~@disabled"})

public class PaymentsTest {

}