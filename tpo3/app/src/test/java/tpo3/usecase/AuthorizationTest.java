package tpo3.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import tpo3.ForumPage;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import tpo3.Utils;

public class AuthorizationTest {
    

    @Test
    public void loginTest() {
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

    @Test
    public void logoutTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get("https://forum.trustedreviews.com/");
            forumPage.doLogin();
            forumPage.doLogout();
            WebElement logout = Utils.getElement(driver, By.xpath("/html/body/div[3]/div[2]/header/div[2]/div[3]/ul/li[3]/button/span"));
            assertEquals(logout.getText(), "Log In");
        });
        drivers.forEach(WebDriver::quit);        
    }

    @Test
    void wrongLoginTest(){
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get("https://forum.trustedreviews.com/");
            forumPage.doWrongLogin();
            WebElement error = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div/span"));
            assertEquals("Your login details were incorrect.", error.getText());
        });
        drivers.forEach(WebDriver::quit);
    }
}