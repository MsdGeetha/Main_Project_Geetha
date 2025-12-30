package data_driven_testing;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class CommonDataExcel_Test {

	public static void main(String[] args) throws Exception {
		FileInputStream file = new FileInputStream("./src/test/resources/TestData.xlsx");
		
		//Open the file
		Workbook wb = WorkbookFactory.create(file);
		
		//get sheet
		Sheet sheet1 = wb.getSheet("Sheet1");
		
		//get row
		Row row1 = sheet1.getRow(0);
		
		//get cell or column
		Cell cell1 = row1.getCell(0);
		//get the value which is present inside 
		String company = cell1.getStringCellValue();
		
		System.out.println(company);
		
		String place=row1.getCell(1).getStringCellValue();
		System.out.println(place);
		
		//2nd row 
		Row row2 = sheet1.getRow(1);
		
		//column 
		String company2 = row2.getCell(0).getStringCellValue();
		System.out.println(company2);
		
		wb.close();

	}

}
