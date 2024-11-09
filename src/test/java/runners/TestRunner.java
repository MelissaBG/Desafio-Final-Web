package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utils.DriverManager;

@RunWith(Cucumber.class)  // Executa o Cucumber com JUnit
@CucumberOptions(
        features = "src/test/resources/features", // Caminho dos arquivos .feature
        glue = "stepsDefinitions", // Pacote onde estão suas definições de steps
        plugin = {
                "pretty", // Gera uma saída legível no console
                "html:target/cucumber-reports/CucumberTestReport.html", // Relatório HTML
                "json:target/cucumber-reports/CucumberTestReport.json"  // Relatório JSON
        },
        tags = "@SmokeTest" // Filtra os testes com base nas tags (opcional)
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
