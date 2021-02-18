package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(xpath = "//span[@class='MuiButton-label' and text() = 'Bejelentkezés']")
    private WebElement loginButton;
    @FindBy(xpath = "//span[contains(text(),'Kilépés')]//ancestor::button/preceding-sibling::div")
    private WebElement loggedInUsername;
    @FindBy(xpath = "//a[contains(text(),'Nincs fiókja? Regisztráljon')]")
    private WebElement registrationLink;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void enterUserName(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickRegistrationLink() {
        registrationLink.click();
    }

    public String getUserName() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loggedInUsername));
            return loggedInUsername.getText();
        } catch (NoSuchElementException e) {
            return "Username is not visible";
        }
    }
}
