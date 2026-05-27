package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import Utility.ScreenshotUtility;
import reports.ExtentManager;

public class TestListener implements ITestListener {

	private static ThreadLocal<ExtentTest>
    extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        ExtentManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
    	
    	ExtentTest test =
    	        ExtentManager.getInstance()
    	                .createTest(
    	                        result.getMethod()
    	                                .getMethodName());

    	extentTest.set(test);

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.get().pass("Test Passed");
    }

    
    @Override
    public void onTestFailure(ITestResult result) {

        String base64Screenshot =
                ScreenshotUtility
                        .captureScreenshotBase64();

        extentTest.get()
                .fail(result.getThrowable());

        extentTest.get()
                .fail(
                        MediaEntityBuilder
                                .createScreenCaptureFromBase64String(
                                        base64Screenshot)
                                .build());
    }
    @Override
    public void onFinish(ITestContext context) {
    	extentTest.remove();
        ExtentManager.flushReport();
    }
}