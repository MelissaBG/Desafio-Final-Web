package stepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado; // Alterado para suporte ao português
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.junit.jupiter.api.Assertions;
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
            // Verifica se o botão "Adicionar ao carrinho" está visível
            Assertions.assertTrue(
                    productPage.isAddToCartButtonVisible(),
                    "Botão 'Adicionar ao carrinho' não está visível."
            );
            System.out.println("Botão 'Adicionar ao carrinho' encontrado e visível.");

            // Clica no botão para adicionar ao carrinho
            productPage.addMonitorToCart();
            System.out.println("Monitor adicionado ao carrinho.");

            // Espera até que a URL contenha o fragmento "#", indicando que o conteúdo foi atualizado dinamicamente
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.urlContains("#")); // Espera até que a URL tenha mudado

            // Aguarda até que o alerta esteja presente
            wait.until(ExpectedConditions.alertIsPresent());
            productPage.acceptAlert(); // Aceita o alerta

    }


    @Quando("vou para o carrinho")
    public void vouParaOCarrinho() {
        cartPage.accessCart();
        Assertions.assertTrue(cartPage.isCartPageLoaded(), "Página do carrinho não foi carregada corretamente.");
    }

    @Quando("finalizo a compra preenchendo os campos obrigatórios")
    public void finalizoACompraPreenchendoOsCamposObrigatorios() {
        cartPage.completePurchase();
        Assertions.assertTrue(checkoutPage.isCheckoutPage(), "Página de checkout não foi carregada corretamente.");
        checkoutPage.fillPurchaseFields("Test Name", "Brazil", "Test City", "1234 5678 9012 3456", "12", "2025");
        checkoutPage.confirmPurchase();
    }

    @Entao("a compra deve ser finalizada com sucesso")
    public void aCompraDeveSerFinalizadaComSucesso() {
        Assertions.assertTrue(checkoutPage.isPurchaseSuccessful(), "Compra não foi finalizada com sucesso!");
    }
}
