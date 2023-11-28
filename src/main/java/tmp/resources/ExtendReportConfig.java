package tmp.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportConfig {

    public static ExtentReports getReportObject(){
        String reportPath = System.getProperty("user.dir") + "/reports/index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
        extentSparkReporter.config().setReportName("Web Automation");
        extentSparkReporter.config().setDocumentTitle("Automation Test Result");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("AutoTester", "Selenium Java");

        return extentReports;
    }
}
