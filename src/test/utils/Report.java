package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class Report {
    private WebDriver driver;
    protected ExtentReports extentReports;
    protected ExtentTest extentTest;

    public void createReport(){
        extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter("report.html");
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setDocumentTitle("Selenium Practice App Demo");
        reporter.config().setReportName("Selenium Practice App Demo");
        extentReports.attachReporter(reporter);
    }

    public void closeReport(){
        extentReports.flush();
    }

    public ExtentTest createTestReport(WebDriver driver){
        this.driver = driver;
        extentTest = extentReports.createTest("Temp Test");
        return extentTest;
    }

    public void logInfo(String info){
        extentTest.info(info);
    }

    public void logInfo(Object object){
        extentTest.info(MarkupHelper.createJsonCodeBlock(object));
    }

    public void logScreenshot(String title){
        extentTest.pass(title, MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.takeScreenshot(driver)).build());
    }

    public void logTestResult(ITestResult result){
        if (result.getStatus() == ITestResult.SUCCESS){
            extentTest.pass("TEST PASSED");
        }else if (result.getStatus() == ITestResult.FAILURE){
            extentTest.fail("TEST FAILED");
        }
    }
}
