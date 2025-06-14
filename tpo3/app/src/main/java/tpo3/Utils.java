package tpo3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {
    public static final String URL = "https://www.trustedreviews.com";
    public static final String FORUM_URL = "https://forum.trustedreviews.com";
    public static final String ARTICLE_URL = "https://forum.trustedreviews.com/d/37-what-tvs-would-you-like-trusted-reviews-to-look-at";
    public static final String SETTINGS_URL = "https://forum.trustedreviews.com/settings";

    public static final String EMAIL = "mario.lawio@mail.ru";
    public static final String LOGIN = "ojito";
    public static final String PASSWORD = "sPii*9212";
    public static final String WRONG_PASSWORD = "312";
    public static final String WRONG_LOGIN = "ohito";
    public static final String WRONG_EMAIL = "mario@mail.ru";

    public static List<WebDriver> getDrivers(){
        List<WebDriver> drivers = new ArrayList<>();
        try {
            List<String> properties = Files.readAllLines(Paths.get("/home/bakhilin/Desktop/tpo3/tpo3/trustedreviews.properties"));
            for (String property : properties) {
                if (property.startsWith("WEB_DRIVER")) {
                    switch (property.toLowerCase().split("=")[1]) {
                        case "chrome":
                            drivers.add(getChromeDriver());
                            break;  
                        case "firefox":
                            drivers.add(getFirefoxDriver());
                            break; 
                        case "both":
                            drivers.add(getChromeDriver());
                            drivers.add(getFirefoxDriver());
                            break;  
                    }
                    return drivers;  
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading properties file", e);
        }
        throw new RuntimeException("No web driver found in properties file");
    }

    public static ChromeDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1400,900");
        options.addArguments("--disable-notifications");

        return new ChromeDriver(options);
    }

    public static FirefoxDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--window-size=1400,900");
        options.addArguments("--disable-notifications");

        return new FirefoxDriver();
    }

    
    public static WebElement getElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitUntilPageLoad(WebDriver driver, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(webDriver -> Objects.equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState"), "complete"));
    }

}
