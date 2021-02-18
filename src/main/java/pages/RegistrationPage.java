package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class RegistrationPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Nincs fiókja? Regisztráljon')]")
    private WebElement registrationLink;
    @FindBy(xpath = "//div[contains(@class, 'MuiTextField-root')]//input")
    private List<WebElement> regDataFields;
    private Select sexDropDown = new Select(driver.findElement(By.id("gender")));
    @FindBy(id = "drivingLicense")
    private WebElement drivingLicenceCheckBox;
    @FindBy(xpath = "//button//span[contains(text(),'Regisztráció')]")
    private WebElement registrationButton;
    @FindBy(id = "birthDate")
    private WebElement birthdateField;
    @FindBy(id = "gdpr")
    private WebElement acceptTermsCheckBox;
    @FindBy(xpath = "//div[@class='MuiAlert-message']")
    private WebElement registrationSuccessMessage;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void clickRegistrationLink() {
        registrationLink.click();
    }

    public void fillInTheFields(String[] regData) {
        int n = 0;

        for (int i = 0; i < regDataFields.size(); i++) {
            regDataFields.get(i).sendKeys(regData[i]);
            n++;
        }

        sexDropDown.selectByValue(regData[n]);

        if (regData[n + 1].equals("true")) {
            drivingLicenceCheckBox.click();
        }
    }

    public void fillInTheBirthDate(String date) {
        birthdateField.sendKeys(date);
    }

    public void clickRegistrationButton() {
        registrationButton.click();
    }

    public void clickAcceptTermsCheckBox() {
        acceptTermsCheckBox.click();
    }

    public String getRegSuccessMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(registrationSuccessMessage));
            return registrationSuccessMessage.getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
