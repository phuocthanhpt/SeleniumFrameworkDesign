package tmp.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import tmp.TestComponents.BaseTest;
import tmp.pageObjects.*;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test(groups = {"Purchase"})
    public void submitOrder(){
        ProductCatalogue productCatalogue = landingPage.loginApplication("tmp@email.com", "Auto@123");
        List<WebElement> products = productCatalogue.getProductList();
        String productName = "ZARA COAT 3";
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
}
