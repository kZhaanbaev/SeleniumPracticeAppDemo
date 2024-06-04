package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InputsPage;

public class InputsPageTest extends BaseTest{
    private InputsPage page;

    @BeforeMethod
    public void setUp(){
        page = new InputsPage(driver);
        driver.get("https://selenium-practice-app.herokuapp.com/?#/inputs");
    }

    @Test(testName = "US101: Verify Input message: test case 1", groups = {"regression"})
    public void test01(){
        page.messageField.sendKeys("Java is hard");
        page.showMessageBtn.click();
        Assert.assertEquals(page.displayedMessage.getText(), "Java is hard");
    }

    @Test(testName = "US101: Verify Input message: test case 2", groups = {"regression"})
    public void test02(){
        page.messageField.sendKeys("It's possible to get better in Java");
        page.showMessageBtn.click();
        Assert.assertEquals(page.displayedMessage.getText(), "It's possible to get better in Java");
    }

    @Test(testName = "US101: Verify Input message: test case 3", groups = {"regression", "smoke"})
    public void test03(){
        page.messageField.sendKeys("I have to try harder to get better");
        page.showMessageBtn.click();
        Assert.assertEquals(page.displayedMessage.getText(), "I have to try harder to get better");
    }

}
