package stepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductPage;
import utils.DriverManager;

public class MonitorPurchaseSteps {

    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Before
    public void setup() {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @After
    public void tearDown() {
        DriverManager.closeDriver();
    }

    @Given("I am on the DemoBlaze homepage")
    public void iAmOnTheDemoBlazeHomepage() {
        driver.get("https://www.demoblaze.com/index.html");
        Assertions.assertTrue(homePage.isHomePageLoaded(), "Home page was not loaded correctly.");
    }

    @When("I access the monitors category")
    public void iAccessTheMonitorsCategory() {
        homePage.goToMonitorsCategory();
        Assertions.assertTrue(homePage.isCategoryLoaded("Monitors"), "Monitors category was not loaded correctly.");
    }

    @When("I select the first monitor")
    public void iSelectTheFirstMonitor() {
        homePage.selectMonitor();
        Assertions.assertTrue(productPage.isProductVisible(), "Selected monitor is not visible.");
    }

    @When("I add the monitor to the cart")
    public void iAddTheMonitorToTheCart() {

        Assertions.assertTrue(productPage.isAddToCartButtonVisible(), "'Add to cart' button is not visible.");
        productPage.addMonitorToCart();
        Assertions.assertTrue(productPage.isAlertPresent(), "Alert for item added to cart was not displayed.");
        String itemName = "Monitor Name";
        Assertions.assertTrue(productPage.isItemInCart(itemName), "Item was not found in the cart after being added.");
    }

    @When("I go to the cart")
    public void iGoToTheCart() {
        cartPage.accessCart();
        Assertions.assertTrue(cartPage.isCartPageLoaded(), "Cart page was not loaded correctly.");
    }

    @When("I complete the purchase by filling in the required fields")
    public void iCompleteThePurchaseByFillingInTheRequiredFields() {
        cartPage.completePurchase();
        Assertions.assertTrue(checkoutPage.isCheckoutPage(), "Checkout page was not loaded correctly.");
        checkoutPage.fillPurchaseFields("Test Name", "Brazil", "Test City", "1234 5678 9012 3456", "12", "2025");
        checkoutPage.confirmPurchase();
    }

    @Then("the purchase should be completed successfully")
    public void thePurchaseShouldBeCompletedSuccessfully() {
        Assertions.assertTrue(checkoutPage.isPurchaseSuccessful(), "Purchase was not completed successfully!");
    }

    @Then("the item {string} should be in the cart")
    public void theItemShouldBeInTheCart(String itemName) {
        Assertions.assertTrue(cartPage.isItemInCart(itemName), "Item was not found in the cart.");
    }
}

