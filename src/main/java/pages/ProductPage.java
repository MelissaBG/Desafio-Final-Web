package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;

    // Localizadores
    private By addToCartButton = By.xpath("//a[text()='Add to cart']");

    // Construtor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void adicionarMonitorAoCarrinho() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Aguarda o botão "Add to cart" estar clicável
        WebElement adicionarAoCarrinho = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

        // Clica no botão "Add to cart"
        adicionarAoCarrinho.click();

        //WebElement adicionarAoCarrinho = driver.findElement(addToCartButton);
       // adicionarAoCarrinho.click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // Corrigido para usar Duration.ofSeconds
    }

    public void aceitarAlerta() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}
