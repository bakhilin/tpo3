package tpo3.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tpo3.ForumPage;
import tpo3.Utils;

public class EditTest {
    
    @Test
    void editEmailTest(){
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            ForumPage forumPage = new ForumPage(driver);
            driver.get(Utils.FORUM_URL);
            forumPage.doLogin(Utils.LOGIN);
            Utils.getElement(driver, By.xpath("/html/body/div[3]/div[2]/header/div[2]/div[3]/ul/li[3]/div/button")).click();
            Utils.getElement(driver, By.xpath("//*[@id=\"header-secondary\"]/ul/li[3]/div/ul/li[2]/a[1]")).click();
            WebElement username = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div[1]/div/div/div/h1/span"));
            
            assertEquals(username.getText(), Utils.LOGIN);
            forumPage.editEmail(Utils.EMAIL);

            Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div[2]/div/div/div/ul/li[1]/fieldset/ul/li[2]/button")).click();
            WebElement checkEmail = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div/div[1]/input"));
            assertEquals(checkEmail.getDomAttribute("placeholder"), Utils.EMAIL);
        });

        drivers.forEach(WebDriver::quit);
    }
}
