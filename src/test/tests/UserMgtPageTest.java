package tests;

import com.github.javafaker.Faker;
import data.pojos.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.UserMgtPage;

import java.lang.reflect.Method;
import java.util.List;

public class UserMgtPageTest extends BaseTest{
    private UserMgtPage page;

    @BeforeMethod
    public void setUp(){
        page = new UserMgtPage(driver);
        driver.findElement(By.xpath("//nav/a[text()='User-Mgt']")).click();
    }

    @DataProvider(name = "roles")
    public Object[] data2(){
        Object[] roles = new String[] {"Mentor"};
        return roles;
    }

    @Test(testName = "US1010: Staging table view", dataProvider = "roles")
    public void test010(String role){
        Faker faker = new Faker();
        User user = new User(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.phoneNumber().cellPhone(),
                "test@test.com",
                role
        );

        report.logInfo(user);

        page.fillOutNewUserRegistrationForm(user.getFirstName(), user.getLastName(), user.getPhone(), user.getEmail(), role);

        Assert.assertEquals(driver.findElement(By.xpath("//td[1]")).getText(), user.getFirstName());
        Assert.assertEquals(driver.findElement(By.xpath("//td[2]")).getText(), user.getLastName());
        Assert.assertEquals(driver.findElement(By.xpath("//td[3]")).getText(), user.getPhone());
        Assert.assertEquals(driver.findElement(By.xpath("//td[4]")).getText(), user.getEmail());
        Assert.assertEquals(driver.findElement(By.xpath("//td[5]")).getText(), role);
    }

    @Test(testName = "US1010: Staging table view - DB check", dataProvider = "roles", groups = "smoke")
    public void test02(String role){
        driver.findElement(By.id("Firstname")).sendKeys("John");
        driver.findElement(By.id("Lastname")).sendKeys("Smith");
        driver.findElement(By.id("Phonenumber")).sendKeys("123-456-7890");
        driver.findElement(By.id("Email")).sendKeys("j.smith@test.com");
        driver.findElement(By.id("Select-role")).sendKeys(role);
        driver.findElement(By.id("submit-btn")).click();

        //accessing db page
        driver.findElement(By.id("access-db-btn")).click();
        //switch to db window
        for(String each: driver.getWindowHandles()){
            if (!each.equals(driver.getWindowHandle()))
                driver.switchTo().window(each);
        }

        //validate user email doesn't exist
        String xpath = "//td[text()='j.smith@test.com']";

        //using list to avoid NoSuchElementException, which would stop the execution and not reach Assertion
        List<WebElement> elementList = driver.findElements(By.xpath(xpath));
        Assert.assertEquals(elementList.size(), 0);
    }

    @Test(testName = "US1012: Adding user to DB", dataProvider = "roles")
    public void test05(String role){




        driver.findElement(By.id("Firstname")).sendKeys("John");
        driver.findElement(By.id("Lastname")).sendKeys("Smith");
        driver.findElement(By.id("Phonenumber")).sendKeys("123-456-7890");
        driver.findElement(By.id("Email")).sendKeys("j.smith@test.com");
        driver.findElement(By.id("Select-role")).sendKeys(role);
        driver.findElement(By.id("submit-btn")).click();

        //submitting table to db
        driver.findElement(By.id("submit-table-btn")).click();

        //validating table contains new email
    }

}
