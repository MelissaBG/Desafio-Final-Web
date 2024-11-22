package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private By successMessage = By.xpath("//h2[contains(text(),'Thank you for your purchase!')]");


    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPurchaseSuccessful() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(successMessage))
                .isDisplayed();
    }
}
