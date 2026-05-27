package dataproviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import utilities.ExcelUtility;

public class LoginDataProvider {

    @DataProvider(name = "loginData")

    public Object[][] getLoginData() throws IOException {

        return ExcelUtility.getExcelData("LoginData");
    }
}