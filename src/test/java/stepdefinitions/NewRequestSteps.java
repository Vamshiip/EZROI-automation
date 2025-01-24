package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Utils;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NewRequestSteps {
    
    WebDriver driver;

    @Given("the user is on the login page")
    public void givenUserIsOnLoginPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://yellow-dune-0dc4f8f1e.5.azurestaticapps.net/sign-in");
    }

    @When("the user logs in with valid credentials")
    public void whenUserLogsIn() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("userName")).sendKeys("vamshi.qa6@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Test@123");
        driver.findElement(By.xpath("//button[text()='Sign In']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/button[@type='button']"))).click();
        
        System.out.println("Taking screenshot...");
        Utils.takeScreenshot(driver);
        System.out.println("Screenshot captured.");
    }

    @When("the user clicks on \"New Document Request\"")
    public void whenUserClicksNewDocumentRequest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> newDoc = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[text()='New Document Request']")));
        newDoc.get(0).click();
        Thread.sleep(5000);
    }

    @When("the user fills in the document request details")
    public void whenUserFillsDocumentDetails() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement valueInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"react-select-14-input\"]")));
        valueInput.sendKeys("alexa");
      //div[@id="react-select-14-option-0"]
        Thread.sleep(3000);
      //  valueInput.click();
        List<WebElement> dropdownOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));
        Thread.sleep(5000);
        if (!dropdownOptions.isEmpty()) {
            dropdownOptions.get(0).click();
        } else {
            System.out.println("No options found in the dropdown.");
        }

        driver.findElement(By.xpath("//input[@placeholder=\"First Name\"]")).sendKeys("Viki");
        driver.findElement(By.xpath("//input[@placeholder=\"Last Name\"]")).sendKeys("Roy");

        driver.findElement(By.xpath("//input[@placeholder='AKA']")).sendKeys("RK67");
        driver.findElement(By.xpath("//input[@class=\"input h-11\"]")).sendKeys("05-06-1995");
        driver.findElement(By.xpath("//div[@class=\"react-datepicker-wrapper\"]//following::input[@class=\"input h-11\"]")).sendKeys("10-15-2024");

        WebElement requestType = driver.findElement(By.xpath("//div[@class=\"select-dropdown-indicator\"]"));
        requestType.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//span[@class=\"ml-2\"]"))).click();
        
 //       driver.findElement(By.xpath("//h6[text()=\"Medical\"]")).click();
    }

    @When("the user uploads medical, billing, and X-ray documents")
    public void whenUserUploadsDocuments() throws InterruptedException {
    	driver.findElement(By.xpath("//h6[text()=\"Medical\"]")).click();
        WebElement medical = driver.findElement(By.xpath("//label[@class=\"form-label mb-2\"]//following::input[@type=\"file\"][1]"));
        File file = new File("./front.png");
        medical.sendKeys(file.getAbsolutePath());
        Thread.sleep(5000);

        driver.findElement(By.xpath("//h6[text()=\"Billing\"]")).click();
        WebElement billing = driver.findElement(By.xpath("//label[@class=\"form-label mb-2\"]//following::input[@type=\"file\"][2]"));
        billing.sendKeys(file.getAbsolutePath());
        Thread.sleep(5000);

        driver.findElement(By.xpath("//h6[text()=\"X-Ray\"]")).click();
        WebElement Xray = driver.findElement(By.xpath("//label[@class=\"form-label mb-2\"]//following::input[@type=\"file\"][3]"));
        Xray.sendKeys(file.getAbsolutePath());
        Thread.sleep(5000);
    }

    @When("the user accepts the terms and conditions")
    public void whenUserAcceptsTermsAndConditions() {
        WebElement checkbox = driver.findElement(By.xpath("//input[@name=\"termsAndConditions\"]"));
        checkbox.click();
        Utils.takeScreenshot(driver);
        driver.quit();
    }
    

    @Then("the new request should be submitted successfully")
    public void thenRequestSubmittedSuccessfully() {
        // Implement verification to confirm request submission (e.g., success message, page change)
        assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Request submitted successfully')]")).isDisplayed());
    }
}
