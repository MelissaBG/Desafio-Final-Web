package stepsDefinitions;

import io.cucumber.java.en.When;
import pages.HomePage;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class FinalizePurchaseSteps {

    private WebDriver driver;
    private HomePage homePage;

    public FinalizePurchaseSteps() {
        this.driver = DriverManager.getDriver();
        this.homePage = new HomePage(driver);
    }

    @When("I finalize the purchase")
    public void finalizePurchase() {
        homePage.placeOrder("João da Silva", "Brasil", "São Paulo", "1234 5678 9876 5432", "12", "2025");
    }
}


