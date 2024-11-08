package stepsDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pages.HomePage;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccessMonitorCategorySteps {

    private WebDriver driver;
    private HomePage homePage;

    public AccessMonitorCategorySteps() {
        this.driver = DriverManager.getDriver();
        this.homePage = new HomePage(driver);
    }

    @When("eu navego para a categoria {string}")
    public void euNavegoParaACategoria(String categoria) {
        if (categoria.equals("Monitor")) {
            homePage.goToMonitorsCategory(); // Navegar para categoria "Monitor"
        }
    }
    @Then("eu devo ver a lista de Monitores")
    public void euDevoVerAListaDeMonitores() {
        assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Monitors')]")).isDisplayed());
        // Aqui verifico se o título da seção de Monitores ou a lista de monitores está sendo exibida.
    }
}

