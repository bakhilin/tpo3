package tpo3.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.helpers.Util;

import io.github.bonigarcia.wdm.WebDriverManager;
import tpo3.ForumPage;
import tpo3.Utils;

public class CreateContentTest {

    @Test
    void followOnDiscussion(){
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get("https://forum.trustedreviews.com/d/37-what-tvs-would-you-like-trusted-reviews-to-look-at");
            forumPage.doLogin();
            WebElement followBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[2]/div/button[1]"));
            followBtn.click();
            WebElement text = Utils.getElement(driver, By.xpath("//*[@id=\"content\"]/div/div/div/div/nav/ul/li[2]/div/button[1]/span"));
            assertEquals("Following", text.getText());
        });

        drivers.forEach(WebDriver::quit);
    }

    @Test 
    void unfollowDiscussion(){
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get("https://forum.trustedreviews.com/d/37-what-tvs-would-you-like-trusted-reviews-to-look-at");
            forumPage.doLogin();
            WebElement followBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[2]/div/button[1]"));
            followBtn.click();
            WebElement text = Utils.getElement(driver, By.xpath("//*[@id=\"content\"]/div/div/div/div/nav/ul/li[2]/div/button[1]/span"));
            assertEquals("Following", text.getText());
            WebElement unfollowBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[2]/div/button[1]"));
            unfollowBtn.click();
            WebElement textUnfollow = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[2]/div/button[1]/span"));
            assertEquals("Follow", textUnfollow.getText());
        });
        
        drivers.forEach(WebDriver::quit);
    }

    @Test
    void likeCommentTest(){
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get("https://forum.trustedreviews.com/d/37-what-tvs-would-you-like-trusted-reviews-to-look-at");
            forumPage.doLogin();
            WebElement likeBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/div/div/div[2]/article/div/aside/ul/li[3]/button"));
            likeBtn.click();
            WebElement checkLike = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/div/div/div[2]/article/div/aside/ul/li[3]/button/span"));
            assertEquals("Unlike", checkLike.getText());
        });

        drivers.forEach(WebDriver::quit);
    }

    @Test 
    void setFlagTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get("https://forum.trustedreviews.com/d/37-what-tvs-would-you-like-trusted-reviews-to-look-at");
            forumPage.doLogin();
            WebElement flagBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/div/div/div[1]/article/div/aside/ul/li[4]/div/button"));
            flagBtn.click();
            
            WebElement openFlag = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/div/div/div[1]/article/div/aside/ul/li[4]/div/ul/li/button"));
            openFlag.click();
            WebElement offTopicBtn = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div/div[1]/div/label[1]/input"));
            offTopicBtn.click();
            WebElement flagPost = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div/div[2]/button"));
            flagPost.click();
        });
        
        drivers.forEach(WebDriver::quit);
    }

    // @Test
    // void replyTest(){
    //     WebDriver driver = new ChromeDriver();
    //     ForumPage forumPage = new ForumPage(driver);
    //     driver.get("https://forum.trustedreviews.com/d/37-what-tvs-would-you-like-trusted-reviews-to-look-at");
    //     forumPage.doLogin();
    //     By replyButtonLocator = By.cssSelector("button.SplitDropdown-button.Button--primary .Button-label");

    //     // Ждём, пока страница стабилизируется
    //     new WebDriverWait(driver, Duration.ofSeconds(10)).until(
    //         d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete")
    //     );

    //     // Кликаем с защитой от StaleElement
    //     WebElement replyButton = new WebDriverWait(driver, Duration.ofSeconds(10))
    //         .until(ExpectedConditions.refreshed(
    //             ExpectedConditions.elementToBeClickable(replyButtonLocator)
    //         ));
    //     replyButton.click();
    //     // WebElement replyBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[1]/div/button[1]"));
    //     // replyBtn.click();
    //     // WebElement replyBtn = driver.findElement(By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[1]/div/button[1]"));
    //     // replyBtn.click();
    //     // driver.quit();
    // }

    

    // @Test
    // void startDiscussionTest() {
    //     WebDriver driver = new ChromeDriver();
    //     ForumPage forumPage = new ForumPage(driver);
    //     driver.get("https://forum.trustedreviews.com/");
    //     forumPage.doLogin();
      
    //     Actions actions = new Actions(driver);
    //     actions.moveToElement(Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div[2]/div[2]/nav/ul/li[2]/button"))).click().perform();
    //     WebElement title = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[4]/div/div/div/div[2]/div/div[1]/ul/li[3]/h3/input"));
    //     title.clear();
    //     title.sendKeys("Test Title");
    //     WebElement body = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[4]/div/div/div/div[2]/div/div[1]/div/div/div/div/textarea"));
    //     body.clear();
    //     body.sendKeys("test body text");
    //     WebElement btn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[4]/div/div/div/div[2]/div/div[1]/div/div/ul/li[1]/button"));
    //     btn.click();
    //     driver.quit();
    // }
}
