package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DatabasePage {
    private WebDriver driver;
    public DatabasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getPasswordByEmail(String email) {
        return driver.findElement(By.xpath("//td[text()='" + email + "']/following-sibling::td"));
    }

}
