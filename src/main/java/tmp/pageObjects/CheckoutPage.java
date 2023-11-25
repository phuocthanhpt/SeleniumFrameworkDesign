package tmp.pageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponents {

    WebDriver driver;

    @FindBy(css="[placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement selectCountry;

    @FindBy(css = ".action__submit")
    private WebElement submit;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectCountry(String countryName){
        Actions action = new Actions(driver);
        action.sendKeys(country, countryName).build().perform();
        selectCountry.click();
    }

    public ConfirmationPage submitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }
}
