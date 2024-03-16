package com.isratshimu.Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class TestBase {
    public static WebDriver driver;
    public static void launchWebBrowser() {
//        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        WebDriverManager.firefoxdriver().setup();

        /*Turn off browser notifications*/
        FirefoxOptions option = new FirefoxOptions();
        option.addPreference("dom.webnotifications.enabled", false);
        option.addPreference("app.update.enabled", false);
        option.addPreference("geo.enabled", false);

        driver = new FirefoxDriver(option);
        driver.manage().window().maximize();
        System.out.println("Web Browser Launched Successfully!");
    }
    public static void closeBrowser() {
        driver.close();
        System.out.println("Browser Closed!");
    }

}