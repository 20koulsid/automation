package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    public CheckoutPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "billing_first_name")
    private WebElement firstName;

    @FindBy(id = "billing_last_name")
    private WebElement lastName;

    @FindBy(id = "id=\"select2-billing_country-container\"")
    private WebElement countryName;

    @FindBy(id = "billing_address_1")
    private WebElement billingAddress;

    @FindBy(id = "billing_postcode")
    private WebElement postcode;

    @FindBy(css = "#order_review > table > tfoot > tr.order-total > td > strong > span > bdi")
    private WebElement totalAmount;

    @FindBy(id = "billing_city")
    private WebElement billingCity;

    @FindBy(id = "place_order")
    private WebElement placeOrder;


    public void provideBillingDetails(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(17));
        wait.until(ExpectedConditions.visibilityOf(billingAddress));
        billingAddress.sendKeys("abc");
    }

    public String getTotalAmount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(17));
        wait.until(ExpectedConditions.visibilityOf(totalAmount));
        return totalAmount.getText();
    }

}
