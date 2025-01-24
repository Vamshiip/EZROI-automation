package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class NewRequestPage {
    WebDriver driver;
    WebDriverWait wait;

    By newDocButton = By.xpath("//span[text()='New Document Request']");
    By firstNameField = By.xpath("//input[@placeholder='First Name']");
    By lastNameField = By.xpath("//input[@placeholder='Last Name']");
    By dobField = By.xpath("//input[@class='input h-11']");
    By submitButton = By.xpath("//button[@type='submit']");
    By medicalFacility = By.xpath("//div[@class=\" css-1yt0726\"]");

    public NewRequestPage(WebDriver driver) {
        this.driver = driver;
        // Updated WebDriverWait to use Duration
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void submitNewRequest(String firstName, String lastName, String dob) {
    	
        wait.until(ExpectedConditions.elementToBeClickable(newDocButton)).click();
        driver.findElement(medicalFacility).sendKeys("Alexa");
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(dobField).sendKeys(dob);
        driver.findElement(submitButton).click();
        driver.quit();
    }
}
