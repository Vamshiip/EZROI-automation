package stepdefinitions;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;


public class WorkflowSteps {

    WebDriver driver;
    WebDriverWait wait;
    
    private void setupWebDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        }
    }

    @Given("The user launches the browser and navigates to the Yellow URL")
    public void launchBrowserAndNavigateToURL() {
    	setupWebDriver();
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver.get("https://yellow-dune-0dc4f8f1e.5.azurestaticapps.net/sign-in");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println("Taking screenshot...");
        Utils.takeScreenshot(driver);
        System.out.println("Screenshot captured.");
    }

    @When("The user enters valid credentials {string} and {string}")
    public void enterCredentials(String username, String password) {
        driver.findElement(By.name("userName")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Sign In']")).click();
    }

    @Then("The user should be logged in successfully")
    public void verifyLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),\"Work Flow\")]")));
    }

    @When("The user searches for the record {string}")
    public void searchRecord(String recordId) {
        WebElement searchBox = driver.findElement(By.xpath("//div//input[@placeholder='Search...']"));
        searchBox.sendKeys(recordId);
        searchBox.sendKeys(Keys.ENTER);
    }

    @When("Selects the record")
    public void selectRecord() throws InterruptedException {
        WebElement record = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='hover:underline']")));
        record.click();
        Thread.sleep(5000);
        Utils.takeScreenshot(driver);
    }

    @When("Updates the internal status to {string}")
    public void updateInternalStatus(String status) throws InterruptedException {
        WebElement statusDropdown = driver.findElement(By.xpath("//div[@id='react-select-10-placeholder']/following::div[@class='select-dropdown-indicator'][1]"));
        statusDropdown.click();
        Thread.sleep(2000);
        WebElement statusOption = driver.findElement(By.xpath("//div[@id='react-select-10-option-3']"));
        statusOption.click();
    }

    @Then("The order details should be updated successfully")
    public void verifyOrderUpdate() {
    	WebElement updateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
 //       WebElement updateButton = driver.findElement(By.xpath("//button[@type='submit']"));
        updateButton.click();
    }
//    @Given("The user navigates to the {string} tab")
//    public void navigateToTab(String tabName) {
//        WebElement tab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[normalize-space(text())='" + tabName + "']")));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(tab).click().perform();
//    }

    @Given("The user navigates to the {string} tab")
    public void navigateToTab(String tabName) {
        String xpath = "//*[normalize-space(text())='" + tabName + "']"; // Default XPath

        // If the tab uses a different XPath format, handle it dynamically here
        if (tabName.equalsIgnoreCase("Invoice")) {
            xpath = "//div[@role='tab' and contains(text(), '" + tabName + "')]";
        }

        WebElement tab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        Actions actions = new Actions(driver);
        actions.moveToElement(tab).click().perform();
    }

      

//    @Given("The user navigates to the {string} tab")
//    public void navigateToTab(String tabName) throws InterruptedException {
//    //	setupWebDriver();
//        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + tabName + "']")));
//    //	WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Files']")));
//
//    	Actions actions = new Actions(driver);
//        actions.moveToElement(tab).click().perform();
//    }

    @When("The user uploads the file {string}")
    public void uploadFile(String filePath) throws IOException, InterruptedException {
        WebElement uploadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='text-lg']")));
        uploadButton.click();
        WebElement browseFilesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='browse files']")));
        browseFilesButton.click();
        String autoitScript = "C:\\Users\\admin\\Desktop\\AutoIT\\FileUpload.exe";
        Runtime.getRuntime().exec(new String[]{autoitScript, filePath});
        Thread.sleep(5000);
        System.out.println(filePath);
    }

    @Then("The file should be uploaded successfully")
    public void verifyFileUpload() {
    	//Upload File in popup
    			WebElement uploadButtonpopup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,\"--upload uppy-c-btn-primary\")]")));
    			uploadButtonpopup.click();


    			// Wait for file to upload completely (e.g., waiting for upload confirmation or progress bar to complete)
    			WebElement uploadConfirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Complete\"]")));
    			System.out.println("File uploaded successfully!");

    			// Click on "Done" in the upload popup
    			WebElement doneButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Done']")));
    			doneButton.click();
    			
    }

    //complete button after file upload
    @When("The user clicks on {string}")
    public void clickButton(String buttonName) throws InterruptedException {
//    	WebElement chatbot = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"VizExIcon__IconWrapper-ofjc9u-0 AkFzP\"]")));
//    	chatbot.click();
    	WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),\"Generate PDF\")]")));
        button.click();
    }

    @When("Refreshes the page")
    public void refreshPageUntilFinalRecordIsVisible() {
        int maxRetries = 5; // Maximum number of refresh attempts
        int retryInterval = 10000; // Interval between refresh attempts in milliseconds (5 seconds)
        boolean isRecordVisible = false;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                // Click the refresh button
                WebElement refreshButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='mt-5 mr-5']")));
                refreshButton.click();

                // Wait briefly before checking for "Final Records"
                Thread.sleep(retryInterval);

                // Check if "Final Records" text is visible
                WebElement finalRecord = driver.findElement(By.xpath("//p[contains(text(),'Final Records')]"));
                if (finalRecord.isDisplayed()) {
                    isRecordVisible = true;
                    break; // Exit the loop once the record is visible
                }
            } catch (Exception e) {
                // Handle cases where the element is not yet visible or other exceptions occur
                System.out.println("Attempt " + attempt + ": Final Records not visible yet.");
            }
        }

        if (!isRecordVisible) {
            throw new AssertionError("Final Records text did not appear within the timeout period.");
        }
    }


    @Then("The final record should be displayed")
    public void verifyFinalRecord() {
        WebElement finalRecord = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Final Records')]")));
        assert finalRecord.isDisplayed();
    }
    
//    @Given("The user navigates to the {string} tab")
//    public void navigateToinvoiceTab(String tabName) {
////        WebElement invoicetab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role=\"tab\" and contains(text(), '\" + tabName + \"')]")));
//    	WebElement invoicetab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='tab' and contains(text(), '" + tabName + "')]")));
//
//        Actions actions = new Actions(driver);
//        actions.moveToElement(invoicetab).click().perform();
//    }
//    


    @When("Sends the email")
    public void sendEmail() {
        try {
            // Locate the "Email Invoice" button
            WebElement sendButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Email Invoice')]")));
            
            // Scroll the element into view using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);
            
            // Wait until the element is clickable
            wait.until(ExpectedConditions.elementToBeClickable(sendButton));
            
            // Click the button using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sendButton);
            
            System.out.println("Email invoice button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error while clicking the 'Email Invoice' button.");
            e.printStackTrace();
        }
    }
    
    @And("confirms the email action")
    public void emailpopup() {
    	WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),\"Send Email\")]")));
    	email.click();
    }

    @Then("The email should be sent successfully")
    public void verifyEmailSent() {
        System.out.println("Email sent successfully");
    }

    @Then("The status {string} should be displayed")
    public void verifyStatus(String statusText) {
        WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), '" + statusText + "')]")));
        System.out.println("Status: " + status.getText());
    }
}

