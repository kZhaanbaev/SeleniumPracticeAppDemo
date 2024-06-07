package tests;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.javafaker.Faker;
import data.pojos.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import utils.Report;
import utils.Screenshot;

public class Temp {
    @Test
    public void test01(){
        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter("report.html");
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setDocumentTitle("Selenium Practice App Demo");
        reporter.config().setReportName("Selenium Practice App Demo");
        extentReports.attachReporter(reporter);

        ExtentTest test = extentReports.createTest("Temp Test");
        test.pass("Pass example");
        test.info("Info example");
        test.fail("Fail example");
        test.skip("Skip example");
        test.warning("Warning example");

        //setting highlights to the log message,aka labeling
        test.pass(MarkupHelper.createLabel("TestPASSmessage", ExtentColor.GREEN));
        test.info(MarkupHelper.createLabel("TestINFOmessage",ExtentColor.CYAN));
        test.fail(MarkupHelper.createLabel("TestFAILmessage",ExtentColor.RED));
        test.skip(MarkupHelper.createLabel("TestSKIPPEDmessage",ExtentColor.GREY));
        test.warning(MarkupHelper.createLabel("TestWARNINGmessage",ExtentColor.ORANGE));

        //logging a list
        String[] list = {"orange", "apple", "banana", "grape", "kiwi"};
        test.info(MarkupHelper.createUnorderedList(list));

        //logging a table info
        String[][] table = {{"data 1", "value 1"}, {"data 2", "value 2"}, {"data 3", "value 3"}};
        test.info(MarkupHelper.createTable(table));

        //logging custom objects (pojos)
        Faker faker = new Faker();
        User user = new User(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.phoneNumber().cellPhone(),
                "test@test.com",
                "Student"
        );
        test.info(MarkupHelper.createJsonCodeBlock(user));

        //adding labels to test
        test.assignAuthor("John");
        test.assignCategory("Regression");
        test.assignDevice("Macbook");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");

        test.pass("Image title", MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.takeScreenshot(driver)).build());
        driver.close();

        extentReports.flush();


    }
}
