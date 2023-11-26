package tmp.pageObjects;

import tmp.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponents {

    WebDriver driver;

    @FindBy(css = ".hero-primary")
    WebElement confirmationMessage;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getConfirmationMessage()
    {
        CheckoutPage cp = new CheckoutPage(driver);
        return confirmationMessage.getText();
    }
}
