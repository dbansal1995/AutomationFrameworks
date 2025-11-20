package stepDefinitions;

import Pages.HomePage;
import Pages.RegisterPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Base;
import org.testng.Assert;

import java.io.IOException;

public class stepDefinitionsImpl extends Base {

    String splittedArray[];
    RegisterPage rp;
    HomePage hm;

    @Before
    public void createObjects() throws IOException {
        initBrowsers();
        hm = new HomePage(driver);
        rp = new RegisterPage(driver);
    }

    @Given("Initialise Web drivers")
    public void Initialise_Web_drivers() throws IOException {
        initBrowsers();

        //Code

    }
    @Given("Application is launched")
    public void Application_is_launched(){
        launchApplication("URL");

    }

    @When("^Product is searched with \"([^\"]*)\"$")
    public void Product_is_searched(String productName){

        hm = new HomePage(driver);
        String SearchedText = hm.SearchProduct(productName);
        splittedArray = SearchedText.split("-");
        System.out.println(splittedArray[1].trim());
    }

    @When("Format searched string ")
    public void format_searched_string() {
        System.out.println("********");
    }
    @Then("Verify searched result {string}")
    public void verify_searched_result(String ProductName) {

        Assert.assertEquals(splittedArray[1].trim(), ProductName);
    }

    @When("Select My Account option {string}")
    public void select_my_account_option(String option) {
        hm = new HomePage(driver);
        hm.SelectOptionInMyAccount(option);
    }
    @When("Add Registration Details {string} {string} {string} {string}")
    public void add_registration_details(String Firstname, String LastName, String Telephone, String password) {
        rp = new RegisterPage(driver);
        rp.AddRegistrationDetails("", LastName, Telephone, password);

    }
    @Then("verify the error message for invalid details")
    public void verify_the_error_message_for_invalid_details() {

        Assert.assertEquals(rp.returnErrorMessage(), "First Name must be between 1 and 32 characters!");

    }

    @After
    public void tear_down(){
        driver.quit();
    }

}
