package runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utils.DriverManager;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",  // Caminho correto do arquivo .feature
        glue = "stepsDefinitions",  // Caminho correto para as definições dos steps
        plugin = {"pretty", "html:target/cucumber-reports/CucumberTestReport.html", "json:target/cucumber-reports/CucumberTestReport.json"},
        tags = "@SmokeTest"
)
public class TestRunner {

        @BeforeClass
        public static void setup() {
                DriverManager.getDriver();
        }

        @AfterClass
        public static void tearDown() {
                DriverManager.closeDriver();
        }
}
