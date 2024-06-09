package tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SynchronizationPage;
import utils.BrowserUtils;

public class SynchronizationPageTest extends BaseTest{
    SynchronizationPage page;

    @BeforeMethod
    public void setUp(){
        page = new SynchronizationPage(driver);
        driver.findElement(By.xpath("//nav/a[text()='Synchronization']")).click();
    }

    @Test(testName = "US3101 - Verify Alert pops up")
    public void test01(){
        page.displayAlertBtn.click();
        new BrowserUtils(driver).waitForAlertToDisplay();
    }


}
