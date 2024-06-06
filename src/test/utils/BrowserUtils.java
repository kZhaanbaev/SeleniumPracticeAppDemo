package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BrowserUtils {
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

    public void waitNumberOfElementsToBeMoreThan(By by, int numberOfElements){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, numberOfElements));
    }

    public void moveIntoViewWithJsExecutor(WebElement element){
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public WebElement highlightElement(WebElement element){
        moveIntoViewWithJsExecutor(element);
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        for (int i = 0; i < 4; i++){
            if (i % 2 == 0){
                sleep(500);
                jsExecutor.executeScript("arguments[0].setAttribute('style','border: solid 2px red');", element);
                jsExecutor.executeScript("arguments[0].style.backgroundColor='yellow'", element);
            }else {
                sleep(500);
                jsExecutor.executeScript("arguments[0].setAttribute('style','border: none');", element);
                jsExecutor.executeScript("arguments[0].style.backgroundColor=null", element);
            }
        }
        return element;
    }

    public void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
