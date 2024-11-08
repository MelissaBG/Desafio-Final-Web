package stepsDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class GoToCartSteps {
    private WebDriver driver;

    public GoToCartSteps() {
        this.driver = DriverManager.getDriver();
    }

    public void goToCart() {
        driver.findElement(By.id("//a[@id='cartur']")).click();
    }
}
