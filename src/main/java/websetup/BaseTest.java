package websetup;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected WebDriver driver;

    public void setup() {
        // Usando WebDriverManager para gerenciar automaticamente o driver do Chrome
        WebDriverManager.chromedriver().setup();

        // Inicialize o WebDriver
        driver = new ChromeDriver();

        // Defina uma URL para abrir
        driver.get("https://www.demoblaze.com");
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

