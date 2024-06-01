import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class _01_ClassTask_Solved {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://selenium-practice-app.herokuapp.com/?#/inputs");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    @Test(testName = "US101: Verify Input message: test case 1", groups = {"regression"})
    public void test01(){
        driver.findElement(By.id("message")).sendKeys("Java is hard");
        driver.findElement(By.name("button1")).click();
        Assert.assertEquals(driver.findElement(By.name("message1")).getText(), "Java is hard");
    }

    @Test(testName = "US101: Verify Input message: test case 2", groups = {"regression"})
    public void test02(){
        driver.findElement(By.id("message")).sendKeys("It's possible to get better in Java");
        driver.findElement(By.name("button1")).click();
        Assert.assertEquals(driver.findElement(By.name("message1")).getText(), "It's possible to get better in Java");
    }

    @Test(testName = "US101: Verify Input message: test case 3", groups = {"regression", "smoke"})
    public void test03(){
        driver.findElement(By.id("message")).sendKeys("I have to try harder to get better");
        driver.findElement(By.name("button1")).click();
        Assert.assertEquals(driver.findElement(By.name("message1")).getText(), "I have to try harder to get better");
    }

}
