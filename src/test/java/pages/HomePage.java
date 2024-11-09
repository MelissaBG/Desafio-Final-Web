package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
        private WebDriver driver;
    // Localizadores
    private By homeLink = By.id("nava");

    // Construtor

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    // Acessa a página inicial
    public void goToHomePage() {
        driver.findElement(homeLink).click();
    }



}