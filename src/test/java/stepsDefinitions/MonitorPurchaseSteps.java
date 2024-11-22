package stepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado; // Alterado para suporte ao português
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductPage;
import utils.DriverManager;

public class MonitorPurchaseSteps {

    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Before
    public void setup() {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @After
    public void tearDown() {
        DriverManager.closeDriver();
    }

    @Dado("que estou na página inicial do DemoBlaze")
    public void queEstouNaPaginaInicialDoDemoBlaze() {
        driver.get("https://www.demoblaze.com/");

        // Espera até que a URL contenha o prefixo desejado
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("demoblaze.com/"));

        // Verifica se a página inicial foi carregada corretamente
        Assertions.assertTrue(homePage.isHomePageLoaded(), "Página inicial não foi carregada corretamente.");
    }

    @Quando("acesso a categoria de monitores")
    public void acessoACategoriaDeMonitores() {
        // Realiza o clique na categoria de monitores
        homePage.goToMonitorsCategory();

        // Aguarda que a URL contenha a parte esperada após o clique
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("demoblaze.com/"));

        // Agora verifica a presença de um elemento na nova página para garantir que ela foi carregada
        WebElement monitorCategoryHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='itemc']")));

        Assertions.assertNotNull(monitorCategoryHeader, "Categoria de Monitores não carregada corretamente");
    }

    @Quando("seleciono o primeiro monitor")
    public void selecionoOPrimeiroMonitor() {
        productPage.selectMonitor();
        Assertions.assertTrue(productPage.isProductVisible(), "Monitor selecionado não está visível.");
    }

    @Quando("adiciono o monitor ao carrinho")
    public void adicionoOMonitorAoCarrinho() {
        System.out.println("Iniciando o processo de adicionar o monitor ao carrinho...");

        // Clique no botão "Adicionar ao Carrinho"
        WebElement addToCartButton = driver.findElement(By.xpath("//*[@id='tbodyid']/div[2]/div/a"));
        addToCartButton.click();

        // Aguardar e aceitar o alerta
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // Espera o alerta
        wait.until(ExpectedConditions.alertIsPresent()); // Aguarda o alerta aparecer
        Alert alert = driver.switchTo().alert(); // Alterna para o alerta
        alert.accept();  // Aceita o alerta

        System.out.println("Alerta aceito com sucesso.");

        // Redireciona para a página do carrinho
        WebElement cartLink = driver.findElement(By.id("cartur")); // O ID do link para o carrinho
        cartLink.click();

        // Aguarda a página de carrinho carregar completamente
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("totalp")));  // Espera até o elemento do total de itens aparecer

        // Verifica o contador de itens no carrinho
        if (isCartCounterUpdated()) {
            System.out.println("Monitor adicionado ao carrinho com sucesso.");
        } else {
            System.out.println("Falha ao adicionar o monitor ao carrinho.");
        }
    }

    public boolean isCartCounterUpdated() {
        System.out.println("Verificando atualização do contador do carrinho...");
        try {
            WebElement cartCounter = driver.findElement(By.id("totalp"));
            int itemCount = Integer.parseInt(cartCounter.getText());  // Obtém o número de itens no carrinho
            System.out.println("Número de itens no carrinho: " + itemCount);
            return itemCount > 0;  // Verifica se o contador de itens foi atualizado
        } catch (Exception e) {
            System.err.println("Erro ao verificar o contador do carrinho: " + e.getMessage());
            return false;
        }
    }

    @Quando("finalizo a compra preenchendo os campos obrigatórios")
    public void finalizoACompraPreenchendoOsCamposObrigatorios() {
        System.out.println("Iniciando o processo de finalização da compra...");

        // Clica no botão "Place Order"
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@data-target='#orderModal']"));
        placeOrderButton.click();

        // Aguarda o pop-up do formulário ser exibido
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement orderModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderModal")));

        System.out.println("Modal de pedido exibido com sucesso.");

        // Preenche os campos obrigatórios no formulário
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement countryField = driver.findElement(By.id("country"));
        WebElement cityField = driver.findElement(By.id("city"));
        WebElement cardField = driver.findElement(By.id("card"));
        WebElement monthField = driver.findElement(By.id("month"));
        WebElement yearField = driver.findElement(By.id("year"));

        nameField.sendKeys("Test Name");
        countryField.sendKeys("Brazil");
        cityField.sendKeys("Test City");
        cardField.sendKeys("1234 5678 9012 3456");
        monthField.sendKeys("12");
        yearField.sendKeys("2025");

        System.out.println("Campos obrigatórios preenchidos.");

        // Clica no botão de confirmação
        WebElement purchaseButton = orderModal.findElement(By.xpath("//button[text()='Purchase']"));
        purchaseButton.click();

        // Aguarda a exibição da mensagem de sucesso
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sweet-alert")));

        // Verifica se a compra foi bem-sucedida
        Assertions.assertTrue(confirmationMessage.getText().contains("Thank you for your purchase!"),
                "Compra não foi finalizada com sucesso!");
        System.out.println("Compra finalizada com sucesso.");
    }


    @Entao("a compra deve ser finalizada com sucesso")
    public void aCompraDeveSerFinalizadaComSucesso() {
        Assertions.assertTrue(checkoutPage.isPurchaseSuccessful(), "Compra não foi finalizada com sucesso!");
    }
}
