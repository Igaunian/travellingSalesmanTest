package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public final int TIMEOUT = Integer.parseInt(System.getenv("timeout"));
    static final String BASEURL = System.getenv("baseurl");
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, TIMEOUT);
    }

    public void navigateToMainPage(String baseurl) {
        driver.get(baseurl);
    }

    public void navigateToUrl(String url) {
        driver.get(BASEURL.concat(url));
    }
}
