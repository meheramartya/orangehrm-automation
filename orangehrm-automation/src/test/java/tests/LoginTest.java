package tests;

import pages.DashboardPage;
import pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import dataproviders.LoginDataProvider;
import drivers.DriverManager;
import listeners.RetryAnalyzer;

public class LoginTest extends BaseClass {
	
	@Test(dataProvider ="loginData",
			dataProviderClass = LoginDataProvider.class)
			//retryAnalyzer = RetryAnalyzer.class)
	public void loginTest(String username,String password) {
		LoginPage loginpage = new LoginPage();

		DashboardPage dashboardPage = loginpage.loginToApplication(username,password);
		
		Assert.assertTrue(dashboardPage.isDashboardDisplayed());
		//Assert.assertTrue(false);
	}

}
