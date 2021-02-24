package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.LoginPage;
import pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.*;


public class RegistrationTest {

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
    @CsvFileSource(resources = "/validRegistrationData.csv", numLinesToSkip = 1)
    public void validRegistrationTest(String data) {
        loginPage.clickRegistrationLink();

        RegistrationPage registrationPage = new RegistrationPage(BaseTest.driver);

        String[] dataList = data.split(";");

        registrationPage.fillInTheFields(dataList);
        registrationPage.clickAcceptTermsCheckBox();
        registrationPage.clickRegistrationButton();

        String successMessage = registrationPage.getRegSuccessMessage();

        loginPage.enterUserName(dataList[0]);
        loginPage.enterPassword(dataList[2]);
        loginPage.clickLoginButton();
        boolean loggedInUsername = loginPage.isUserName(dataList[3] + " " + dataList[4] + " " + dataList[5]);
        loginPage.clickLogoutButton();

        assertAll(
                () -> assertTrue(successMessage.contains("Köszönjük a regisztrációt")),
                () -> assertTrue(loggedInUsername)
        );

    }

    // TODO: change assert for error message
    // since there is no error message at the moment when we try to log in with a nonexistent user,
    // I use a workaround, and assert that the logged in user's name doesn't appear
    @ParameterizedTest
    @CsvFileSource(resources = "/validRegistrationData.csv", numLinesToSkip = 1)
    public void registrationTestDontAcceptTheTerms(String data) {
        loginPage.clickRegistrationLink();

        RegistrationPage registrationPage = new RegistrationPage(BaseTest.driver);

        String[] dataList = data.split(";");

        registrationPage.fillInTheFields(dataList);
        registrationPage.clickRegistrationButton();
        boolean isErrorMessage = registrationPage.isAcceptTermsErrorMessage();

        registrationPage.clickLoginLink();

        loginPage.enterUserName(dataList[0]);
        loginPage.enterPassword(dataList[2]);
        loginPage.clickLoginButton();
        boolean loggedInUsername = loginPage.isUserName(dataList[3] + " " + dataList[4] + " " + dataList[5]);

        assertAll(
                () -> assertFalse(loggedInUsername),
                () -> assertTrue(isErrorMessage)
        );
    }

    @AfterAll
    public static void tearDown() {
        BaseTest.tearDown();
    }
}
