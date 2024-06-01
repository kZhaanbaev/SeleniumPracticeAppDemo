package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageTest extends BaseTest{

    @Test(testName = "Verify header of the page")
    public void test01(){
        Assert.assertEquals(driver.getTitle(), "TLA Automation");
    }

    @Test(testName = "Verify number of nav buttons equal to 18")
    public void test02(){
        List<WebElement> elements = driver.findElements(By.xpath("//a[contains(@class, 'navbar-brand')]"));
        Assert.assertEquals(elements.size(), 18);
    }

    @Test(testName = "Verify footer text")
    public void test03(){
        Assert.assertTrue(driver.findElement(By.id("copyright")).getText().contains("Tech Lead Academy"));
    }
}
