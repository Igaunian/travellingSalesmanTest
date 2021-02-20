package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//span[@class='MuiButton-label' and text() = 'Bejelentkezés']")
    private WebElement loginButton;
    @FindBy(xpath = "//span[contains(text(),'Kilépés')]//ancestor::button/preceding-sibling::div")
    private WebElement loggedInUsername;
    @FindBy(xpath = "//a[contains(text(),'Nincs fiókja? Regisztráljon')]")
    private WebElement registrationLink;
    @FindBy(xpath = "//p[@id='username-helper-text']")
    private WebElement usernameErrorMessage;
    @FindBy(xpath = "//p[@id='password-helper-text']")
    private WebElement passwordErrorMessage;
    @FindBy(xpath = "//span[contains(text(),'Kilépés')]")
    private WebElement logoutButton;

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

    public void loginWithEnterKey() {
        passwordField.sendKeys(Keys.ENTER);
    }

    public void clickRegistrationLink() {
        registrationLink.click();
    }

    public boolean isUserName(String username) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(loggedInUsername, username));
            return loggedInUsername.getText().equals(username);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getUsernameErrorMessage() {
        return usernameErrorMessage.getText();
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage.getText();
    }

    public boolean isPasswordFieldTypePassword() {
        return passwordField.getAttribute("type").equals("password");
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

}
