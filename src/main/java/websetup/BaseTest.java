package websetup;

import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class BaseTest {

    protected WebDriver driver;
    public void setup() {
        driver = DriverManager.getDriver(); // Obter driver do DriverManager
        driver.get("https://www.demoblaze.com");
    }

    public void tearDown() {
        DriverManager.closeDriver(); // Fechar driver centralizado no DriverManager
    }

}

