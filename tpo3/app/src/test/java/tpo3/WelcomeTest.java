package tpo3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class WelcomeTest {
    
    @Test
    void checkHeaderMainPage(){
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            driver.get(Utils.URL);
            assertEquals(mainPage.getHeaderInfo().getText(), MainPage.HEADER);
        });

        drivers.forEach(WebDriver::quit);
    }

    @Test
    void checkFooterMainPage(){
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            driver.get(Utils.URL);
            assertEquals(mainPage.getFooterInfo().getText(), MainPage.FOOTER);
        });

        drivers.forEach(WebDriver::quit);
    }
}
