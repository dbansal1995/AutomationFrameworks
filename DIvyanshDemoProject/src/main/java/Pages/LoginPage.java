package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CommonUtilities;


public class LoginPage extends CommonUtilities {


    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    WebDriver driver;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    //Page Factory WebElements
    @FindBy(id = "input-email")
    private WebElement emailtextbox;

    @FindBy(id = "input-password")
    private WebElement passwordTextbox;

    @FindBy(css = "[value='Login']")
    private WebElement login_btn;

    @FindBy(css = "[id='content'] h2:nth-child(1)")
    private WebElement LoginPageHeading;

    public void LoginAccount(String useremail, String password) {

        emailtextbox.sendKeys(useremail);
        passwordTextbox.sendKeys(password);
        login_btn.click();

    }

    public String login_heading() {
        return LoginPageHeading.getText();
    }


}
