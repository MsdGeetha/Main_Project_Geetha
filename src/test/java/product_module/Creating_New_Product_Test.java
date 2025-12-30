package product_module;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import generic_libraries.ExcelUtil;
import generic_libraries.FileUtils;
import generic_libraries.JavaUtils;
import generic_libraries.WebDriver_Utils;

//Test case 6
public class Creating_New_Product_Test {

	public static void main(String[] args)throws Exception {
		//creating objects of the Util's
		FileUtils file = new FileUtils();
		ExcelUtil excel = new ExcelUtil();
		JavaUtils jUtil = new JavaUtils();
		WebDriver_Utils wd = new WebDriver_Utils();
		
		
//		FileInputStream file = new FileInputStream("./src/test/resources/CommonData.properties");
//		Properties pObj = new Properties();
//		pObj.load(file);
		
		String browser=file.readDataFromProperties("browser");
		String url = file.readDataFromProperties("url");
		String username = file.readDataFromProperties("username");
		String password = file.readDataFromProperties("password");
		
//		String browser=pObj.getProperty("browser");
//		String url=pObj.getProperty("url");
//		String username=pObj.getProperty("username");
//		String password=pObj.getProperty("password");
		
		WebDriver driver = null;
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
//			driver.manage().window().maximize();
			wd.maximizeWindow(driver);
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			wd.waitForPageLoad(driver, 10);
		}
		driver.get(url);
//		Login to application
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
//		Navigate to Home Page Click on 'Products'
		driver.findElement(By.linkText("Products")).click();
		
//		Click on 'Products'  lookup image
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		
//		FileInputStream fExcel = new FileInputStream("./src/test/resources/TestData.xlsx");
//		Workbook wb = WorkbookFactory.create(fExcel);
//		Sheet sh = wb.getSheet("Products");
//		String cellValue=sh.getRow(0).getCell(1).getStringCellValue();
//		Random ran = new Random();
//		int random = ran.nextInt(500);
//		String productName = cellValue+"_"+random;
		
		String productName = excel.readDataFromExcel("Products", 0, 1)+"_"+jUtil.getRandom();
		
//		Enter mandatory fields with valid data
		driver.findElement(By.name("productname")).sendKeys(productName);
		
//		click on 'Save' button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verifying the product is created or not 
		String actual=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		String expted = productName;
		
		if(actual.contains(expted)) {
			System.out.println("Product has been created ");
		}
		else
		{
			System.out.println("Product is not created");
		}
		//SignOut
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		WebElement signout = driver.findElement(By.linkText("Sign Out"));
		
//		Actions act = new Actions(driver);
//		act.moveToElement(signout).click().perform();
		wd.mouseHoverAndClickOnEle(driver, signout);
		
		driver.quit();

	}

}
