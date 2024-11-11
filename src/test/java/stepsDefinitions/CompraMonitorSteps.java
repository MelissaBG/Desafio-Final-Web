package stepsDefinitions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductPage;
import utils.DriverManager;

public class CompraMonitorSteps {

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

    @Given("que estou na página inicial do DemoBlaze")
    public void queEstouNaPaginaInicialDoDemoBlaze() {
        driver.get("https://www.demoblaze.com/index.html");
    }

    @When("acesso a categoria de monitores")
    public void acessoACategoriaDeMonitores() {
        homePage.acessarCategoriaMonitor();
    }

    @When("seleciono o primeiro monitor")
    public void selecionoOPrimeiroMonitor() {
        homePage.selecionarMonitor();
    }

    @When("adiciono o monitor ao carrinho")
    public void adicionoOMonitorAoCarrinho() {
        productPage.adicionarMonitorAoCarrinho();
    }

    @When("vou para o carrinho")
    public void vouParaOCarrinho() {
        cartPage.acessarCarrinho();
    }

    @When("finalizo a compra preenchendo os campos obrigatórios")
    public void finalizoACompraPreenchendoOsCamposObrigatorios() {
        cartPage.finalizarCompra();
        checkoutPage.preencherInformacoesDeCompra("Nome Teste", "Brasil", "Cidade Teste", "1234 5678 9012 3456", "12", "2025");
        checkoutPage.confirmarCompra();
    }

    @Then("a compra deve ser finalizada com sucesso")
    public void aCompraDeveSerFinalizadaComSucesso() {
        Assertions.assertTrue(checkoutPage.isCompraSucesso(), "Compra não realizada com sucesso!");
    }
}
