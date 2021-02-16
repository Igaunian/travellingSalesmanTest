package tests;

import org.junit.jupiter.api.*;
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

    @Test
    public void loginValidCredentials() {
        loginPage.enterUserName("SF");
        loginPage.enterPassword("password");
        loginPage.clickLoginButton();

        Assertions.assertEquals("Ferenc Péter Sándor", loginPage.getUserName());
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
