package stepsDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class FinalizePurchaseSteps {

    private WebDriver driver;

    // Construtor da classe
    public FinalizePurchaseSteps() {
        this.driver = DriverManager.getDriver();
    }

    public void finalizePurchase() {
        // XPath para o botão "Fazer Pedido" na página do carrinho
        driver.findElement(By.xpath("///button[@class='btn btn-success']")).click();

        // Preencher os campos do formulário de pedido
        driver.findElement(By.id("name")).sendKeys("João da Silva");
        driver.findElement(By.id("country")).sendKeys("Brasil");
        driver.findElement(By.id("city")).sendKeys("São Paulo");
        driver.findElement(By.id("card")).sendKeys("1234 5678 9876 5432");
        driver.findElement(By.id("month")).sendKeys("12");
        driver.findElement(By.id("year")).sendKeys("2025");

        // Clicar no botão "Purchase" (Comprar)
        driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
    }
}
