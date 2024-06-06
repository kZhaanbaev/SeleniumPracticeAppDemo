package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "username")
    public WebElement usernameInputField;

    @FindBy(name = "password")
    public WebElement passwordInputField;

    @FindBy(tagName = "button")
    public WebElement loginBtn;

    public void login(String username, String password){
        usernameInputField.sendKeys(username);
        passwordInputField.sendKeys(password);
        loginBtn.click();
    }
}
