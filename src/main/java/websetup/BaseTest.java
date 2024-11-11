package websetup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = DriverManager.getDriver();
        driver.get("https://www.demoblaze.com/index.html");
    }

    @AfterEach
    public void tearDown() {
        DriverManager.closeDriver();
    }
}

