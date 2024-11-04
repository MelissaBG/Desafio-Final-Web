package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class OpenSiteSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given("que o usuário acessa o site DemoBlaze")
    public void que_o_usuário_acessa_o_site_DemoBlaze() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/index.html");
    }

    @Then("o site deve ser carregado com sucesso")
    public void o_site_deve_ser_carregado_com_sucesso() {
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nava")));
        assertTrue(logo.isDisplayed());
        driver.quit();
    }
}
