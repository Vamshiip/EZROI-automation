package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    By usernameField = By.name("userName");
    By passwordField = By.name("password");
    By signInButton = By.xpath("//button[text()='Sign In']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        try {
            logger.info("Attempting to log in with username: " + username);
            driver.findElement(usernameField).sendKeys(username);
            driver.findElement(passwordField).sendKeys(password);
            driver.findElement(signInButton).click();
        } catch (Exception e) {
            logger.error("Login failed", e);
        }
    }
}
