package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(expectedName, loginPage.getUserName(expectedName));    }


    @ParameterizedTest
    @CsvFileSource(resources = "/loginDataNoPassword.csv", numLinesToSkip = 1)
    public void loginNoPassword(String username, String expectedMessage) {
        loginPage.enterUserName(username);
        loginPage.clickLoginButton();

        assertEquals(expectedMessage, loginPage.getPasswordErrorMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/loginDataNoCredentials.csv", numLinesToSkip = 1)
    public void loginNoCredentials(String expectedUsernameError, String expectedPasswordError) {

        loginPage.clickLoginButton();

        Assertions.assertAll(
                () -> assertEquals(expectedUsernameError, loginPage.getUsernameErrorMessage()),
                () -> assertEquals(expectedPasswordError, loginPage.getPasswordErrorMessage())
        );
    }

    @AfterEach
    public void quitDriver() {
        BaseTest.tearDown();
    }

}
