package testNG;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generic_libraries.ExcelUtil;
import generic_libraries.WebDriver_Utils;

public class DataProvider_GenericTest {
	@Test(dataProvider = "getData")
	public void excute(String cmpName,String place) {
		System.out.println(cmpName+"----->"+place);
	}
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException {
		ExcelUtil excel = new ExcelUtil();
		Object[][] result = excel.readingDataForTestNG("Dream_Companies");
		return result;
	}
}
