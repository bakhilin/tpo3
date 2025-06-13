package tpo3.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import tpo3.ForumPage;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tpo3.Utils;

public class AuthorizationTest {    
    
    private static final String LOGIN_ERROR = "Your login details were incorrect.";

    @Test
    public void loginByEmailTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage mainPage = new ForumPage(driver);
            driver.get(Utils.FORUM_URL);
            mainPage.doLogin(Utils.EMAIL);
            WebElement username = Utils.getElement(driver, ForumPage.USERNAME);
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
            WebElement username = Utils.getElement(driver, ForumPage.USERNAME);
            assertEquals(Utils.LOGIN, username.getText());
        });
        drivers.forEach(WebDriver::quit);
    }

    @Test
    public void logoutTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get(Utils.FORUM_URL);
            forumPage.doLogin(Utils.LOGIN);
            forumPage.doLogout();
            WebElement logout = Utils.getElement(driver, ForumPage.LOGOUT);
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
            WebElement error = Utils.getElement(driver, ForumPage.ERROR);
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
            WebElement error = Utils.getElement(driver, ForumPage.ERROR);
            assertEquals(LOGIN_ERROR, error.getText());
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
            WebElement error = Utils.getElement(driver, ForumPage.ERROR);
            assertEquals(LOGIN_ERROR, error.getText());
        });
        drivers.forEach(WebDriver::quit);
    }

}