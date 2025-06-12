package tpo3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends Page{
    
    private static final By headerInfo = By.xpath("//*[@id=\"smart-head\"]/div[1]/div/div[1]/div");
    private static final By footerInfo = By.xpath("/html/body/div[2]/footer/div/div/div[3]");

    public static final String HEADER = "Unbiased and independent advice on what to buy";
    public static final String FOOTER = "© Copyright 2025 Candr Media Group Limited, 44 Copperfield Street, London, SE10DY.";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    

    public void agreeCookies() {
        WebElement agree = Utils.getElement(driver, By.xpath("//*[@id=\"qc-cmp2-ui\"]/div[2]/div/button[2]"));
        agree.click();
    }

    public WebElement getHeaderInfo() {
        agreeCookies();
        WebElement headerInfo = Utils.getElement(driver, MainPage.headerInfo);
        
        return headerInfo;
    }

    public WebElement getFooterInfo() {
        agreeCookies();
        WebElement footerInfo = Utils.getElement(driver, MainPage.footerInfo);
        
        return footerInfo;
    }
}
