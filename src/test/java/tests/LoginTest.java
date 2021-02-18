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

        Assertions.assertEquals(expectedName, loginPage.getUserName());
    }

    @AfterEach
    public void quitDriver() {
        BaseTest.tearDown();
    }

}
