package org.example;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.CommonUtilities;

import java.io.IOException;

@Listeners(utils.Listeners.class)
public class SmokeTestCases extends Base {
    String FirstName;
    String LastName;

    @AfterMethod
    public void TearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void TearUp() throws IOException {
        initBrowsers();
        launchApplication();

    }

    @Test(groups = {"smoke"})
    public void VerifySearchFuntioanlity() throws IOException {
        //Verify Searched Product
        HomePage hm = new HomePage(driver);
        String SearchedText = hm.SearchProduct("Mac");
        String splittedArray[] = SearchedText.split("-");
        System.out.println(splittedArray[1].trim());
        Assert.assertEquals(splittedArray[1].trim(), "Mac");

    }

    @Test(retryAnalyzer = utils.RetryClass.class, groups = "smoke")
    public void RegisterNewAccountWithInvalidDetails() throws IOException, InterruptedException {
        //Verify Searched Product
        HomePage hm = new HomePage(driver);
        hm.SelectOptionInMyAccount("Register");
        RegisterPage rp = new RegisterPage(driver);
        rp.AddRegistrationDetails("", "Bansal", "123456789", "divyanshTest");
        // Assert.assertEquals(rp.returnAlertMessage(),"Warning: You must agree to the Privacy Policy!");
        Assert.assertEquals(rp.returnErrorMessage(), "First Name must be between 1 and 32 characters!");


    }

    @Test
    public void RegisterNewAccountWithValidDetails() throws IOException, InterruptedException {
        //Verify Searched Product
        HomePage hm = new HomePage(driver);
        hm.SelectOptionInMyAccount("Register");
        RegisterPage rp = new RegisterPage(driver);
        FirstName = CommonUtilities.generateRandomString(6);
        LastName = CommonUtilities.generateRandomString(6);
        rp.AddRegistrationDetails(FirstName, LastName, "123456789", "divyanshTest");
        Thread.sleep(2000);
        Assert.assertEquals(rp.verifyAccountCreation(), "Your Account Has Been Created!");
    }

    @Test(dependsOnMethods = "RegisterNewAccountWithValidDetails")
    public void LoginRegisteredAccount() {

        HomePage hm = new HomePage(driver);
        //  hm.SelectOptionInMyAccount("Logout");
        hm.SelectOptionInMyAccount("Login");
        LoginPage lp = new LoginPage(driver);
        System.out.println("FirstName: " + FirstName + " " + "LastName: " + LastName);
        lp.LoginAccount(FirstName + LastName + "@gmail.com", "divyanshTest");
        String login_heading = lp.login_heading();
        Assert.assertEquals(login_heading, "My Account");
    }


    @DataProvider(name = "RegistrationData")
    public Object[][] enterMultipleRegistration() {
        return new Object[][]{

        };
    }

}

