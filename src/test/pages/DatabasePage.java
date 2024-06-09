package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DatabasePage {
    private WebDriver driver;
    public DatabasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getPasswordByEmail(String email) {
        return driver.findElements(By.xpath("//td[text()='" + email + "']/following-sibling::td"));
    }

    public WebElement getDeleteBtnByEmail(String email) {
        return driver.findElement(By.xpath("//td[text()='" + email + "']/following-sibling::td//i[text()='Delete']"));
    }

}
