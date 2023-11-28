package tmp.tests;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import tmp.TestComponents.BaseTest;
import tmp.pageObjects.CartPage;
import tmp.pageObjects.ProductCatalogue;


public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException {
		landingPage.loginApplication("tmp_test1@email.com", "Auto@123456");
		Assert.assertEquals("Incorrect emaillll or password.", landingPage.getErrorMessage());
	}
	

	@Test(groups= {"ErrorHandling"})
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("tmp@email.com", "Auto@123");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

	
	

}
