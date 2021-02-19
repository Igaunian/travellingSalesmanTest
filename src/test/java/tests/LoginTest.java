package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.LoginPage;

public class LoginTest {

    LoginPage loginPage;

    @BeforeEach
    public void setupEach() {
        BaseTest.setup();
        loginPage = new LoginPage(BaseTest.driver);
        loginPage.navigateToMainPage();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void loginValidCredentials(String username, String password, String expectedName) {
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        Assertions.assertEquals(expectedName, loginPage.getUserName(expectedName));    }


    @ParameterizedTest
    @CsvFileSource(resources = "/loginDataNoPassword.csv", numLinesToSkip = 1)
    public void loginNoPassword(String username, String expectedMessage) {
        loginPage.enterUserName(username);
        loginPage.clickLoginButton();

        Assertions.assertEquals(expectedMessage, loginPage.getPasswordErrorMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/loginDataNoCredentials.csv", numLinesToSkip = 1)
    public void loginNoCredentials(String expectedMessages) {
        String[] expectedMessagesArray = expectedMessages.split(";");
        String[] errorMessages = new String[expectedMessagesArray.length];

        loginPage.clickLoginButton();

        errorMessages[0] = loginPage.getUsernameErrorMessage();
        errorMessages[1] = loginPage.getPasswordErrorMessage();

        Assertions.assertArrayEquals(expectedMessagesArray, errorMessages);
    }

    @AfterEach
    public void quitDriver() {
        BaseTest.tearDown();
    }

}
