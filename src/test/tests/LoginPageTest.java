package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserUtils;

public class LoginPageTest extends BaseTest{

    @BeforeMethod
    public void setUp(){
        driver.get("https://selenium-practice-app.herokuapp.com/?#/usermgt");
        driver.findElement(By.id("practice-form")).click();
        new BrowserUtils(driver).switchToNextWindow();
    }

    @Test(testName = "US701 - Login Tests")
    public void test01() {
        driver.findElement(By.name("username")).sendKeys("john.snow@north.com");
        driver.findElement(By.name("password")).sendKeys("john.snow$");
        driver.findElement(By.tagName("button")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://selenium-practice-app.herokuapp.com/?#/user");
    }
}
