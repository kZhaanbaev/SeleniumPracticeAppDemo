package tests;

import com.github.javafaker.Faker;
import data.pojos.ExistingUser;
import data.pojos.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DatabasePage;
import pages.LoginPage;
import pages.UserMgtPage;
import utils.BrowserUtils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class DatabasePageTest extends BaseTest{
    DatabasePage page;
    UserMgtPage userMgtPage;

    @BeforeMethod
    public void setUp(){
        page = new DatabasePage(driver);
        userMgtPage = new UserMgtPage(driver);

        driver.findElement(By.xpath("//nav/a[text()='User-Mgt']")).click();
    }

    @Test(testName = "US2004 - New user's password format")
    public void test01(){
        Faker faker = new Faker();
        String first = faker.name().firstName();
        String last = faker.name().lastName();
        User user = new User(
                first,
                last,
                faker.phoneNumber().cellPhone(),
                first.toLowerCase() + "." + last.toLowerCase() + "@test.com",
                "Student"
        );

        //adding to report to visually see it
        report.logInfo("Used Test data User:");
        report.logInfo(user);

        userMgtPage.fillOutNewUserRegistrationForm(user.getFirstName(), user.getLastName(), user.getPhone(), user.getEmail(), "Student");
        userMgtPage.submitTableBtn.click();
        userMgtPage.accessDbBtn.click();
        new BrowserUtils(driver).switchToNextWindow();

        String expectedPassword = first.toLowerCase() + "." + last.toLowerCase() + "$";
        String actualPassword = page.getPasswordByEmail(user.getEmail()).get(0).getText();

        Assert.assertEquals(actualPassword, expectedPassword);
    }

    @Test(testName = "US2005 - Delete new user's information in database")
    public void test02(){
        Faker faker = new Faker();
        String first = faker.name().firstName();
        String last = faker.name().lastName();

        User user = new User(
                first,
                last,
                faker.phoneNumber().cellPhone(),
                first.toLowerCase() + "." + last.toLowerCase() + "@test.com",
                "Student"
        );

        report.logInfo("Used Test data User:");
        report.logInfo(user);

        userMgtPage.fillOutNewUserRegistrationForm(user.getFirstName(), user.getLastName(), user.getPhone(), user.getEmail(), "Student");
        userMgtPage.submitTableBtn.click();
        userMgtPage.accessDbBtn.click();
        new BrowserUtils(driver).switchToNextWindow();

        Assert.assertTrue(page.getDeleteBtnByEmail(user.getEmail()).isDisplayed());

        page.getDeleteBtnByEmail(user.getEmail()).click();

        new BrowserUtils(driver).sleep(1000);
        List<WebElement> list = page.getPasswordByEmail(user.getEmail());

        Assert.assertEquals(list.size(), 0);

    }


}
