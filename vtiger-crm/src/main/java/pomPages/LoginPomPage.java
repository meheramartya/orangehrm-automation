package pomPages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// import utils.PropertyFileUtil;  // COMMENTED OUT

public class LoginPomPage {

    // PropertyFileUtil pfu = new PropertyFileUtil();  // COMMENTED OUT
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//input[@name = 'user_name']")
    private WebElement userNameTf;
    
    @FindBy(xpath = "//input[@name = 'user_password']")
    private WebElement passwordTf;
    
    @FindBy(xpath = "//input[@id = 'submitButton']")
    private WebElement loginBtn;
    
    public LoginPomPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    // ✅ UPDATED: Accept parameters
    public void Login(String username, String password) throws IOException {
        
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle());
        
        wait.until(ExpectedConditions.visibilityOf(userNameTf));
        wait.until(ExpectedConditions.elementToBeClickable(userNameTf));
        userNameTf.clear();
        userNameTf.sendKeys(username);
        System.out.println("✔ Username entered: " + username);
        
        wait.until(ExpectedConditions.visibilityOf(passwordTf));
        wait.until(ExpectedConditions.elementToBeClickable(passwordTf));
        passwordTf.clear();
        passwordTf.sendKeys(password);
        System.out.println("✔ Password entered");
        
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();
        System.out.println("✔ Login button clicked");
        
        wait.until(ExpectedConditions.urlContains("module=Home"));
        System.out.println("✔ Login successful");
    }
}