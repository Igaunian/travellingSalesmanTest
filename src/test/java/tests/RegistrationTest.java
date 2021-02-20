package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.LoginPage;
import pages.RegistrationPage;


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

        Assertions.assertAll(
                () -> Assertions.assertTrue(successMessage.contains("Köszönjük a regisztrációt")),
                () -> Assertions.assertTrue(loggedInUsername)
        );

    }

    @AfterAll
    public static void tearDown() {
        BaseTest.tearDown();
    }
}
