package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Attachment;

public class Utils {
	// Method to take and attach a screenshot in Allure reports
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Add other utility methods as needed
    public static String getCurrentTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

}
