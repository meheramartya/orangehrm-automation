package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import drivers.DriverManager;
import utilities.WaitUtility;

public class DashboardPage {
	
	public DashboardPage() {
		PageFactory.initElements(DriverManager.getDriver(),this);
	}
	
	@FindBy(xpath = "//h6[text()='Dashboard']")
	private WebElement dashboardHeader;
	
	public boolean isDashboardDisplayed() {
		WaitUtility.waitForElementToBeVisible(dashboardHeader, 20);
		
		return dashboardHeader.isDisplayed();
				
	}

}
