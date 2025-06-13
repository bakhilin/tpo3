package tpo3.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tpo3.ForumPage;
import tpo3.Utils;


public class ForumMainPageTest {
    
    @Test
    void checkOldestFilterTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            driver.get(Utils.FORUM_URL);
            ForumPage forumPage = new ForumPage(driver);
            forumPage.agreeCookies();
            WebElement filterBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div[2]/div[2]/div[1]/div[1]/ul[1]/li/div/button"));
            filterBtn.click();
            WebElement oldestBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div[2]/div[2]/div[1]/div[1]/ul[1]/li/div/ul/li[4]/button"));
            oldestBtn.click();
            WebElement checkDate = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/a/div/div[1]/div/div/div[2]"));
            assertEquals(checkDate.getText(), "10 Mar");
        });
    
        drivers.forEach(WebDriver::quit);
    }

    @Test
    void checkTopFilterTest(){
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get(Utils.FORUM_URL);
            forumPage.agreeCookies();
            WebElement filterBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div[2]/div[2]/div[1]/div[1]/ul[1]/li/div/button"));
            filterBtn.click();
            WebElement topBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div[2]/div[2]/div[1]/div[1]/ul[1]/li/div/ul/li[2]/button"));
            topBtn.click();
            WebElement title = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/a/div/div[2]/div[1]/div[1]/h2"));
            assertEquals(title.getText(), "Competition time: Win £250/$250/€250");
        });

        drivers.forEach(WebDriver::quit);
    }

    @Test
    void searchInputTest(){
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get(Utils.FORUM_URL);
            forumPage.agreeCookies();
            WebElement input = Utils.getElement(driver, By.xpath("/html/body/div[3]/div[2]/header/div[2]/div[3]/ul/li[1]/div/div/input"));
            input.clear();
            input.sendKeys("Competition time: Win £250/$250/€250");
            input.sendKeys(Keys.RETURN);
            WebElement title = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/a/div/div[2]/div[1]/div[1]/h2"));
            assertEquals("Competition time: Win £250/$250/€250", title.getText());
        });

        drivers.forEach(WebDriver::quit);
    }

    @Test 
    void checkNotificationEmptyTest(){
        List<WebDriver> drivers = Utils.getDrivers();   
        drivers.parallelStream().forEach(driver -> {
            driver.get(Utils.FORUM_URL);
            ForumPage forumPage = new ForumPage(driver);
            forumPage.doLogin(Utils.LOGIN);
            WebElement notificationBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/div[2]/header/div[2]/div[3]/ul/li[2]/div/button"));
            notificationBtn.click();
            WebElement text = Utils.getElement(driver, By.xpath("/html/body/div[3]/div[2]/header/div[2]/div[3]/ul/li[2]/div/div/div/div[2]/div"));
            assertEquals("No Notifications", text.getText());
        }); 

        drivers.forEach(WebDriver::quit);
    }
}

