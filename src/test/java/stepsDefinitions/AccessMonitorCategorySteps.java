package stepDefinitions;

import io.cucumber.java.en.When;
import pages.HomePage;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;


public class AccessMonitorCategorySteps {
    WebDriver driver = DriverManager.getDriver();
    HomePage homePage = new HomePage(driver);

    @When("I access the Monitors category")
    public void iAccessTheMonitorsCategory() {
        homePage.goToMonitorsCategory();
    }
}
