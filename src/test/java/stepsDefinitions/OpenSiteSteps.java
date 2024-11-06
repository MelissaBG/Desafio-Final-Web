package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ConfigurationExtentReport;

public class OpenSiteSteps {
    private WebDriver driver;

    @Given("O usuario abre o site da Demoblaze")
    public void userOpenWebsite(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Inicializa o relatório e cria o teste
        ConfigurationExtentReport.initializeReport();
        ConfigurationExtentReport.createTest("Abrir o site Demoblaze");

        // Abre o site
        driver.get("https://www.demoblaze.com/index.html");

        String pageTitle = driver.getTitle();
        if (pageTitle.contains("STORE")) {
            ConfigurationExtentReport.getTest().pass("Página carregada com sucesso: " + pageTitle);
        } else {
            ConfigurationExtentReport.getTest().fail("Erro ao carregar a página: Título incorreto - " + pageTitle);
        }

        driver.quit();
        ConfigurationExtentReport.flushReport();
    }
}


