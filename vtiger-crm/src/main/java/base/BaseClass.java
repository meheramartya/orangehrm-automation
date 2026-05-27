package base;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;

import pomPages.HomePomPage;
import pomPages.LoginPomPage;
import utils.DatabaseUtils;
// import utils.PropertyFileUtil;  // COMMENTED OUT
import listeners.TestListener;
import listeners.UtilityObjectClass;

@Listeners(TestListener.class)
public class BaseClass {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public static WebDriver sdriver;

    DatabaseUtils dbUtil;
    // PropertyFileUtil pfu = new PropertyFileUtil();  // COMMENTED OUT

    protected String username;
    protected String password;
    String url;
    String browser;

    @BeforeSuite
    public void connectToDatabase() throws SQLException {
        dbUtil = new DatabaseUtils();
        dbUtil.connectToDB();
        Reporter.log("Database Connected", true);
    }

    @BeforeTest
    public void configParallelExe() {
        Reporter.log("Parallel Execution Configured", true);
    }

    @BeforeClass
    public void setUp() throws IOException {
        Reporter.log("Launching Browser", true);
        
        // GET VALUES FROM RUNTIME (-D PARAMETERS)
        browser = System.getProperty("browser", "chrome");
        username = System.getProperty("username", "admin");
        password = System.getProperty("password", "password");
        url = System.getProperty("url", "http://localhost:8888/");
        long timeouts = Long.parseLong(System.getProperty("timeouts", "10"));
        
        Reporter.log("Browser: " + browser, true);
        Reporter.log("URL: " + url, true);
        Reporter.log("Username: " + username, true);

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            driver = new ChromeDriver();
        }

        sdriver = driver;
        UtilityObjectClass.setDriver(driver);
        
        Reporter.log("sdriver initialized: " + (sdriver != null ? "SUCCESS" : "FAILED"), true);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeouts));

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(url);

        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));

        Reporter.log("Browser Launched Successfully", true);
    }

    @BeforeMethod
    public void login() throws IOException {
        if (driver == null) {
            Reporter.log("ERROR: driver is null before login!", true);
            throw new IllegalStateException("WebDriver is null before login");
        }
        
        LoginPomPage lp = new LoginPomPage(driver);
        lp.Login(username, password);  // ✅ Pass parameters

        Reporter.log("Logged into Application", true);
    }

    @AfterMethod
    public void logout() {
        if (driver != null) {
            HomePomPage hp = new HomePomPage(driver);
            hp.Logout();
            Reporter.log("Logged out from Application", true);
        } else {
            Reporter.log("WARNING: driver is null during logout", true);
        }
    }

    @AfterClass
    public void quitBrowser() {
        if (driver != null) {
            driver.quit();
            Reporter.log("Browser Closed", true);
            UtilityObjectClass.setDriver(null);
        } else {
            Reporter.log("WARNING: driver is null during browser close", true);
        }
    }

    @AfterTest
    public void closeConfigPE() {
        Reporter.log("Closed Parallel Execution Config", true);
    }

    @AfterSuite
    public void disconnectDB() throws SQLException {
        if (dbUtil != null) {
            dbUtil.disconnectWithDB();
            Reporter.log("Database Disconnected", true);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}