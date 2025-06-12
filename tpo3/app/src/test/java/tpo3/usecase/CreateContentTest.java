package tpo3.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
            WebElement unfollowBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[2]/div/button[1]"));
            unfollowBtn.click();
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
            WebElement text = Utils.getElement(driver, By.xpath("//*[@id=\"content\"]/div/div/div/div/nav/ul/li[2]/div/button[1]/span"));
            assertEquals("Following", text.getText());
            WebElement unfollowBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[2]/div/button[1]"));
            unfollowBtn.click();
            WebElement textUnfollow = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[2]/div/button[1]/span"));
            assertEquals("Follow", textUnfollow.getText());
            WebElement followBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[2]/div/button[1]"));
            followBtn.click();
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
            WebElement unlike = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/div/div/div[2]/article/div/aside/ul/li[3]/button/span"));
            unlike.click();
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


    @Test
    void replyTest(){
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get("https://forum.trustedreviews.com/d/37-what-tvs-would-you-like-trusted-reviews-to-look-at");
            forumPage.doLogin();
            driver.navigate().refresh();
            WebElement replyBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/div/div/div[1]/article/div/aside/ul/li[2]/button"));
            replyBtn.click();
            WebElement textArea = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[4]/div/div/div/div[2]/div/div[1]/div/div/div/div/textarea"));
            textArea.clear();
            String testText = "Hello, Guys! Really interesting discussion.";
            textArea.sendKeys(testText);    
            WebElement sendTextBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[4]/div/div/div/div[2]/div/div[1]/div/div/ul/li[1]/button"));
            sendTextBtn.click();
            WebElement comment = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/div/div/div[27]/article/div/div/p"));
            
            assertEquals("Hello, Guys! Really interesting discussion.", comment.getText());
        });

        drivers.forEach(WebDriver::quit);
    }

    
    @Test 
    void startDiscussion() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get("https://forum.trustedreviews.com/");
            forumPage.doLogin();
            driver.navigate().refresh();    
            WebElement startDiscussionBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div[2]/div[2]/nav/ul/li[2]/button"));
            startDiscussionBtn.click();
        });

        drivers.forEach(WebDriver::quit);
    }
}
