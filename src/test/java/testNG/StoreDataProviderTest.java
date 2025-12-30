package testNG;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import generic_libraries.ExcelUtil;

public class StoreDataProviderTest {

	@DataProvider(name = "Crds")
	public Object[][] creentials() {
		Object[][] obj = new Object[2][2];
		obj[0][0] = "admin";
		obj[0][1] = "admin";
		
		obj[1][0] = "admin";
		obj[1][1] = "password";
		
		return obj;
	}
	
	@DataProvider(name = "store_Proucts")
	public Object[][] proucts() {
		
		Object[][] obj = new Object[3][2];
		obj[0][0] = "Dell";
		obj[0][1] =45000 ;
		
		obj[1][0] = "Hp";
		obj[1][1] = 50000;
		
		obj[2][0]="Samsung";
		obj[2][1]= 60000;
		
		return obj;
	}
	@DataProvider
	public Object[][] store_Company() throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(file);
		Sheet sh = wb.getSheet("Dream_Companies");
		int rowCount = sh.getLastRowNum()+1;
		int celCount = sh.getRow(0).getLastCellNum();
		Object[][] obj = new Object[rowCount][celCount];
		for(int i = 0;i<rowCount;i++) {
			for(int j=0;j<celCount;j++) {
				obj[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return obj;
	}
	//Reading the data from generic util's
	@DataProvider
	public Object[][] readData() throws EncryptedDocumentException, IOException {
		ExcelUtil excel = new ExcelUtil();
		Object[][] result = excel.readingDataForTestNG("Dream_Companies");
		return result;
	}
	
	
		
	
}
