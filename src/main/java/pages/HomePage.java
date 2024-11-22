package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    // Usando o XPath correto para localizar a categoria "Monitors"
    private By monitorsCategory = By.xpath("//*[@id='itemc']"); // Alterado para XPath
    private By monitor = By.xpath("//a[contains(@href, 'prod.html?idp_=10')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToMonitorsCategory() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement monitorsCategoryElement = wait.until(ExpectedConditions.elementToBeClickable(monitorsCategory)); // Usando o linkText
        monitorsCategoryElement.click();
    }

    public void goToHomePage() {
        driver.get("https://www.demoblaze.com/");
    }

    public boolean isHomePageLoaded() {
        return driver.getTitle().contains("STORE") && driver.getCurrentUrl().equals("https://www.demoblaze.com/");
    }

}
