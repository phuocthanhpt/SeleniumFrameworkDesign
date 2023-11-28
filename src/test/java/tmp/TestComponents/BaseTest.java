package tmp.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import tmp.pageObjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        // Read properties file
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/tmp/resources/GlobalData.properties");
        properties.load(fis);
//        String browserName = properties.getProperty("browser");
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");

        if (browserName.contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }


    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goToLandingPage();

        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

    public List<HashMap<String, String>> getJsonDataToMap(String dataFilePath) throws IOException {
        // read json to string
        String jsonContent = FileUtils.readFileToString(new File(dataFilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        return data;
    }

    /*
        This method is called in Listeners, it does not know WebDriver running
        We should provide WebDriver as param
     */
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destFilePath = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        File destFile = new File(destFilePath);
        FileUtils.copyFile(srcFile, destFile);

        return destFilePath;
    }
}
