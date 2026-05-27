package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	private ExcelUtility() {}
	
	public static Object[][] getExcelData(String sheetName) throws IOException{
		FileInputStream fis = new FileInputStream("./src/test/resources/testdata/orangehrm-testdata.xlsx");
		
		XSSFWorkbook workbook  = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int rowCount = sheet.getLastRowNum();
		
		int cellCount = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rowCount][cellCount];
		
		DataFormatter formatter = new DataFormatter();
		
		for(int i = 1; i <= rowCount; i++) {
			
			Row row = sheet.getRow(i);
			
			for(int j = 0; j < cellCount; j++) {
				
				Cell cell = row.getCell(j);
				
				data[i - 1][j] = formatter.formatCellValue(cell);
			}
		}
		
		workbook.close();
		fis.close();
		
		return data;
	}

}
