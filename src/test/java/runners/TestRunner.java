package runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utils.ReportUtils;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",  // Caminho para as features
        glue = "stepDefinitions",                  // Caminho para os step definitions
        plugin = {"pretty", "html:reports/cucumber-report.html"}
)
public class TestRunner {
    @BeforeClass
    public static void setup() {
        ReportUtils.initReport("DemoblazeTests");
    }

    @AfterClass
    public static void tearDown() {
        ReportUtils.flushReports();
    }
}

