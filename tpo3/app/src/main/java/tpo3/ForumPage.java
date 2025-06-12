package tpo3;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForumPage extends Page {
    public ForumPage(WebDriver driver) {
        super(driver);
    }

    public void doLogin(){
        tryLogin(Utils.PASSWORD);
    }


    private void tryLogin(String password) {
        agreeCookies();
        // WebElement agree = Utils.getElement(driver, By.xpath("//*[@id=\"accept-btn\"]"));
        // agree.click();
        WebElement loginButton = Utils.getElement(driver, By.xpath("//*[@id=\"header-secondary\"]/ul/li[3]/button"));
        loginButton.click();
        WebElement loginInput = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div[2]/div[1]/input"));
        WebElement passwordInput = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div[2]/div[2]/input"));
        WebElement authButton = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div[2]/div[4]/button"));
        loginInput.clear();
        passwordInput.clear();
        loginInput.sendKeys(Utils.LOGIN);
        passwordInput.sendKeys(password);
        authButton.click();
    }

    public void doLogout() {
        Utils.getElement(driver, By.xpath("/html/body/div[3]/div[2]/header/div[2]/div[3]/ul/li[3]/div/button")).click();
        WebElement logoutButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/div[3]/div[2]/header/div[2]/div[3]/ul/li[3]/div/ul/li[4]/button")
                ));
        logoutButton.click();
    }

    public void doWrongLogin(){
        tryLogin(Utils.WRONG_PASSWORD);
    }

    public void agreeCookies(){
        WebElement agree = Utils.getElement(driver, By.xpath("//*[@id=\"accept-btn\"]"));
        agree.click();
    }
}
