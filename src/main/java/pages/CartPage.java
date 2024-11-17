package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private By placeOrderButton = By.xpath("//button[text()='Place Order']");
    private By cartTitle = By.xpath("//h2[text()='Cart']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void completePurchase() {
        driver.findElement(placeOrderButton).click();
    }

    public boolean isItemInCart(String itemName) {
        By itemLocator = By.xpath("//td[contains(text(), '" + itemName + "')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(itemLocator)).isDisplayed();
    }

    public boolean isCartPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.or(
                ExpectedConditions.titleContains("Cart"),
                ExpectedConditions.visibilityOfElementLocated(cartTitle)
        ));
    }

    public void accessCart() {
        WebElement cartLink = driver.findElement(By.id("cart"));
        cartLink.click();
    }
}

