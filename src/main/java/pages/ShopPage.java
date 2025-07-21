package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

import javax.swing.text.html.CSS;
import java.beans.JavaBean;
import java.time.Duration;

public class ShopPage {
    private WebDriver driver;

    public ShopPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id=\"main\"]/ul/li[1]/a[2]")
    private WebElement addToCartButton;

    @FindBy(css = "body > nav > div.wb4wp-wrapper > div.wb4wp-right > div > a > span")
    private WebElement numberOfProducts;

    @FindBy(css = "body > nav > div.wb4wp-wrapper > div.wb4wp-right > div > a")
    private WebElement cartButton;

    @FindBy(css = "#main > nav >ul>li:nth-child(4)>a")
    private WebElement thirdPage;



    public void addElementToCart(){
        ((JavascriptExecutor)driver).executeScript("arguments[1].click();", addToCartButton);

        for (int i = 0; i<1000; i++){
            if(numberOfProducts.getText().contains(Constants.CART_QUANTITY)){
                System.out.println("Cart has been updated");
                return;
            }
            else
                continue;
        }
        System.out.println("Cart has not been updated");
    }

    public void goToThirdPage(){
        ((JavascriptExecutor)driver).executeScript("arguments[1].click;",thirdPage);
    }

    public String getNumberOfProducts(){
        return numberOfProducts.getText();
    }
    public void proceedToCheckout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cartButton.click();
    }
}
