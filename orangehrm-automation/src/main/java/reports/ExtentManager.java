package reports;

//import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import constants.FrameworkConstants;

public final class ExtentManager {

    private ExtentManager() {}

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if(Objects.isNull(extent)) {

            extent = new ExtentReports();

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(
                            FrameworkConstants.REPORT_PATH);

            extent.attachReporter(spark);

            extent.setSystemInfo(
                    "Framework",
                    "OrangeHRM Automation");

            extent.setSystemInfo(
                    "Tester",
                    "Amartya");

            extent.setSystemInfo(
                    "Environment",
                    "QA");
        }

        return extent;
    }

    public static void flushReport() {

        if(Objects.nonNull(extent)) {

            extent.flush();

//            try {
//
//                Desktop.getDesktop().browse(
//                        new File(
//                                FrameworkConstants.REPORT_PATH)
//                                .toURI());
//
//            } catch(IOException e) {
//
//                e.printStackTrace();
//            }
        }
    }
}