package utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CommonUtilities extends Base {
    public JavascriptExecutor js;
    WebDriverWait wait;



    public JavascriptExecutor scrollScreen(int x) {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + x + ")");
        return js;
    }

    public JavascriptExecutor ScrollIntoElement(WebElement element) {

        js.executeScript("arguments[0].scrollIntoView(true)", element);
        return js;
    }

    public String getWindows() {
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        String parentWindow = it.next();
        String childWindow = it.next();
        driver.switchTo().window(childWindow);
        return childWindow;


    }

    public static String generateRandomString(int length) {

        // String string="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        return RandomStringUtils.randomAlphanumeric(length);


    }

    public static String generateRandomNumeric(int length) {

        return RandomStringUtils.randomNumeric(length);

    }

    public void switchToFrame(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
        //driver.switchTo().defaultContent();
    }

    public void HandleAlerts(String decision) {
        if (decision.equalsIgnoreCase("Accept")) {
            System.out.println(driver.switchTo().alert().getText());
            driver.switchTo().alert().accept();

        }
        if (decision.equalsIgnoreCase("Decline")) {
            driver.switchTo().alert().dismiss();
        }
    }

    public WebDriverWait waitForElement(WebElement ele, int timeout) {

        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        return wait;
    }

    public Actions MoveToElement(WebElement element) {

        Actions action = new Actions(driver);

        action.moveToElement(element).build().perform();
        return action;
    }


    public Boolean findBrokenLink(List<WebElement> brokenLinks) throws IOException {


        boolean flag=false;
        for(int i=0;i<brokenLinks.size();i++) {

            String url=brokenLinks.get(i).getAttribute("href");
            System.out.println(url);
            HttpURLConnection connection=(HttpURLConnection)new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int responseCode=connection.getResponseCode();

            if(responseCode>400){
                System.out.println("Broken Link: "+url);
                flag=true;
                return flag;
            }

        }
        return flag;
    }

    public Robot robotutils() throws AWTException {

        Robot robot=new Robot();
        return robot;
    }

}
