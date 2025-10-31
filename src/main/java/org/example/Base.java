package org.example;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {

    public WebDriver driver;
    public WebDriverWait wait;
    public Properties prop;

    public void initBrowsers() throws IOException {
        prop = new Properties();
        // System.out.println(System.getProperty("user.dir"));

        FileInputStream fs = new FileInputStream(new File(System.getProperty("user.dir") + "\\src\\main\\java\\utils\\data.properties"));

        prop.load(fs);
        String Browser = System.getProperty("Browser") != null ? System.getProperty("Browser") : prop.getProperty("Browser");
       // String Browser = prop.getProperty("Browser");
        if (Browser.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();


        } else if (Browser.equalsIgnoreCase("Firefox")) {

            driver = new FirefoxDriver();

        } else if (Browser.equals("Safari")) {
            //Safari Code
        }


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    public @Nullable String launchApplication() {

        String url = prop.getProperty("URL");

        driver.get(url);

        return driver.getTitle();


    }


}
