package stepsDefinitions;

import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ConfigurationExtentReport;

import java.io.IOException;

public class TestClass {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Inicializa o relatório
        ConfigurationExtentReport.initializeReport();
    }

    @Test
    public void demoblazeTest() {
        // Cria um novo teste no relatório
        ConfigurationExtentReport.createTest("Demoblaze - Teste de Fluxo de Compra");

        try {
            driver.get("https://www.demoblaze.com");
            ConfigurationExtentReport.getTest().log(Status.INFO, "Página inicial acessada");

            // Exemplo de passos de teste (adicionar produto ao carrinho)
            ConfigurationExtentReport.getTest().log(Status.PASS, "Produto adicionado ao carrinho com sucesso");

            // Captura de tela
            String screenshotPath = ConfigurationExtentReport.captureScreenshot(driver, "ProdutoAdicionadoCarrinho");
            ConfigurationExtentReport.getTest().addScreenCaptureFromPath(screenshotPath, "Produto Adicionado ao Carrinho");

        } catch (Exception e) {
            ConfigurationExtentReport.getTest().log(Status.FAIL, "Erro durante o teste: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        // Finaliza o relatório
        ConfigurationExtentReport.flushReport();
    }
}
