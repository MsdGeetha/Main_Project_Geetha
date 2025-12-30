package generic_libraries;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	/**
	 * This method which is used to read the single data from the excel
	 * @param sheetName
	 * @param rowNo
	 * @param celNo
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String readDataFromExcel(String sheetName,int rowNo,int celNo ) throws EncryptedDocumentException, IOException {
		FileInputStream fi = new FileInputStream(IpathConstants.exclePath);
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet(sheetName);
		String value=sh.getRow(rowNo).getCell(celNo).getStringCellValue();
		return value;
	}
	/**
	 * This method which is used to read the data from the multiple rows and columns return in the list
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public List<String> readMultipleDataFromExcel(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fi = new FileInputStream(IpathConstants.exclePath);
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		int celCount=sh.getRow(0).getLastCellNum();
		List<String> list = new ArrayList<String>();
		for(int i=0;i<=rowCount;i++) {
			for(int j=0;j<celCount;j++) {
				String value=sh.getRow(i).getCell(j).getStringCellValue();
				list.add(value);
			}
		}
		return list;
	}
	/**
	 * this method which is used to get the row count
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fi = new FileInputStream(IpathConstants.exclePath);
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet(sheetName);
		int rowCount=sh.getLastRowNum();
		return rowCount;
	}
	/**
	 * This method which is used to get the cell count
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getCellCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fi = new FileInputStream(IpathConstants.exclePath);
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet(sheetName);
		int celCount = sh.getRow(0).getLastCellNum();
		return celCount;
	}
	/**
	 * This method whic is used to write the single data into the excel sheet
	 * @param sheetName
	 * @param rowNo
	 * @param celNo
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeDataIntoExcel(String sheetName,int rowNo,int celNo,String value) throws EncryptedDocumentException, IOException {
		FileInputStream fi = new FileInputStream(IpathConstants.exclePath);
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNo);
		Cell cel = row.createCell(celNo);
		cel.setCellType(CellType.STRING);
//		cel.setCellType(CellType.NUMERIC);
		cel.setCellValue(value);
		
		FileOutputStream fOut = new FileOutputStream(IpathConstants.exclePath);
		wb.write(fOut);
		wb.close();
	}
	/**
	 * This is the method which is used to read the data for the TestNG from a sheet
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public Object[][] readingDataForTestNG(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(IpathConstants.exclePath);
		Workbook wb = WorkbookFactory.create(file);
		Sheet sh = wb.getSheet(sheetName);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
