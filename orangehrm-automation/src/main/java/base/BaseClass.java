package base;

import drivers.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import enums.BrowserType;
import factories.DriverFactory;
import utilities.PropertyUtility;

public class BaseClass {

    protected Logger logger =
            LogManager.getLogger(this.getClass());

    @BeforeClass
    public void setup() {

        String browser =
                PropertyUtility.getProperty("browser");

        WebDriver driver =
                DriverFactory.initializeBrowser(
                        BrowserType.valueOf(
                                browser.toUpperCase()));

        DriverManager.setDriver(driver);
        driver.manage().timeouts()
        .pageLoadTimeout(
                Duration.ofSeconds(60));

        logger.info(browser +
                " browser launched successfully");

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(
                        Duration.ofSeconds(
                                Long.parseLong(
                                        PropertyUtility
                                                .getProperty(
                                                        "implicitWait"))));

        driver.manage().timeouts()
                .pageLoadTimeout(
                        Duration.ofSeconds(
                                Long.parseLong(
                                        PropertyUtility
                                                .getProperty(
                                                        "pageLoadTimeout"))));
        logger.info("Opening URL now...");

        driver.get(
                PropertyUtility.getProperty("url"));

        logger.info("URL opened");

        logger.info("Application URL opened successfully");
    }

    @AfterClass
    public void tearDown() {

        if (DriverManager.getDriver() != null) {

            DriverManager.getDriver().quit();

            DriverManager.unload();

            logger.info("Browser closed successfully");
        }
    }
}