package tmp.pageObjects;

import tmp.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {

    WebDriver driver;

    @FindBy(css=".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css=".totalRow button")
    WebElement checkout_button;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyProductDisplay(String productName){
        return cartProducts.stream().anyMatch(product->
                product.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckout(){
        checkout_button.click();

        return new CheckoutPage(driver);
    }
}
