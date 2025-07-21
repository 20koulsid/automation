import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Constants;
import utils.FrameworkProperties;

public class Main {
    public static void main(String[] args){
        FrameworkProperties frameworkProperties = new FrameworkProperties();
       DriverSingleton driverSingleton =  DriverSingleton.getInstance(frameworkProperties.getProperty("browser"));
        WebDriver driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);

        HomePage homePage = new HomePage();
        SignInPage signInPage = new SignInPage();
        ShopPage shopPage = new ShopPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();

        homePage.clickSignIn();
        signInPage.logIn(frameworkProperties.getProperty(Constants.Email), frameworkProperties.getProperty(Constants.PASSWORD));

        if(homePage.getUsername().equals(Constants.USERNAME))
            System.out.println("Test passed");
        else
            System.out.println("test failed");

        homePage.clickShopButton();
        shopPage.addElementToCart();
        shopPage.proceedToCheckout();
        cartPage.proceedToCheckout();
        checkoutPage.provideBillingDetails();


        DriverSingleton.closedObjectInstance();
    }
}
