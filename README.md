
# **Projeto de Automação de Testes - DemoBlaze**

Este projeto utiliza **Cucumber**, **Selenium** e **JUnit** para automatizar testes de UI no site **DemoBlaze**. O objetivo principal é garantir que o site funcione corretamente em diferentes cenários de interação, como acessar o site, navegar entre categorias e realizar compras.

## **Tecnologias Utilizadas**

- **Cucumber**: Framework de testes Behavior-Driven Development (BDD) para definir cenários de teste de forma legível.
- **Selenium WebDriver**: Biblioteca para automatizar interações com o navegador e executar testes de UI.
- **JUnit**: Framework para execução de testes automatizados e asserções.

## **Estrutura do Projeto**

O projeto segue a estrutura de **Cucumber**, onde os testes são descritos em arquivos `.feature` e os steps de automação são definidos em arquivos `.java`.

### **Pastas principais do projeto**:
- **`src/main/java/stepsDefinitions`**: Contém as definições dos steps de automação.
- **`src/test/resources`**: Contém os arquivos `.feature` que descrevem os cenários de teste.
- **`src/main/java/pages`**: Contém as páginas de objetos (Page Object Model) que facilitam a interação com os elementos das páginas.
- **`src/main/java/utils`**: Contém utilitários, como a classe para gerenciar o driver do Selenium.

---

## **Funcionalidade do Teste**

### **1. Teste de Abertura do Site (Smoke Test)**

Este teste verifica se o site **DemoBlaze** carrega corretamente.

#### Gherkin (Cenário de Teste):
```gherkin
@SmokeTest
Feature: Abrir o Site DemoBlaze

  Scenario: Abrir a página inicial
    Given que eu abra a página inicial do DemoBlaze
    Then a página inicial deve ser carregada corretamente
```

#### Steps de Automação (`OpenSiteSteps.java`):
```java
package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.DriverManager;

public class OpenSiteSteps {

    private WebDriver driver;
    private HomePage homePage;

    public OpenSiteSteps() {
        this.driver = DriverManager.getDriver();
        this.homePage = new HomePage(driver);
    }

    @Given("que eu abra a página inicial do DemoBlaze")
    public void openDemoBlazeSite() {
        // Acessa a página inicial do DemoBlaze
        homePage.goToHomePage();
    }

    @Then("a página inicial deve ser carregada corretamente")
    public void verificarPaginaCarregada() {
        // Verifica se a URL da página inicial está correta
        String expectedUrl = "https://www.demoblaze.com/";
        String currentUrl = driver.getCurrentUrl();

        // Asserção que valida se a URL é a esperada
        Assertions.assertEquals(expectedUrl, currentUrl, "A URL da página inicial não corresponde à esperada.");
    }
}
```

### **2. Teste de Compra de Monitor**

Este teste automatiza o processo de navegação no site, seleção de um monitor, adição ao carrinho e finalização da compra.

#### Gherkin (Cenário de Teste):
```gherkin
Feature: Realizar uma compra de monitor no DemoBlaze

  Scenario: Realizar uma compra de monitor no DemoBlaze
    Given que estou na página inicial do DemoBlaze
    When acesso a categoria de monitores
    And seleciono o primeiro monitor
    And adiciono o monitor ao carrinho
    And finalizo a compra preenchendo os campos obrigatórios
    Then a compra deve ser finalizada com sucesso
```

#### Steps de Automação (`MonitorPurchaseSteps.java`):
```java
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
        // Inicializa as páginas e o driver antes de cada cenário
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @After
    public void tearDown() {
        // Fecha o driver após a execução do teste
        DriverManager.closeDriver();
    }

    @Dado("que estou na página inicial do DemoBlaze")
    public void queEstouNaPaginaInicialDoDemoBlaze() {
        // Acessa a página inicial do DemoBlaze
        driver.get("https://www.demoblaze.com/");
        
        // Espera que a URL da página seja carregada
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("demoblaze.com/"));
        
        // Valida que a página inicial foi carregada corretamente
        Assertions.assertTrue(homePage.isHomePageLoaded(), "Página inicial não foi carregada corretamente.");
    }

    @Quando("acesso a categoria de monitores")
    public void acessoACategoriaDeMonitores() {
        // Navega para a categoria de monitores
        homePage.goToMonitorsCategory();
        
        // Espera que a categoria de monitores esteja visível
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("demoblaze.com/"));
        WebElement monitorCategoryHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='itemc']")));
        
        // Valida se a categoria foi carregada corretamente
        Assertions.assertNotNull(monitorCategoryHeader, "Categoria de Monitores não carregada corretamente");
    }

    @Quando("seleciono o primeiro monitor")
    public void selecionoOPrimeiroMonitor() {
        // Seleciona o primeiro monitor da lista
        productPage.selectMonitor();
        
        // Valida se o produto foi selecionado corretamente
        Assertions.assertTrue(productPage.isProductVisible(), "Monitor selecionado não está visível.");
    }

    @Quando("adiciono o monitor ao carrinho")
    public void adicionoOMonitorAoCarrinho() {
        // Adiciona o monitor ao carrinho de compras
        WebElement addToCartButton = driver.findElement(By.xpath("//*[@id='tbodyid']/div[2]/div/a"));
        addToCartButton.click();
        
        // Espera pela confirmação da adição ao carrinho
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        
        // Navega para a página do carrinho
        WebElement cartLink = driver.findElement(By.id("cartur"));
        cartLink.click();
        
        // Espera que o carrinho seja carregado corretamente
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("totalp")));
    }

    @Quando("finalizo a compra preenchendo os campos obrigatórios")
    public void finalizoACompraPreenchendoOsCamposObrigatorios() {
        // Inicia o processo de finalização de compra
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@data-target='#orderModal']"));
        placeOrderButton.click();
        
        // Espera pela visibilidade do modal de finalização de pedido
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement orderModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderModal")));
        
        // Preenche os campos obrigatórios para finalizar a compra
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

        // Clica no botão de finalizar compra
        WebElement purchaseButton = orderModal.findElement(By.xpath("//button[text()='Purchase']"));
        purchaseButton.click();

        // Espera pela mensagem de confirmação da compra
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sweet-alert")));
        
        // Valida se a compra foi finalizada com sucesso
        Assertions.assertTrue(confirmationMessage.getText

().contains("Thank you for your purchase!"), "Compra não finalizada com sucesso.");
    }

    @Entao("a compra deve ser finalizada com sucesso")
    public void aCompraDeveSerFinalizadaComSucesso() {
        // Este passo já está validado no método de finalização da compra
    }
}
```
#### **TestRunner.java**
Classe responsável por orquestrar a execução de todas as etapas da automação.

## **Conclusão**

Neste projeto, foi abordado o uso de Cucumber com Selenium para automação de testes em um site de e-commerce. O exemplo demonstrado aqui reflete a interação com a página inicial, categorias de produtos e o processo de compra. Com o uso de testes automatizados, é possível garantir que a funcionalidade do site seja validada de forma eficiente.

