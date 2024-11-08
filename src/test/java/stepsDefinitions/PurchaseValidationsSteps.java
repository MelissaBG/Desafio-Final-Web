package stepsDefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverManager;

public class PurchaseValidationsSteps {
    private WebDriver driver;

    public PurchaseValidationsSteps() {
        this.driver = DriverManager.getDriver();
    }

    public void validateHomePage() {
        // Validar se a página inicial foi carregada verificando o título
        String pageTitle = driver.getTitle();
        Assertions.assertTrue(pageTitle.contains("STORE"));
    }

    public void validateMonitorCategory() {
        // Validar se a categoria "Monitors" foi acessada corretamente
        WebElement monitorCategory = driver.findElement(By.xpath("//a[contains(text(),'Monitors')]"));
        Assertions.assertTrue(monitorCategory.isDisplayed(), "Monitor category not displayed");
    }

    public void validateMonitorAddedToCart() {
        // Validar se o monitor foi adicionado ao carrinho
        WebElement cartIcon = driver.findElement(By.id("cartur"));
        cartIcon.click();

        // Verificar se o monitor está visível no carrinho
        WebElement cartItem = driver.findElement(By.xpath("//tr/td[contains(text(),'Samsung')]"));
        Assertions.assertTrue(cartItem.isDisplayed(), "Monitor not added to the cart");
    }

    public void validateCheckoutPage() {
        // Validar se a página de checkout é exibida
        WebElement checkoutButton = driver.findElement(By.xpath("//button[contains(text(),'Place Order')]"));
        Assertions.assertTrue(checkoutButton.isDisplayed(), "Checkout button is not displayed");
    }

    public void validateOrderCompletion() {
        // Validar se a compra foi completada com sucesso verificando a mensagem de confirmação
        WebElement orderConfirmationMessage = driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]"));
        Assertions.assertTrue(orderConfirmationMessage.isDisplayed(), "Order confirmation message not displayed");
    }
}
