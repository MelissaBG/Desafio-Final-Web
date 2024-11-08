package websetup;

import utils.DriverManager;

//base para a configuração e finalização do navegador
public class BaseTest {
    @BeforeEach
    public void setUp() {
        DriverManager.getDriver().get("https://www.demoblaze.com/");
    }

    @AfterEach
    public void tearDown() {
       DriverManager.closeDriver();
    }
}
