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
    private By mensagemSucesso = By.xpath("//h2[contains(text(),'Thank you for your purchase!')]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherInformacoesDeCompra(String name, String country, String city, String card, String month, String year) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        nameElement.sendKeys(name);

        WebElement countryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(countryField));
        countryElement.sendKeys(country);

        WebElement cityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cityField));
        cityElement.sendKeys(city);

        WebElement cardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cardField));
        cardElement.sendKeys(card);

        WebElement monthElement = wait.until(ExpectedConditions.visibilityOfElementLocated(monthField));
        monthElement.sendKeys(month);

        WebElement yearElement = wait.until(ExpectedConditions.visibilityOfElementLocated(yearField));
        yearElement.sendKeys(year);
    }

    public void confirmarCompra() {
        driver.findElement(purchaseButton).click();
    }

    public boolean isCompraSucesso() {
        return driver.findElement(mensagemSucesso).isDisplayed();
    }
}
