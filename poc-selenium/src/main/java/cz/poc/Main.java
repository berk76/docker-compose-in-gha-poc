package cz.poc;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Main {
    
    public static void main(String args[]) throws IOException  {
        runTests();
    }

    public static void runTests() throws IOException {
        WebDriver driver = getChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/Dispatcher");
        
        takeSnapShot(driver, "homepage.png");

        String headerText = driver.findElement(By.tagName("h1")).getText();
        Assert.assertTrue("Text not found!", headerText.contains("Hello World"));
        
        driver.quit();
    }


    public static void takeSnapShot(WebDriver webdriver, String file) throws IOException {
        String snapshotDir = "target/";
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        //Call getScreenshotAs method to create image file
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File destFile = new File(snapshotDir + file);
        //Copy file at destination
        FileUtils.copyFile(srcFile, destFile);
    }

    public static WebDriver getChromeDriver() {
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("window-size=1200,1200");

        driver = new ChromeDriver(options);
        return driver;
    }
}
