package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    private By categoriaMonitor = By.linkText("Monitors");
    private By monitor = By.xpath("//a[contains(@href, 'prod.html?idp_=10')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void acessarCategoriaMonitor() {
        driver.findElement(categoriaMonitor).click();
    }

    public void selecionarMonitor() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement monitorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(monitor));
        monitorElement.click();
    }
    public void goToHomePage() {
        // Navega para a URL inicial, por exemplo:
        driver.get("https://www.demoblaze.com/");
    }
}
