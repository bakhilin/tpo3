package tpo3.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import tpo3.ForumPage;
import tpo3.MainPage;
import tpo3.Utils;


public class SimpleTest {
    public static final String FIREFOX_NAME = "webdriver.gecko.driver";
    public static final String FIREFOX_PATH = "/usr/bin/geckodriver";


    @Test
    public void testLogin(){
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage mainPage = new ForumPage(driver);
            driver.get(Utils.FORUM_URL);
            mainPage.doLogin();
            WebElement username = Utils.getElement(driver, By.xpath("/html/body/div[3]/div[2]/header/div[2]/div[3]/ul/li[3]/div/button/span[2]/span"));
            assertEquals(Utils.LOGIN, username.getText());
        });
        drivers.forEach(WebDriver::quit);
    }

    // logout 
    @Test 
    public void testLogout(){

    }

    
}
