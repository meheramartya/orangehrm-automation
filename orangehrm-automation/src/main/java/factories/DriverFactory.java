package factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions; 
import enums.BrowserType;
import utilities.PropertyUtility;

public class DriverFactory {

	private DriverFactory() {}
	
	public static WebDriver initializeBrowser(BrowserType browser) {
	
	WebDriver driver;
	
	switch(browser) {
//		case CHROME:
//		
//			driver = new ChromeDriver();
//			break;
		case CHROME:

		    String executionMode =
		            PropertyUtility.getProperty(
		                    "executionMode");

		    if(executionMode.equalsIgnoreCase(
		            "local")) {

		        driver = new ChromeDriver();

		    } else {

		        ChromeOptions options =
		                new ChromeOptions();

		        options.addArguments(
		                "--no-sandbox");
		        options.addArguments("--headless=new");

		        options.addArguments(
		                "--disable-dev-shm-usage");

		        options.addArguments(
		                "--disable-gpu");

		        options.addArguments(
		                "--remote-allow-origins=*");

		        try {

		            driver =
		                    new RemoteWebDriver(

		                            new URL(
		                                    PropertyUtility
		                                            .getProperty(
		                                                    "gridUrl")),

		                            options);

		        } catch(MalformedURLException e) {

		            throw new RuntimeException(e);
		        }
		    }

		    break;
		    
		case EDGE:
			
			driver = new EdgeDriver();
			break;
			
		default:

            throw new IllegalArgumentException(
                    "Invalid Browser Type");
        }
	return driver;
			
	}
	
}
