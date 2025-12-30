package data_driven_testing;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadNumaricDataExcel_Test {

	public static void main(String[] args) throws Exception {
		FileInputStream file = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(file);
		Sheet sh = wb.getSheet("Sheet5");
		Cell cel = sh.getRow(0).getCell(2);
		
		DataFormatter format = new DataFormatter();
		String value = format.formatCellValue(cel);
		System.out.println(value);
		wb.close();
		
	}

}
