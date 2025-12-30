package data_driven_testing;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FeatchingMutipleDataFromExcel_Test {

	public static void main(String[] args) throws Exception {
		FileInputStream file = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(file);
		Sheet sh = wb.getSheet("Sheet2");
		int count = sh.getLastRowNum();
		for(int i = 0;i<=count;i++) {
			String company = sh.getRow(i).getCell(0).getStringCellValue();
			String location = sh.getRow(i).getCell(1).getStringCellValue();
			System.out.println(company+" = "+location);
		}
		wb.close();
	}

}
