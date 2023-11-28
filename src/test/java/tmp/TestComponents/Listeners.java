package tmp.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tmp.resources.ExtendReportConfig;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest extentTest;
    ExtentReports extentReports = ExtendReportConfig.getReportObject();
    ThreadLocal<ExtentTest> threadLocalExtentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
        threadLocalExtentTest.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        extentTest.log(Status.PASS, "Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        threadLocalExtentTest.get().fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass()
                    .getField("driver").get(result.getInstance());  // get WebDriver object from ITestResult object
            String screenshotFilePath = getScreenshot(result.getMethod().getMethodName(), driver);
            threadLocalExtentTest.get().addScreenCaptureFromPath(screenshotFilePath, result.getMethod().getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        extentReports.flush();
    }
}
