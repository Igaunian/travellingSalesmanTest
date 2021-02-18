package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.LoginPage;

public class LoginTest {

    LoginPage loginPage = new LoginPage(BaseTest.driver);

    @BeforeAll
    public static void setup() {
        BaseTest.setup();
    }

    @BeforeEach
    public void setupEach() {
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
    public void closeDriver() {
        BaseTest.driver.close();
    }

    @AfterAll
    public static void tearDown() {
        BaseTest.tearDown();
    }
}
