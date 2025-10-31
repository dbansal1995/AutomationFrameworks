package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonUtilities;

import java.util.List;

public class HomePage extends CommonUtilities {


    public HomePage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    //Page Factory
    @FindBy(css = "[title='naveenopencart']")
    private WebElement HomeLogo;

    @FindBy(css = "[title='My Account']")
    private WebElement MyAccount;

    @FindBy(css = ".dropdown-menu.dropdown-menu-right a")
    private List<WebElement> MyAccountMenu;

    @FindBy(css = "[name='search']")
    private WebElement SearchBar;

    @FindBy(xpath = "//span[@class='input-group-btn']")
    private WebElement search_btn;

    @FindBy(xpath = "//h1")
    private WebElement SearchProductText;

//List, Set, ArrayList --- Java collection

    @FindBy(css = ".nav.navbar-nav>li>a")
    private List<WebElement> HomeMenuBar;


    //Add Products
    @FindBy(css = ".button-group>button:nth-child(1)")
    private List<WebElement> addToCar_btns;

    @FindBy(css = ".caption a")
    private List<WebElement> ProductNames;

    @FindBy(id = "cart")
    private WebElement cart_btn;

    @FindBy(xpath = "//a[normalize-space()='shopping cart']")
    private WebElement shopping_cart_btn;


    public void SelectHomePageLogo() {
        HomeLogo.click();

    }


    public void SelectOptionInMyAccount(String option) {
        MyAccount.click();

        for (int i = 0; i < MyAccountMenu.size(); i++) {

            String myAccountOption = MyAccountMenu.get(i).getText();
            System.out.println(myAccountOption);
            if (myAccountOption.equalsIgnoreCase(option)) {
                MyAccountMenu.get(i).click();

            }
        }
//        if(option.equalsIgnoreCase("Login")){
//
//
//        } else if (option.equalsIgnoreCase("Register")) {
//
//            new RegisterPage(driver);
//
//        }
    }


    public String SearchProduct(String searchedString) {
        SearchBar.click();
        SearchBar.sendKeys(searchedString);
        search_btn.click();
        String fetchedText = SearchProductText.getText();
        return fetchedText;
    }

    public void MoveToHomeMenuOption(String homeMenuOption) {

        for (int i = 0; i < HomeMenuBar.size(); i++) {
            String MenuOption = HomeMenuBar.get(i).getText();
            if (MenuOption.equals(homeMenuOption)) {

                MoveToElement(HomeMenuBar.get(i));
                break;
            }


        }

    }

    public void addProduct(String Product_name) throws InterruptedException {

        for (int i = 0; i < ProductNames.size(); i++) {

            if (ProductNames.get(i).getText().equalsIgnoreCase(Product_name)) {

                addToCar_btns.get(i).click();
                break;
            }

        }

        try {
            waitForElement(shopping_cart_btn, 5);
            shopping_cart_btn.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException | org.openqa.selenium.NoSuchElementException e) {
            shopping_cart_btn.click();
            e.getStackTrace();
        }

    }


}
