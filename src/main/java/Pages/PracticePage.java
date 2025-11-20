package Pages;

import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonUtilities;

import java.io.IOException;
import java.util.List;

public class PracticePage extends CommonUtilities {


    public PracticePage(WebDriver driver){
        this.driver=driver;
        js=(JavascriptExecutor) driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css ="#product>tbody:nth-child(2)" )
    private WebElement table;

    @FindBy(css ="#product>tbody:nth-child(2)>tr")
    private List<WebElement> rows;

    @FindBy(css ="#product>thead>tr>th")
    private List<WebElement> columns;

    @FindBy(css="div[class='totalAmount']")
    private WebElement totalAmount;

    @FindBy(css="#mousehover")
    private WebElement mousehover;

    @FindBy(css=".mouse-hover-content>a")
    private List<WebElement> mouseHoverDropwdownmenu;

    @FindBy(css="#courses-iframe")
    private WebElement IframeID;


    @FindBy(xpath="(//a[contains(@href,'/sign_up')])[1]")
    private WebElement joinNowBtn;

    @FindBy(xpath = "//h3")
    private WebElement childWindowHeading;

    @FindBy(css = "a[href*='documents'][class=blinkingText]")
    private WebElement blinkingTextLink;;

    @FindBy(css="li>a")
    private List<WebElement> Footerlinks;

    public WebElement getWebTable(){
        return table;
    }

    public int returnSumAmount(){
        int sum=0;
        System.out.println(rows.size());
        for(int j=0;j<=rows.size()-1;j++)
        {
            ScrollIntoElement(rows.get(j));
            List<WebElement> cells= rows.get(j).findElements(By.tagName("td"));

            String amountValue=cells.get(cells.size()-1).getText();
            System.out.println(amountValue);
            sum+=Integer.parseInt(amountValue); ;

        }
        System.out.println("Total Amount: "+sum);

        return sum;
        }

    public int returnTotalAmount(){

        String value=totalAmount.getText();
        String spliitedArray[]=value.split(":");
        return Integer.parseInt(spliitedArray[1].trim());
    }


    public void hoverOnElementAndSelectOption(String option){
        MoveToElement(mousehover);
        waitForElement(mouseHoverDropwdownmenu.get(0),2);

        for(int i=0;i<mouseHoverDropwdownmenu.size();i++){
            if (mouseHoverDropwdownmenu.get(i).getText().equals(option)){
                mouseHoverDropwdownmenu.get(i).click();
                break;
            }
        }

    }
    public void switchToIframeAndVerifyElement(){
        scrollScreen(1200);
        switchToFrame(IframeID);
        waitForElement(joinNowBtn,2);
        joinNowBtn.click();
    }

    public String switchToAnotherTabAndVerifyHeading(){

        getWindows();
        waitForElement(childWindowHeading,2000);
        System.out.println("Child Window Heading: "+childWindowHeading.getText());
        return childWindowHeading.getText();
    }


    public String returnBlinkingText(){

        waitForElement(blinkingTextLink,2000);
        return blinkingTextLink.getText();
    }

    public boolean returnBrokenLink() throws IOException {

        boolean flag=findBrokenLink(Footerlinks);
        return flag;
    }
}
