package org.example;

import Pages.PracticePage;
import io.cucumber.java.After;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.CommonUtilities;

import java.io.IOException;

public class WebTableTestCase extends Base{

    @BeforeMethod
    public void TearTear() throws IOException {
        System.out.println("WebTable Test Case Execution Started");
        initBrowsers();
        launchApplication("WebTableUrl");

    }

    @Test
    public void webTableTest() throws IOException {

        PracticePage practicePage=new PracticePage(driver);
        practicePage.ScrollIntoElement(practicePage.getWebTable());
        int TotalAmount=practicePage.returnTotalAmount();
        System.out.println("Total Amount from Application: "+TotalAmount);
        int amount=practicePage.returnSumAmount();
        Assert.assertEquals(amount,TotalAmount);

    }

    @Test
    public void mouseHover(){
        PracticePage practicePage=new PracticePage(driver);
        practicePage.hoverOnElementAndSelectOption("Reload");
    }

    @Test
    public void verifyButtonInIframe(){
        PracticePage practicePage=new PracticePage(driver);
        practicePage.switchToIframeAndVerifyElement();
        String childWIndowText=practicePage.switchToAnotherTabAndVerifyHeading();
        Assert.assertEquals(childWIndowText,"Sign Up");
    }

    @Test
    public void verifyBlinkingText(){
        PracticePage practicePage=new PracticePage(driver);
        String blinkingText=practicePage.returnBlinkingText();
        Assert.assertEquals(blinkingText,"Free Access to InterviewQues/ResumeAssistance/Material");
    }

    @Test
    public void findBrokenLink() throws IOException {

        PracticePage practicePage=new PracticePage(driver);
        boolean isbroken=practicePage.returnBrokenLink();
        Assert.assertTrue(isbroken);

    }

    @AfterMethod
    public void TearDown() {
        driver.quit();
        System.out.println("WebTable Test Case Execution Ended");
    }

}
