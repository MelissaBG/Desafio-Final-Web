package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.DriverManager;

public class OpenSiteSteps {

    private WebDriver driver;
    private HomePage homePage;

    public OpenSiteSteps() {
        this.driver = DriverManager.getDriver();
        this.homePage = new HomePage(driver);
    }


    @Given("que eu abra a página inicial do DemoBlaze")
    public void openDemoBlazeSite() {
        homePage.goToHomePage();
    }

    @Then("a página inicial deve ser carregada corretamente")
    public void verificarPaginaCarregada() {
        String expectedUrl = "https://www.demoblaze.com/";
        String currentUrl = driver.getCurrentUrl();

        Assertions.assertEquals(expectedUrl, currentUrl, "A URL da página inicial não corresponde à esperada.");

    }
}


