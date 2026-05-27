package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import drivers.DriverManager;
import utilities.WaitUtility;

public class LoginPage {

	public LoginPage() {
		PageFactory.initElements(DriverManager.getDriver(),this);
	}
	
	@FindBy(name="username")
	private WebElement usernameTextField;
	
	@FindBy(name="password")
	private WebElement passwordTextField;
	
	@FindBy(xpath = "//button[@type='submit']")
	    private WebElement loginButton;

	public DashboardPage loginToApplication(String username,String password)
	{
		WaitUtility.waitForElementToBeVisible(usernameTextField, 10);
		
		  usernameTextField.clear();
		  usernameTextField.sendKeys(username);
		  
		  passwordTextField.clear();
	      passwordTextField.sendKeys(password);
	      
	      loginButton.click();
	      
	      return new DashboardPage();

	}
}
