package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By addToCartButton = By.xpath("//*[@id='tbodyid']/div[2]/div/a");
    private By productName = By.xpath("//div[@id='tbodyid']//div[1]//div[1]//div[1]"); // Adjust this locator as needed for the product name on your page

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void selectMonitor() {
        WebElement monitor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tbodyid']/div[1]/div/a")));
        monitor.click();
    }

    public void addMonitorToCart() {
        System.out.println("Tentando adicionar monitor ao carrinho...");

        // Espera e clica no botão Adicionar ao Carrinho
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCart.click();
        System.out.println("Clique realizado no botão 'Adicionar ao Carrinho'.");

        // Tenta aceitar o alerta
        try {
            acceptAlert();
            System.out.println("Alerta aceito com sucesso.");
        } catch (TimeoutException e) {
            System.err.println("O alerta não apareceu dentro do tempo esperado. Verifique o fluxo.");
        }

        // Aguarde um pouco mais para garantir que a atualização do carrinho seja refletida no contador
        try {
            Thread.sleep(2000);  // Aguarda 2 segundos (ajuste conforme necessário)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean verifyExpectedSelector() {
        System.out.println("Validando seletor utilizado para o carrinho...");
        WebElement cartIcon = driver.findElement(By.id("cartur"));
        return cartIcon != null && cartIcon.isDisplayed();
    }
    // Verifica se o contador de itens no carrinho foi atualizado
    public boolean isCartCounterUpdated() {
        System.out.println("Verificando atualização do contador do carrinho...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Aguarda até que o contador de itens no carrinho seja visível e tenha um valor maior que 0
            WebElement cartCounter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("totalp")));

            // Verificando se o número de itens foi atualizado corretamente
            int itemCount = Integer.parseInt(cartCounter.getText());
            System.out.println("Número de itens no carrinho: " + itemCount);
            return itemCount > 0;
        } catch (TimeoutException | NumberFormatException e) {
            System.err.println("Nenhum item detectado no carrinho ou erro ao obter o número de itens.");
            return false;
        }
    }

    public boolean isTotalPriceDisplayed() {
        System.out.println("Validando seletor para o preço total...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement totalPrice = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("totalp"))
            );

            // Verifique o conteúdo do elemento se necessário
            String totalText = totalPrice.getText();
            System.out.println("Preço total exibido: " + totalText);
            return totalText != null && !totalText.isEmpty();
        } catch (TimeoutException e) {
            System.err.println("Elemento de preço total não encontrado ou não visível.");
            return false;
        }
    }
    public void acceptAlert() {
        System.out.println("Aguardando alerta ser exibido...");
        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Aumentado o tempo de espera
        alertWait.until(ExpectedConditions.alertIsPresent());

        // Aceita o alerta
        driver.switchTo().alert().accept();
        System.out.println("Alerta aceito.");
    }

    public boolean alertPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Alerta detectado: " + alert.getText());
            alert.accept();
            return true;
        } catch (NoAlertPresentException e) {
            System.err.println("Nenhum alerta presente.");
            return false;
        }
    }


    public boolean isAddToCartButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton)).isDisplayed();
    }
    public boolean isCartUpdated() {
        System.out.println("Verificando atualização do carrinho...");
        try {
            WebElement cartUpdateMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartMessage"))); // Atualize o seletor conforme necessário
            System.out.println("Mensagem de atualização do carrinho detectada: " + cartUpdateMessage.getText());
            return true;
        } catch (TimeoutException e) {
            System.err.println("Nenhuma atualização detectada no carrinho.");
            return false;
        }
    }


    public boolean isItemInCart(String itemName) {
        By itemLocator = By.xpath("//td[contains(text(), '" + itemName + "')]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(itemLocator)).isDisplayed();
    }

    public boolean isProductVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    public WebDriver getDriver() {
        return this.driver;
    }

}