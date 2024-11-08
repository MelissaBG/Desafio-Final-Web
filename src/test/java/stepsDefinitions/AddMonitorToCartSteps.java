package stepsDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class AddMonitorToCartSteps {

    private WebDriver driver;

    public AddMonitorToCartSteps() {
        this.driver = DriverManager.getDriver();
    }

    public void addMonitorToCart() {
        // XPath para o primeiro monitor na categoria
        driver.findElement(By.xpath("//font[contains(text(),'Monitor Apple 24')]")).click();
        // XPath para o botão "Add to cart"
        driver.findElement(By.xpath("//font[contains(text(),'Adicionar ao carrinho')]")).click();
    }
}
