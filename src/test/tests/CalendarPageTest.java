package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalendarPageTest extends BaseTest{

    @BeforeMethod
    public void setUp(){
        driver.findElement(By.xpath("//nav/a[text()='Calendar']")).click();
    }

    @Test(testName = "US1015: Choosing date from the calendar")
    public void test01(){
        driver.findElement(By.xpath("(//input)[2]")).click();
//        driver.findElement(By.xpath("//input[@class='react-datepicker-ignore-onclickoutside']")).sendKeys("test");

        driver.findElement(By.xpath("//div[contains(@class, 'selected')]/following::div")).click();
        driver.findElement(By.className("btn")).click();

        String expectedText = "There are 1 days between 6/2/2024 and 6/3/2024";
        String actualText = driver.findElement(By.id("num-days")).getText();

        Assert.assertEquals(actualText, expectedText);
    }
}
