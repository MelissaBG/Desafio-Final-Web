package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;

    private By nameField = By.id("name");
    private By countryField = By.id("country");
    private By cityField = By.id("city");
    private By cardField = By.id("card");
    private By monthField = By.id("month");
    private By yearField = By.id("year");
    private By purchaseButton = By.xpath("//button[text()='Purchase']");
    private By successMessage = By.xpath("//h2[contains(text(),'Thank you for your purchase!')]");
    private By checkoutHeader = By.id("checkout-header"); // Elemento específico para verificar a página de checkout

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillPurchaseFields(String name, String country, String city, String card, String month, String year) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        driver.findElement(countryField).sendKeys(country);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(cardField).sendKeys(card);
        driver.findElement(monthField).sendKeys(month);
        driver.findElement(yearField).sendKeys(year);
    }

    public void confirmPurchase() {
        driver.findElement(purchaseButton).click();
    }

    public boolean isPurchaseSuccessful() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(successMessage))
                .isDisplayed();
    }

    public boolean isCheckoutPage() {
        WebElement checkoutHeaderElement = driver.findElement(checkoutHeader);
        return checkoutHeaderElement.isDisplayed();
    }
}
