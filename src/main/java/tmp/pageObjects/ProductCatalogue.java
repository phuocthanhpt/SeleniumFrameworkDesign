package tmp.pageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponents {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".mb-3")
    List<WebElement> products;

    By by_products = By.cssSelector(".mb-3");
    By by_addToCart_button = By.cssSelector(".card-body button:last-of-type");
    By by_toastMessage_alert = By.cssSelector("#toast-container");

    @FindBy(css=".ng-animating")
    WebElement spinner;

    public List<WebElement> getProductList(){
        waitForElementToAppear(by_products);
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement product = getProductList().stream().filter(item->
                item.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

        return product;
    }

    public void addProductToCart(String productName){
        WebElement product = getProductByName(productName);
        product.findElement(by_addToCart_button).click();
        waitForElementToAppear(by_toastMessage_alert);
//        waitForElementDisappear(spinner);
        waitForElementDisappear(driver.findElement(By.cssSelector(".ng-animating")));
    }
}
