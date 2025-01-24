package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import java.io.IOException;
import java.time.Duration;

import config.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProcessPage {
    WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(ProcessPage.class);

    By globalSearchField = By.xpath("//div//input[@placeholder='Search...']");
    By recordLink = By.xpath("//a[@class = 'hover:underline']");
    By internalStatusDropdown = By.xpath("//div[@id='react-select-10-placeholder']/following::div[@class='select-dropdown-indicator'][1]");
    By approveOption = By.xpath("//div[@id='react-select-10-option-3']");
    By updateOrderDetailsButton = By.xpath("//button[@type='submit']");
    By filesTab = By.xpath("//*[text()='Files']");
    By uploadButton = By.xpath("//span[@class='text-lg']");
    By browseFilesButton = By.xpath("//button[text()='browse files']");
    By uploadPopupButton = By.xpath("//button[contains(@class,'--upload uppy-c-btn-primary')]");
    By doneButton = By.xpath("//button[text()='Done']");
    By generatePdfButton = By.xpath("//button[text()='Generate PDF']");
    By refreshButton = By.xpath("//*[@class='mt-5 mr-5']");
    By finalRecordsText = By.xpath("//p[contains(text(),'Final Records')]");
    By invoiceTab = By.xpath("//div[contains(text(),'Invoice')]");
    By emailInvoiceButton = By.xpath("//span[contains(text(),'Email Invoice')]");
    By sendMailButton = By.xpath("//button[contains(@class,'bg-green-500 active:bg-green-700')]");

    public ProcessPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void searchAndApprove(String orderId) {
        try {
            driver.findElement(globalSearchField).sendKeys(orderId, Keys.ENTER);
            wait.until(ExpectedConditions.elementToBeClickable(recordLink)).click();
            wait.until(ExpectedConditions.elementToBeClickable(internalStatusDropdown)).click();
            wait.until(ExpectedConditions.elementToBeClickable(approveOption)).click();
            driver.findElement(updateOrderDetailsButton).click();
        } catch (Exception e) {
            logger.error("Failed to search and approve order: " + orderId, e);
        }
    }

    public void handleFiles() {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(filesTab))).click().perform();
            wait.until(ExpectedConditions.elementToBeClickable(uploadButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(browseFilesButton)).click();
            Runtime.getRuntime().exec(new String[] {ConfigurationManager.getProperty("autoit.script.path"), ConfigurationManager.getProperty("file.upload.path")});
            wait.until(ExpectedConditions.elementToBeClickable(uploadPopupButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(doneButton)).click();
        } catch (Exception e) {
            logger.error("File handling failed", e);
        }
    }

    public void generateAndVerifyPdf() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(generatePdfButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(refreshButton)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(finalRecordsText));
        } catch (Exception e) {
            logger.error("PDF generation and verification failed", e);
        }
    }

    public void sendInvoiceEmail() {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(invoiceTab))).click().perform();
            wait.until(ExpectedConditions.elementToBeClickable(emailInvoiceButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(sendMailButton)).click();
        } catch (Exception e) {
            logger.error("Sending invoice email failed", e);
        }
    }
}