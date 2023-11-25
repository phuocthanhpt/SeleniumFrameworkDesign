package tmp;

import AbstractComponents.AbstractComponents;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tmp.pageObjects.LandingPage;
import tmp.pageObjects.ProductCatalogue;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LandingPage landingPage = new LandingPage(driver);
        landingPage.goToLandingPage();
        landingPage.loginApplication("tmp@email.com", "Auto@123");

        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        List<WebElement> products = productCatalogue.getProductList();
        String productName = "ZARA COAT 3";
        productCatalogue.addProductToCart(productName);

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
//
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct->
                cartProduct.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(productName));
        System.out.println(match);
        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();
        Actions action = new Actions(driver);
        action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


//        driver.close();
//        driver.quit();
    }
}
