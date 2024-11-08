package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    // Construtor que recebe o WebDriver
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Método para acessar a categoria Monitores
    public void goToMonitorsCategory() {
        driver.findElement(By.xpath("//a[text()='Monitors']")).click();
    }

    // Método para acessar o carrinho
    public void goToCart() {
        driver.findElement(By.id("cartur")).click();
    }

    // Método para acessar a página inicial (se necessário)
    public void goToHomePage() {
        driver.findElement(By.id("nava")).click(); // Caso haja um link para a Home no site
    }
}

