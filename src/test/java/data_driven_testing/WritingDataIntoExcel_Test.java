package data_driven_testing;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WritingDataIntoExcel_Test {

	public static void main(String[] args) throws Exception {
		FileInputStream file = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(file);
		
		//Either we can create the sheet or get the sheet which is already present
		Sheet create = wb.getSheet("Testing");
		//Sheet sh = wb.getSheet("Sheet4");
		
		// we are creating the row in the sheet
		Row row = create.createRow(1);
		
		//We are creating the cell in the sheet
		Cell cell = row.createCell(0);
		
		//We are inserting the value to the cell
		cell.setCellValue("Geetha");
		

		//to write the data into the file
		FileOutputStream fileClose = new FileOutputStream("./src/test/resources/TestData.xlsx");
		System.out.println("data is writting in the file");
		wb.write(fileClose);
		
		wb.close();
		System.out.println("ending");

	}

}
