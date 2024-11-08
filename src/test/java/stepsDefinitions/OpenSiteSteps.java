package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import websetup.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenSiteSteps extends BaseTest {

    private WebDriver driver;

    public OpenSiteSteps() {
        this.driver = DriverManager.getDriver();
    }

    // Verificar se a página foi carregada corretamente
    @Then("a página inicial deve ser carregada corretamente")
    public void verificarPaginaCarregada() {

        boolean isElementPresent = driver.findElements(By.id("logo")).size() > 0;

        // Se o elemento foi encontrado, a página foi carregada corretamente
        assertTrue(isElementPresent, "A página não foi carregada corretamente.");
    }
    @Given("que eu abra a página inicial do DemoBlaze")
    public void openDemoBlazeSite() {
        driver.get("https://www.demoblaze.com"); // URL do site
    }
}

