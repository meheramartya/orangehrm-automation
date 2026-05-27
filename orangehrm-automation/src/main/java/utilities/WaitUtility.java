package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import drivers.DriverManager;

public final class WaitUtility {

    private WaitUtility() {}

    public static void waitForElementToBeVisible(
            WebElement element,
            long duration) {

        WebDriverWait wait =
                new WebDriverWait(
                        DriverManager.getDriver(),
                        Duration.ofSeconds(duration));

        wait.until(
                ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickable(
            WebElement element,
            long duration) {

        WebDriverWait wait =
                new WebDriverWait(
                        DriverManager.getDriver(),
                        Duration.ofSeconds(duration));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        element));
    }

    public static void waitForTitleContains(
            String title,
            long duration) {

        WebDriverWait wait =
                new WebDriverWait(
                        DriverManager.getDriver(),
                        Duration.ofSeconds(duration));

        wait.until(
                ExpectedConditions.titleContains(title));
    }
}
