package AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css="[routerlink*='cart']")
    WebElement cartHeader;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToAppear(By findBy) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
    }

    public void waitForElementDisappear(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void goToCartPage(){
        cartHeader.click();
    }
}
