package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By productName = By.xpath("//div[@id='tbodyid']//div[1]//div[1]//div[1]"); // Adjust this locator as needed for the product name on your page

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void selectMonitor() {
        WebElement monitor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tbodyid']/div[1]/div/a")));
        monitor.click();
    }

    public boolean isProductVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

}