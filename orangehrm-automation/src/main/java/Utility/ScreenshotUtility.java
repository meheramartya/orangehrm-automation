package Utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import drivers.DriverManager;

public final class ScreenshotUtility {

    private ScreenshotUtility() {}

    public static String captureScreenshotBase64() {

        return ((TakesScreenshot)
                DriverManager.getDriver())
                .getScreenshotAs(
                        OutputType.BASE64);
    }
}