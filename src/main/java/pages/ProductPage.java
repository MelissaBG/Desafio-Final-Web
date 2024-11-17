package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;

    // Locators
    private By addToCartButton = By.xpath("//a[text()='Add to cart']");
    private By productName = By.xpath("//h1"); // Adjust this locator as needed for the product name on your page

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addMonitorToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the "Add to cart" button to be clickable and click it
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCart.click();

        // Accept the alert that appears after adding to the cart
        acceptAlert();
    }

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    // Checks if the "Add to cart" button is visible
    public boolean isAddToCartButtonVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton)).isDisplayed();
    }

    // Checks if the alert is present
    public boolean isAlertPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Checks if the item is in the cart
    public boolean isItemInCart(String itemName) {
        By itemLocator = By.xpath("//td[contains(text(), '" + itemName + "')]"); // Adjust as needed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(itemLocator)).isDisplayed();
    }

    // Checks if the product is visible
    public boolean isProductVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Wait for the product name (or another identifying element) to be visible
            return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
