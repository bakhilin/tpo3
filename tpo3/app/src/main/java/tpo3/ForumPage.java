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

    public void doLogin(String login){
        tryLogin(Utils.PASSWORD, login);
    }


    private void tryLogin(String password, String login) {
        agreeCookies();
        WebElement loginButton = Utils.getElement(driver, By.xpath("//*[@id=\"header-secondary\"]/ul/li[3]/button"));
        loginButton.click();
        WebElement loginInput = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div[2]/div[1]/input"));
        WebElement passwordInput = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div[2]/div[2]/input"));
        WebElement authButton = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div[2]/div[4]/button"));
        loginInput.clear();
        passwordInput.clear();
        loginInput.sendKeys(login);
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

    public void doWrongLogin(String login, String password){
        tryLogin(password, login);
    }

    public void agreeCookies(){
        WebElement agree = Utils.getElement(driver, By.xpath("//*[@id=\"accept-btn\"]"));
        agree.click();
    }

    public void editEmail(String newEmail){
        Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div[2]/div/div/div/ul/li[1]/fieldset/ul/li[2]/button")).click();
        WebElement inputEmail = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div/div[1]/input"));
        WebElement inputPassword = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div/div[2]/input"));
        WebElement submit = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div/div[3]/button"));

        inputEmail.clear();
        inputEmail.sendKeys(newEmail);

        inputPassword.clear();
        inputPassword.sendKeys(Utils.PASSWORD);

        submit.click();
    }

    public WebElement followOnDiscussion(){
        // doLogin(Utils.LOGIN);
        WebElement followBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[2]/div/button[1]"));
        followBtn.click();
        WebElement text = Utils.getElement(driver, By.xpath("//*[@id=\"content\"]/div/div/div/div/nav/ul/li[2]/div/button[1]/span"));
        
        return text;
    }

    public WebElement unFollowDiscussion(){
        WebElement unfollowBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[2]/div/button[1]"));
        unfollowBtn.click();
        WebElement textUnfollow = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/nav/ul/li[2]/div/button[1]"));
        
        return textUnfollow;
    }

    public void resetChangesBtn(String xpath) {
        WebElement unfollowBtn = Utils.getElement(driver, By.xpath(xpath));
        unfollowBtn.click();
    }

    public void likeComment() {
        doLogin(Utils.LOGIN);
        WebElement likeBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/div/div/div[2]/article/div/aside/ul/li[3]/button"));
        likeBtn.click();
    }

    public void setFlag(){
        doLogin(Utils.LOGIN);
        WebElement flagBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/div/div/div[1]/article/div/aside/ul/li[4]/div/button"));
        flagBtn.click();    
        WebElement openFlag = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/div/div/div[1]/article/div/aside/ul/li[4]/div/ul/li/button"));
        openFlag.click();
        WebElement offTopicBtn = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div/div[1]/div/label[1]/input"));
        offTopicBtn.click();
        WebElement flagPost = Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div/div[2]/button"));
        flagPost.click();
    }

    public void reply(){
        doLogin(Utils.LOGIN);
        driver.navigate().refresh();
        WebElement replyBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div/div/div/div/div/div[1]/article/div/aside/ul/li[2]/button"));
        replyBtn.click();
        WebElement textArea = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[4]/div/div/div/div[2]/div/div[1]/div/div/div/div/textarea"));
        textArea.clear();
        String testText = "Hello, Guys! Really interesting discussion.";
        textArea.sendKeys(testText);    
        WebElement sendTextBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[4]/div/div/div/div[2]/div/div[1]/div/div/ul/li[1]/button"));
        sendTextBtn.click();
    }

    public void startDiscussion(){
        doLogin(Utils.LOGIN);
        driver.navigate().refresh();    
        WebElement startDiscussionBtn = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[1]/div/div[2]/div[2]/nav/ul/li[2]/button"));
        startDiscussionBtn.click();
        WebElement title = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[4]/div/div/div/div[2]/div/div[1]/ul/li[3]/h3/input"));
        WebElement writePost = Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[4]/div/div/div/div[2]/div/div[1]/div/div/div/div/textarea"));

        title.clear();
        writePost.clear();
        
        title.sendKeys("Test");
        writePost.sendKeys("Some post body Test");

        Utils.getElement(driver, By.xpath("/html/body/div[3]/main/div[4]/div/div/div/div[2]/div/div[1]/div/div/ul/li[1]/button")).click();
        Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[3]/ul/li[1]")).click();
        Utils.getElement(driver, By.xpath("/html/body/div[4]/div[1]/div[1]/div/form/div[2]/div/div[2]/button")).click();
        driver.navigate().refresh();    
    }
}
