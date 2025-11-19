package org.example;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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


        String Browser = System.getProperty("Browser") != null ? System.getProperty("Browser") :  loadProperty("Browser");;
       // String Browser = prop.getProperty("Browser");
        if (Browser.equalsIgnoreCase("Chrome")) {
            ChromeOptions options=new ChromeOptions();
            options.setExperimentalOption("","");
            driver = new ChromeDriver();




        } else if (Browser.equalsIgnoreCase("Firefox")) {

            driver = new FirefoxDriver();
            FirefoxProfile firefoxProfile=new FirefoxProfile();
            firefoxProfile.setPreference("","");

        } else if (Browser.equals("Safari")) {
            //Safari Code
        }


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    public String loadProperty(String PropertyName) throws IOException {

        prop = new Properties();
        // System.out.println(System.getProperty("user.dir"));

        //Mac
        FileInputStream fs = new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/java/utils/data.properties"));
        //Windows
        //FileInputStream fs = new FileInputStream(new File(System.getProperty("user.dir") + "\\src\\main\\java\\utils\\data.properties"));

        prop.load(fs);

        return prop.getProperty(PropertyName);

    }


    public @Nullable String launchApplication() {

        String url = prop.getProperty("URL");

        driver.get(url);

        return driver.getTitle();


    }


}
