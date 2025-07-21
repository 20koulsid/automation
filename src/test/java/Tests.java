import drivers.DriverSingleton;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Constants;
import utils.FrameworkProperties;

import static org.junit.Assert.assertEquals;

public class Tests {
    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static CartPage cartPage;
    static CheckoutPage checkoutPage;
    static ShopPage shopPage;
    static SignInPage signInPage;
    static HomePage homePage;


    @BeforeClass
    public static void initializeObject(){
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
        driver = DriverSingleton.getDriver();
        homePage = new HomePage();
        signInPage = new SignInPage();
        checkoutPage = new CheckoutPage();
    }



    @Test
    public void testingAuthentication(){
        driver.get(Constants.URL);
        homePage.clickSignIn();
        signInPage.logIn(frameworkProperties.getProperty(Constants.Email),frameworkProperties.getProperty(Constants.PASSWORD));
        assertEquals(frameworkProperties.getProperty(Constants.USERNAME),homePage.getUsername());
    }

    @Test
    public void testingAddingThingsToCart(){
        driver.get(Constants.URL);
        homePage.clickShopButton();
        shopPage.goToThirdPage();
        shopPage.addElementToCart();
        assertEquals(Constants.CART_QUANTITY,shopPage.getNumberOfProducts());
    }

    @Test
    public void testingTheFullBuyingProcess(){
        driver.get(Constants.URL);
        homePage.clickShopButton();
        shopPage.goToThirdPage();
        shopPage.addElementToCart();
        shopPage.proceedToCheckout();
        cartPage.proceedToCheckout();
        checkoutPage.provideBillingDetails();
        assertEquals("Order recieved", checkoutPage.getTotalAmount());
    }



    @AfterClass
    public static void closeObjects(){ driver.close();}

}
