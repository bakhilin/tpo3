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
            driver.get(Utils.ARTICLE_URL);
            forumPage.doLogin(Utils.LOGIN);
            WebElement followBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[2]/div/button[1]"));
            followBtn.click();
            
            WebElement result = forumPage.followOnDiscussion();
            
            assertEquals("Following", result.getText());
            
            forumPage.resetChangesBtn("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[2]/div/button[1]");
        });

        drivers.forEach(WebDriver::quit);
    }

    @Test 
    void unFollowDiscussion(){
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get(Utils.ARTICLE_URL);
            forumPage.doLogin(Utils.LOGIN);

            WebElement result = forumPage.unFollowDiscussion();
            result.click();
            assertEquals("Follow", result.getText());
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
            driver.get(Utils.ARTICLE_URL);
            forumPage.likeComment();
            WebElement checkLike = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/div/div/div[2]/article/div/aside/ul/li[3]/button/span"));
            assertEquals("Unlike", checkLike.getText());
            forumPage.resetChangesBtn("/html/body/div[3]/main/div[1]/div/div/div/div/div/div/div[2]/article/div/aside/ul/li[3]/button/span");
        });

        drivers.forEach(WebDriver::quit);
    }

    @Test 
    void setFlagTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get(Utils.ARTICLE_URL);
            forumPage.setFlag();
        });
        
        drivers.forEach(WebDriver::quit);
    }


    @Test
    void replyTest(){
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get(Utils.ARTICLE_URL);
            forumPage.reply();            
            
            WebElement comment = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/div/div/div[27]/article/div/div/p"));
            
            assertEquals("Hello, Guys! Really interesting discussion.", comment.getText());
        });

        drivers.forEach(WebDriver::quit);
    }

    
    @Test 
    void startDiscussionTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get("https://forum.trustedreviews.com/");            
            
            forumPage.startDiscussion();

            WebElement checkPostedTitle = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/a/div/div[2]/div[1]/div[1]/h2"));
            WebElement checkPostedBody = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/a/div/div[2]/div[2]"));

            assertEquals(checkPostedTitle.getText(), "Test");
            assertEquals(checkPostedBody.getText(), "Some post body Test");
            // rename, restore, delete
        });

        drivers.forEach(WebDriver::quit);
    }
}
