package tpo3.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import tpo3.ForumPage;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tpo3.Utils;

public class AuthorizationTest {
    
    @Test
    public void loginByEmailTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage mainPage = new ForumPage(driver);
            driver.get(Utils.FORUM_URL);
            mainPage.doLogin(Utils.EMAIL);
            WebElement username = Utils.getElement(driver, By.xpath("/html/body/div[3]/div[2]/header/div[2]/div[3]/ul/li[3]/div/button/span[2]/span"));
            assertEquals(Utils.LOGIN, username.getText());
        });
        drivers.forEach(WebDriver::quit);
    }

    @Test
    public void loginByUsernameTest(){
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage mainPage = new ForumPage(driver);
            driver.get(Utils.FORUM_URL);
            mainPage.doLogin(Utils.LOGIN);
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
            forumPage.doLogin(Utils.LOGIN);
            forumPage.doLogout();
            WebElement logout = Utils.getElement(driver, By.xpath("/html/body/div[3]/div[2]/header/div[2]/div[3]/ul/li[3]/button/span"));
            assertEquals(logout.getText(), "Log In");
        });
        drivers.forEach(WebDriver::quit);        
    }

    @Test 
    void wrongUsernameTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get(Utils.FORUM_URL);
            forumPage.doWrongLogin(Utils.PASSWORD, Utils.WRONG_LOGIN);
            WebElement error = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div/span"));
            assertEquals("Your login details were incorrect.", error.getText());
        });
        drivers.forEach(WebDriver::quit);
    }

    @Test
    void wrongEmailTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get(Utils.FORUM_URL);
            forumPage.doWrongLogin(Utils.PASSWORD, Utils.WRONG_EMAIL);
            WebElement error = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div/span"));
            assertEquals("Your login details were incorrect.", error.getText());
        });
        drivers.forEach(WebDriver::quit);
    }

    @Test
    void wrongPasswordTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get(Utils.FORUM_URL);
            forumPage.doWrongLogin(Utils.LOGIN, Utils.WRONG_PASSWORD);
            WebElement error = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div/span"));
            assertEquals("Your login details were incorrect.", error.getText());
        });
        drivers.forEach(WebDriver::quit);
    }

}