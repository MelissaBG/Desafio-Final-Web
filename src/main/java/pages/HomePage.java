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




    public void selectMonitor() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement monitorElement = wait.until(ExpectedConditions.elementToBeClickable(monitor));
        monitorElement.click();
    }

    public void goToHomePage() {
        driver.get("https://www.demoblaze.com/");
    }

    public boolean isHomePageLoaded() {
        return driver.getTitle().contains("STORE") && driver.getCurrentUrl().equals("https://www.demoblaze.com/");
    }

    public boolean isCategoryLoaded() {
        try {
            // Espera até que um elemento da categoria de monitores seja visível na nova página
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement categoryHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Monitors']"))); // Supondo que haja um título com esse texto
            return categoryHeader != null; // Verifica se o título da categoria foi encontrado
        } catch (Exception e) {
            return false; // Se o título ou o elemento esperado não for encontrado
        }
    }
}
