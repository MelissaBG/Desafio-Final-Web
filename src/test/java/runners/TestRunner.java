package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

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

}
