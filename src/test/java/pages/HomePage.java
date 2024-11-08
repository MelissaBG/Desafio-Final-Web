package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    // Localizadores
    private By monitorsCategoryLink = By.xpath("//a[4]");
    private By cartLink = By.id("cartur");
    private By homeLink = By.id("nava");
    private By addToCartButton = By.xpath("//font[contains(text(),'Adicionar ao carrinho')]");
    private By placeOrderButton = By.xpath("//button[contains(text(),'Place Order')]");
    private By orderConfirmationMessage = By.xpath("//h2[contains(text(),'Thank you for your purchase!')]");

    // Construtor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Acessa a categoria Monitores
    public void goToMonitorsCategory() {
        driver.findElement(monitorsCategoryLink).click();
    }

    // Acessa o carrinho
    public void goToCart() {
        driver.findElement(cartLink).click();
    }

    // Acessa a página inicial
    public void goToHomePage() {
        driver.findElement(homeLink).click();
    }

    // Seleciona o monitor pelo nome
    public void selectMonitor(String monitorName) {
        driver.findElement(By.xpath("//font[contains(text(),'" + monitorName + "')]")).click();
    }

    // Adiciona o monitor ao carrinho
    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    // Finaliza a compra
    public void placeOrder(String name, String country, String city, String cardNumber, String month, String year) {
        driver.findElement(placeOrderButton).click();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("card")).sendKeys(cardNumber);
        driver.findElement(By.id("month")).sendKeys(month);
        driver.findElement(By.id("year")).sendKeys(year);
        driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
    }

    // Verifica se a categoria Monitores está visível
    public boolean isMonitorCategoryDisplayed() {
        return driver.findElement(monitorsCategoryLink).isDisplayed();
    }

    // Verifica se o monitor está no carrinho
    public boolean isMonitorInCart(String monitorName) {
        return driver.findElement(By.xpath("//tr/td[contains(text(),'" + monitorName + "')]")).isDisplayed();
    }

    // Verifica se o botão de checkout está visível
    public boolean isCheckoutButtonDisplayed() {
        return driver.findElement(placeOrderButton).isDisplayed();
    }

    // Verifica se a confirmação do pedido é exibida
    public boolean isOrderConfirmationDisplayed() {
        return driver.findElement(orderConfirmationMessage).isDisplayed();
    }
}