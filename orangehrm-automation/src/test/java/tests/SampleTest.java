package tests;

import org.testng.annotations.Test;

import base.BaseClass;
import drivers.DriverManager;

public class SampleTest extends BaseClass {

    @Test
    public void launchTest() {

        System.out.println(
                DriverManager.getDriver().getTitle());
    }
}