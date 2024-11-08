package stepsDefinitions;

import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.DriverManager;

public class PurchaseValidationsSteps {

    private WebDriver driver;
    private HomePage homePage;

    public PurchaseValidationsSteps() {
        this.driver = DriverManager.getDriver();
        this.homePage = new HomePage(driver);
    }

    @Then("I should see the home page")
    public void validateHomePage() {
        String pageTitle = driver.getTitle();
        Assertions.assertTrue(pageTitle.contains("STORE"));
    }

    @Then("I should see the Monitor category displayed")
    public void validateMonitorCategory() {
        Assertions.assertTrue(homePage.isMonitorCategoryDisplayed(), "Monitor category not displayed");
    }

    @Then("I should see the monitor added to the cart")
    public void validateMonitorAddedToCart() {
        homePage.goToCart();
        Assertions.assertTrue(homePage.isMonitorInCart("Samsung"), "Monitor not added to the cart");
    }

    @Then("I should see the checkout page")
    public void validateCheckoutPage() {
        Assertions.assertTrue(homePage.isCheckoutButtonDisplayed(), "Checkout button is not displayed");
    }

    @Then("I should see the order confirmation")
    public void validateOrderCompletion() {
        Assertions.assertTrue(homePage.isOrderConfirmationDisplayed(), "Order confirmation message not displayed");
    }
}

