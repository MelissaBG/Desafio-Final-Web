package stepsDefinitions;

import io.cucumber.java.en.When;
import pages.HomePage;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class AddMonitorToCartSteps {

    private WebDriver driver;
    private HomePage homePage;

    public AddMonitorToCartSteps() {
        this.driver = DriverManager.getDriver();
        this.homePage = new HomePage(driver);
    }

    @When("eu adiciono o monitor ao carrinho")
    public void addMonitorToCart() {
        homePage.selectMonitor("Monitor Apple 24");
        homePage.addToCart();
    }
}


