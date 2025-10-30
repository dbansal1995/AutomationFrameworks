package org.example;

import Pages.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.CommonUtilities;

import java.io.IOException;

import utils.Listeners.*;
import utils.RetryClass;

//@Listeners(utils.Listeners.class)

public class EndToEnd extends Base {

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @BeforeClass
    public void TearUp() throws IOException {
        initBrowsers();
        launchApplication();

    }


    @Test
    public void AddProductIntoCart() throws IOException, InterruptedException {
        //Verify Searched Product
        HomePage hm = new HomePage(driver);
        hm.SearchProduct("Mac");
        hm.addProduct("MacBook");
        ShoppingCart sc = new ShoppingCart(driver);
        String productPrice = sc.total_value_value();
        System.out.println(productPrice);
        Assert.assertTrue(productPrice.contains("$"));
        sc.select_checkout_btn();
        CheckoutPage cp = new CheckoutPage(driver);
        cp.select_checkout_btn();
        String FirstName = CommonUtilities.generateRandomString(5);
        String LastName = CommonUtilities.generateRandomString(5);
        String AddressName = CommonUtilities.generateRandomString(5);
        cp.EnterBillingDetails(FirstName, LastName, AddressName, "Delhi", "India", "Delhi");
        cp.addPaymentMethod();
        String alertMessage = cp.returnalertMessage();
        String splittedAlertArray[] = alertMessage.split("×");
        System.out.println(splittedAlertArray[0]);
        Assert.assertEquals(splittedAlertArray[0].trim(), "Warning: Payment method required!");
        String str = "Warning: Payment method required!";
        System.out.println(str.length());


    }


}

