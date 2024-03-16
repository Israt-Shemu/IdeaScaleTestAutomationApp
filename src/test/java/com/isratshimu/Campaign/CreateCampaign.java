package com.isratshimu.Campaign;

import com.isratshimu.Base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class CreateCampaign extends TestBase {
    public static final String campaignName = "Campaign By IsratJahanShemu";
    public static final String ideaTitle = "Idea of IsratJahanShemu";
    public static final String BASE_URL = "https://trialqa.ideascale.com/";

    @BeforeMethod
    public void initSetUp() {
        launchWebBrowser();
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser();
    }

    @Test
    public static void Test1CreateCampaign() {
        login();
        createNewCampaign();
    }

    @Test
    public static void Test2CreateIdea() {
        login();
        createNewIdea();
    }

    @Test
    public static void Test3UpvoteTheIdea() {
        login();
        upvoteOnIdea();
    }

    @Test
    public static void Test4CommentOnTheIdea() {
        login();
        commentOnIdea();
    }

    public static void login() {
        driver.get(BASE_URL);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        /*Accept Cookies*/
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("button[class='btn btn-primary flex-fill json-link']")))).click();
        driver.get(BASE_URL);

        WebElement Email = driver.findElement(By.id("login-email"));
        Email.sendKeys("trialqa.ideascale@gmail.com");

        WebElement Password = driver.findElement(By.id("login-password"));
        Password.sendKeys("a@123456#");

        WebElement LoginBtn = driver.findElement(By.cssSelector("button[class='btn btn-xl btn-primary btn-block ga-tracker disabled-when-loading btn-captcha'"));
        LoginBtn.click();
    }

    public static void createNewCampaign() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));

        /*Click Trial QA Dropdown*/
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("topbar-avatar")))).click();

        /*Click Community Settings*/
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"utb-user-menu\"]/ul/li[2]/a")))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        /*Get all the current tabs open*/
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        /*Click Engagement*/
        driver.findElement(By.cssSelector("#heading-engagement > h4 > a")).click();

        /*Click Campaigns*/
        driver.findElement(By.xpath("//*[@id=\"main-nav-engagement\"]/div")).click();

        /*Click Add New Campaign*/
        By campaign = By.xpath("/html/body/div[5]/div[1]/div/section/div[2]/div[1]/a[2]");
        WebElement campaignElement = wait.until(ExpectedConditions.elementToBeClickable(campaign));
        campaignElement.click();

        /*Provide Campaign Name*/
        WebElement Name = driver.findElement(By.id("category-name-field"));
        Name.sendKeys(campaignName);

        /*Click dropdown right to Save button*/
        driver.findElement(By.xpath("//*[@id=\"tab-campaign\"]/div[2]/div/div/button")).click();

        /*Click Save and Continue*/
        driver.findElement(By.xpath("//*[@id=\"tab-campaign\"]/div[2]/div/div/div/button")).click();

        /*Click Schedule*/
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"tab-campaign\"]/div[1]/ul/li[7]")))).click();
        /*Launch Immediately*/
        driver.findElement(By.xpath("//*[@id=\"campaign-form\"]/div/div[2]/section[1]/div[2]/div[2]/button")).click();
        /*Back to Home*/
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public static void createNewIdea() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));

        /* wait for the home page to be fully loaded */
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("topbar-avatar"))));

        driver.get("https://trialqa.ideascale.com/a/idea?templateId=0");

        /*Submit your Idea*/

        /*Set Title*/
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("idea-title-input")))).sendKeys(ideaTitle);

        /*Select Campaign*/
        driver.findElement(By.id("select2-idea-campaign-value-container")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//li[contains(text(),'" +campaignName+ "')]")))).click();

        /*Set Description*/
        WebElement Description = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("idea-desc-value"))));
        Description.sendKeys("Idea of Israt Jahan Shemu ");

        /* to perform Scroll on application using Selenium */
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");

        /*Submit Form*/
        WebElement submitButtonElement = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Submit')]"))));
        submitButtonElement.click();

    }

    public static void upvoteOnIdea() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        /* wait for the home page to be fully loaded */
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("topbar-avatar"))));

        /* click on IDEAS menu */
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"official-header-row\"]/div/div/nav/div/a[2]")))).click();

        String href = driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[2]/div/div/div[2]/article[7]/header/h2/a")).getAttribute("href");
        driver.get(href);

        /* upvote the idea */
        try{
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("a[class='vote-up json-link idea-voted']")))).click();
        }catch (Exception e){
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("a[class='vote-up json-link']")))).click();
        }
    }

    public static void commentOnIdea() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        /* wait for the home page to be fully loaded */
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("topbar-avatar"))));

        /* click on IDEAS menu */
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"official-header-row\"]/div/div/nav/div/a[2]")))).click();

        String href = driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[2]/div/div/div[2]/article[7]/header/h2/a")).getAttribute("href");
        driver.get(href);

        /* write comment in the comment box */
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("comment-text")))).sendKeys("Nice Idea");

        /* to perform Scroll on application using Selenium */
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,450)", "");

        /* click on the comment box */
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("comment-text")))).click();
        /* submit comment by clicking the Submit Comment button */
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("input[class='btn btn-lg btn-primary disabled-when-loading']")))).click();
    }
}