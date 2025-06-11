package tpo3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
    public static final String CHROME_NAME = "webdriver.chrome.driver";
    public static final String CHROME_PATH = "/usr/bin/chromedriver";
    public static final String FIREFOX_NAME = "webdriver.gecko.driver";
    public static final String FIREFOX_PATH = "chromedriver-mac-arm64/geckodriver";

    public static final String URL = "https://www.trustedreviews.com";
    public static final String FORUM_URL = "https://forum.trustedreviews.com";

    public static final String EMAIL = "mario.lawio@mail.ru";
    public static final String LOGIN = "ojito";
    public static final String PASSWORD = "sPii*9212";
    public static final String WRONG_PASSWORD = "312";

    public static List<WebDriver> getDrivers(){
        List<WebDriver> drivers = new ArrayList<>();
        try {
            List<String> properties = Files.readAllLines(Paths.get("/home/bakhilin/tpo3/trustedreviews.properties"));
            for (String property : properties) {
                if (property.startsWith("WEB_DRIVER")) {
                    switch (property.toLowerCase().split("=")[1]) {
                        case "chrome":
                            drivers.add(getChromeDriver());
                            break;  // Changed from return to break
                        case "firefox":
                            drivers.add(getFirefoxDriver());
                            break;  // Changed from return to break
                        case "both":
                            drivers.add(getChromeDriver());
                            drivers.add(getFirefoxDriver());
                            break;  // Changed from return to break
                    }
                    return drivers;  // Moved return here
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading properties file", e);
        }
        throw new RuntimeException("No web driver found in properties file");
    }

    public static ChromeDriver getChromeDriver() {
    
        return new ChromeDriver();
            
    }

    public static FirefoxDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        
        return new FirefoxDriver();
    }

    public static void prepareDrivers() {
        System.setProperty(CHROME_NAME, CHROME_PATH);
        System.setProperty(FIREFOX_NAME, FIREFOX_PATH);
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
