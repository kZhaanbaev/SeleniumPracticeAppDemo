package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUpBase(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://selenium-practice-app.herokuapp.com/?#/home");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
