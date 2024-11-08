package stepsDefinitions;

import io.cucumber.java.en.When;
import pages.HomePage;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class GoToCartSteps {

    private WebDriver driver;
    private HomePage homePage;

    public GoToCartSteps() {
        this.driver = DriverManager.getDriver();
        this.homePage = new HomePage(driver);
    }

    @When("I go to the cart")
    public void goToCart() {
        homePage.goToCart();
    }
}

