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
    private WebDriverWait wait;

    // Locators
    private By addToCartButton = By.xpath("//*[@id='tbodyid']/div[2]/div/a");
    private By productName = By.xpath("//div[@id='tbodyid']//div[1]//div[1]//div[1]"); // Adjust this locator as needed for the product name on your page

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void selectMonitor() {
        WebElement monitor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tbodyid']/div[1]/div/a")));
        monitor.click();
    }

    public void addMonitorToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCart.click();
        acceptAlert();
    }

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Espera o alerta ser apresentado
        wait.until(ExpectedConditions.alertIsPresent());

        // Aceita o alerta (clica no botão OK do alerta)
        driver.switchTo().alert().accept();
    }

    public boolean isAddToCartButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton)).isDisplayed();
    }

    public boolean isAlertPresent() {
        try {
            // Espera até 10 segundos para que o alerta apareça
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            System.err.println("O alerta não apareceu dentro do tempo esperado.");
            return false;
        }
    }


    public boolean isItemInCart(String itemName) {
        By itemLocator = By.xpath("//td[contains(text(), '" + itemName + "')]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(itemLocator)).isDisplayed();
    }

    public boolean isProductVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    public WebDriver getDriver() {
        return this.driver;
    }

}