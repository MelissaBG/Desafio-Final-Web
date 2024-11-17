package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    private By monitorsCategory = By.linkText("Monitors");
    private By monitor = By.xpath("//a[contains(@href, 'prod.html?idp_=10')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToMonitorsCategory() {
        driver.findElement(monitorsCategory).click();
    }
    public void selectMonitor() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement monitorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(monitor));
        monitorElement.click();
    }
    public void goToHomePage() {
        driver.get("https://www.demoblaze.com/");
    }

    public boolean isHomePageLoaded() {
        return driver.getTitle().contains("STORE") && driver.getCurrentUrl().equals("https://www.demoblaze.com/");
    }

    public boolean isCategoryLoaded(String category) {
        return driver.findElement(By.xpath("//h2[text()='" + category + "']")).isDisplayed();
    }
}
