package tests;

import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import utils.Report;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public Report report;
    public ExtentTest testReport;

    @BeforeMethod
    public void setUpBase(Method method){
        initializeDriver("chrome");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(ConfigReader.readProperty("config.properties", "url"));
        testReport = report.createTestReport(driver, method);
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        report.logTestResult(result);
        driver.quit();
    }

    public void initializeDriver(String browserType){
        switch (browserType.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Wrong browser type");
        }
    }

    @BeforeSuite
    public void setUpReport(){
        report = new Report();
        report.createReport();
    }

    @AfterSuite
    public void endReport(){
        report.closeReport();
    }

}
