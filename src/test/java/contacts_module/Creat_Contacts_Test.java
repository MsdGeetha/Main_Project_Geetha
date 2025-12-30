package contacts_module;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

//Test Case - 3
public class Creat_Contacts_Test {

	public static void main(String[] args) throws Exception {
//		WebDriver driver = null;
//		Random ran = new Random();
//		int random = ran.nextInt(500);
//		
//		FileInputStream file = new FileInputStream("./src/test/resources/CommonData.properties");
//		Properties pObj = new Properties();
//		pObj.load(file);
//		
//		String browser = pObj.getProperty("browser");
//		String url = pObj.getProperty("url");
//		String username = pObj.getProperty("username");
//		String password = pObj.getProperty("password");
//		
//		if(browser.equalsIgnoreCase("chrome")) {
//			driver=new ChromeDriver();
//			driver.manage().window().maximize();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		}
//		driver.get(url);
////		Login to application
//		driver.findElement(By.name("user_name")).sendKeys(username);
//		driver.findElement(By.name("user_password")).sendKeys(password);
//		driver.findElement(By.id("submitButton")).click();
		

		//Creating the Objects of Generic utils
		
		FileUtils utils = new FileUtils();
		JavaUtils random = new JavaUtils();
		ExcelUtil excel = new ExcelUtil();
		WebDriver_Utils wd = new WebDriver_Utils();
		WebDriver driver= null;
		
		//Calling the methods
		String browser = utils.readDataFromProperties("browser");
		String url=utils.readDataFromProperties("url");
		String username=utils.readDataFromProperties("username");
		String password=utils.readDataFromProperties("password");
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
//			driver.manage().window().maximize();
			wd.maximizeWindow(driver);
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			wd.waitForPageLoad(driver, 15);
		}
		driver.get(url);
//		Login to application
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		
//		Navigate to Home Page Click on Contacts
		
		driver.findElement(By.linkText("Contacts")).click();

		
//		Click on Create Contact lookup image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
//		Enter 'Last Name' text field
		
//		FileInputStream fExcel = new FileInputStream("./src/test/resources/TestData.xlsx");
//		Workbook wb = WorkbookFactory.create(fExcel);
//		Sheet sh = wb.getSheet("Contacts");
		
		String lastname=excel.readDataFromExcel("Contacts", 0, 1)+"_"+random.getRandom();
		
//		String cellValue =sh.getRow(0).getCell(1).getStringCellValue();
//		String lastname = cellValue+random;
		System.out.println("lastname = "+lastname);
		
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
//		click on Save 
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String actual = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		System.out.println("actual = "+actual);
		if(actual.contains(lastname)) {
			System.out.println("Contact has been crated");
		}
		else {
			System.out.println("Contacts has not been created ");
		}
		//sign out
		//click on Administrator icon
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		
		//mousehover on the signout link and click on that
		WebElement signout = driver.findElement(By.linkText("Sign Out"));
		
//		Actions act = new Actions(driver);
//		act.moveToElement(signout).click().perform();
		wd.mouseHoverAndClickOnEle(driver, signout);
		
		driver.quit();
	}

}
