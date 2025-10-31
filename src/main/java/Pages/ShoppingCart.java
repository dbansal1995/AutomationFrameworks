package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonUtilities;


public class ShoppingCart extends CommonUtilities {


    WebDriver driver;

    public ShoppingCart(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    //Page Factory WebElements
    @FindBy(css = "a[class='btn btn-primary']")
    private WebElement Checkout_btn;

    @FindBy(xpath = "(//tbody//tr[4]//td[2])[2]")
    private WebElement total_value;


    public void select_checkout_btn() {
        Checkout_btn.click();
    }

    public String total_value_value() {
        return total_value.getText();
    }


}
