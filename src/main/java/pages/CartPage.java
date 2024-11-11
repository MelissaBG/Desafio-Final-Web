package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    private By botaoPlaceOrder = By.xpath("//button[text()='Place Order']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acessarCarrinho() {
        driver.findElement(By.linkText("Cart")).click();
    }

    public void finalizarCompra() {
        driver.findElement(botaoPlaceOrder).click();
    }
}

