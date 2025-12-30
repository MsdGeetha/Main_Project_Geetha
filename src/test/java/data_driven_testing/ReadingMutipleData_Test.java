package data_driven_testing;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadingMutipleData_Test {

	public static void main(String[] args) throws Exception{
		FileInputStream file = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(file);
		Sheet sh = wb.getSheet("Sheet3");
		int rowCount = sh.getLastRowNum();
		int cellCount = sh.getRow(0).getLastCellNum();
		for(int i =0;i<=rowCount;i++) {
			for(int j=0;j<cellCount;j++) {
				String value = sh.getRow(i).getCell(j).getStringCellValue();
				System.out.print(value+" ,");
			}
			System.out.println();
		}

	}

}
