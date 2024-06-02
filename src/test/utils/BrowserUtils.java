package utils;

import org.openqa.selenium.WebDriver;
import tests.BaseTest;

import java.util.Set;

public class BrowserUtils extends BaseTest {
    private WebDriver driver;

    public BrowserUtils(WebDriver driver){
        this.driver = driver;
    }

    public void switchToNextWindow() {
        Set<String> set = driver.getWindowHandles();
        for (String each: set){
            if (!each.equals(driver.getWindowHandle()))
                driver.switchTo().window(each);
        }
    }
}
