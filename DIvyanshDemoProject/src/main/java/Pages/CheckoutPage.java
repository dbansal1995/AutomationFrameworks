package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.CommonUtilities;


public class CheckoutPage extends CommonUtilities {


    //WebDriver driver;

    public CheckoutPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

//    public By getAbc() {
//        return abc;
//    }
//
//    public void setAbc(By abc) {
//        this.abc = abc;
//    }
//
//    By abc= By.className("");


    //Page Factory WebElements
    @FindBy(css = "[value='guest']")
    private WebElement Guest_radio_btn;

    @FindBy(css = "[value='Continue']")
    private WebElement continue_account_btn;

    @FindBy(id = "input-payment-firstname")
    private WebElement input_first_name;

    @FindBy(id = "input-payment-lastname")
    private WebElement input_last_name;

    @FindBy(id = "input-payment-address-1")
    private WebElement address_text;

    @FindBy(id = "input-payment-city")
    private WebElement payment_city;

    @FindBy(css = "#input-payment-email")
    private WebElement payment_email_id;

    @FindBy(id = "input-payment-postcode")
    private WebElement postcode;

    @FindBy(id = "input-payment-telephone")
    private WebElement telephone_edit_box;


    @FindBy(css = "#input-payment-country")
    private WebElement payment_country_dropdown;

    @FindBy(id = "input-payment-zone")
    private WebElement input_payment_region;

    @FindBy(name = "agree")
    private WebElement tnc_checkbox;

    @FindBy(css = "#button-payment-method")
    private WebElement Continue_billing_btn;

    @FindBy(css = "#button-guest")
    private WebElement getContinue_billing_btn;

    @FindBy(css = ".alert.alert-danger.alert-dismissible")
    private WebElement alertMessage_paymentMethod;

    public void select_checkout_btn() {
        waitForElement(Guest_radio_btn, 5);
        Guest_radio_btn.click();
        continue_account_btn.click();
    }

    public void EnterBillingDetails(String FirstName, String LastName, String Address, String City, String CountryName, String Region) {
        input_first_name.sendKeys(FirstName);
        input_last_name.sendKeys(LastName);
        address_text.sendKeys(Address);
        payment_city.sendKeys(City);
        postcode.sendKeys("1122334455");
        telephone_edit_box.sendKeys("9999999999");
        payment_email_id.sendKeys(FirstName + LastName + "@gmail.com");
        selectValueInList(payment_country_dropdown, CountryName);
//        Select select=new Select(payment_country_dropdown);
//        select.selectByValue(CountryName);

//        Select select1=new Select(input_payment_region);
//        select1.selectByValue("abc");
        selectValueInList(input_payment_region, Region);
        getContinue_billing_btn.click();

    }

    public void addPaymentMethod() {
        waitForElement(tnc_checkbox, 5);
        tnc_checkbox.click();
        Continue_billing_btn.click();

    }


    public void selectValueInList(WebElement element, String value) {
        // element.click();
        Select select = new Select(element);
        select.selectByVisibleText(value);

    }


    public String returnalertMessage() {
        return alertMessage_paymentMethod.getText();
    }
}
