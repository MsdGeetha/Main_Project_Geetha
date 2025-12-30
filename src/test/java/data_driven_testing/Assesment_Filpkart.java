package data_driven_testing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;

public class Assesment_Filpkart {

	public static void main(String[] args) throws Exception {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//input[contains(@title,'Search')]")).sendKeys("Laptop",Keys.ENTER);

		List<WebElement> products = driver.findElements(By.xpath("//div[@class='RG5Slk']"));
//		String product = driver.findElement(By.xpath("(//div[@class='RG5Slk'])[1]")).getText();
//		for(WebElement p :products ) {
//			System.out.println(p.getText());
//		}
		 
		List<WebElement> prices = driver.findElements(By.xpath("//div[@class='hZ3P6w DeU9vF']"));
//		String price=driver.findElement(By.xpath("(//div[@class='hZ3P6w DeU9vF'])[1]")).getText();

		//driver.quit();
		FileInputStream file = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(file);
		Sheet sheet = wb.getSheet("Product Deatils");
		
		Row rowHeader = sheet.createRow(0);
		rowHeader.createCell(0).setCellValue("Product Details");
		rowHeader.createCell(1).setCellValue("Product Prices");
		
		for(int i =0;i<products.size();i++) {
			Row row = sheet.createRow(i+1);
			
			String eachProducts = products.get(i).getText();
			String eachPrice = prices.get(i).getText();
			
			row.createCell(0).setCellValue(eachProducts);
			row.createCell(1).setCellValue(eachPrice);
			
		}
		
//		Row row=sheet.createRow(0);
//		Cell cell1 = row.createCell(0);
//		cell1.setCellValue(product);
//		Cell cell2 = row.createCell(1);
//		cell2.setCellValue(price);
		
		
		FileOutputStream fileClose = new FileOutputStream("./src/test/resources/TestData.xlsx");
		System.out.println("data is writting in the file");
		wb.write(fileClose);
		
		
		file.close();
		wb.close();
		driver.quit();
		System.out.println("Data written successfully!");
//		
	}

}
