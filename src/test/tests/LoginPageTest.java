package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserMgtPage;
import utils.BrowserUtils;

public class LoginPageTest extends BaseTest{
    LoginPage page;
    UserMgtPage userMgtPage;

    @BeforeMethod
    public void setUp(){
        page = new LoginPage(driver);
        userMgtPage = new UserMgtPage(driver);

        driver.get("https://selenium-practice-app.herokuapp.com/?#/usermgt");
        userMgtPage.loginBtn.click();
        new BrowserUtils(driver).switchToNextWindow();
    }

    @Test(testName = "US701 - Login Tests")
    public void test01() {
//        driver.findElement(By.name("username")).sendKeys("john.snow@north.com");
//        driver.findElement(By.name("password")).sendKeys("john.snow$");
//        driver.findElement(By.tagName("button")).click();
        page.login("john.snow@north.com", "john.snow$");
        Assert.assertEquals(driver.getCurrentUrl(), "https://selenium-practice-app.herokuapp.com/?#/user");
    }
}
