package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonUtilities;


public class RegisterPage extends CommonUtilities {


    WebDriver driver;

    public RegisterPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    //Page Factory WebElements
    @FindBy(css = "h1")
    private WebElement RegisterAccountHeading;

    @FindBy(css = "#input-firstname")
    private WebElement firstName;

    @FindBy(css = "#input-lastname")
    private WebElement lastName;

    @FindBy(css = "#input-password")
    private WebElement password;

    @FindBy(css = "#input-confirm")
    private WebElement Confirmpassword;

    @FindBy(css = "#input-email")
    private WebElement email;

    @FindBy(css = "#input-telephone")
    private WebElement Telephone;

    @FindBy(name = "agree")
    private WebElement Privacycheckbox;

    @FindBy(css = "input[type=submit]")
    private WebElement Submit_btn;

    //Warning alert message
    @FindBy(css = ".alert.alert-danger.alert-dismissible")
    private WebElement alert;

    //nvalidFirstName Message
    @FindBy(css = "#input-firstname+div")
    private WebElement InvalidFirstNameToolTip;


    @FindBy(css = "h1")
    private WebElement AccountCreationTooltip;

    public String RegisterAccount() {
        String RegisterAccountPageHeading = RegisterAccountHeading.getText();
        return RegisterAccountPageHeading;
    }


    public void AddRegistrationDetails(String name, String last_Name, String TelephoneNo, String PasswordValue) {
        firstName.sendKeys(name);
        lastName.sendKeys(last_Name);
        email.sendKeys(name + last_Name + "@gmail.com");

        Telephone.sendKeys(TelephoneNo);
        password.sendKeys(PasswordValue);
        Confirmpassword.sendKeys(PasswordValue);
        Privacycheckbox.click();
        Submit_btn.click();

    }

    public String returnAlertMessage() {

        String alert_message = alert.getText();
        return alert_message;

    }

    public String returnErrorMessage() {

        String InvalidFirstNameMsg = InvalidFirstNameToolTip.getText();
        return InvalidFirstNameMsg;

    }

    public String verifyAccountCreation() {
        //Your Account Has Been Created!
        waitForElement(AccountCreationTooltip, 5);
        return AccountCreationTooltip.getText();

    }


}
