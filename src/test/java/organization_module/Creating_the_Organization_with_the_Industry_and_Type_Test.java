package organization_module;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import generic_libraries.ExcelUtil;
import generic_libraries.FileUtils;
import generic_libraries.JavaUtils;

//Test case 2
public class Creating_the_Organization_with_the_Industry_and_Type_Test {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		//Creating the Objects of Generic utils
		
		FileUtils utils = new FileUtils();
		JavaUtils random = new JavaUtils();
		ExcelUtil excel = new ExcelUtil();
		
		WebDriver driver= null;
		
		//Calling the methods
		String browser = utils.readDataFromProperties("browser");
		String url=utils.readDataFromProperties("url");
		String username=utils.readDataFromProperties("username");
		String password=utils.readDataFromProperties("password");
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		}
		driver.get(url);
//		Login to application
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
//		Click on 'ORGANIZATIONS' link
		driver.findElement(By.linkText("Organizations")).click();
		
//		Click on 'ORGANIZATIONS'  lookup image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
//		FileInputStream fexcle = new FileInputStream("./src/test/resources/TestData.xlsx");
//		Workbook wb = WorkbookFactory.create(fexcle);
//		Sheet sh = wb.getSheet("Organizations");
//		
//		String cellVaue = sh.getRow(0).getCell(1).getStringCellValue();
//		System.out.println("cellVaue = "+cellVaue);
//		String orgName = cellVaue+random;
//		System.out.println("orgName = "+orgName);
		
		// Reading data from excel
				String orgName=excel.readDataFromExcel("Organizations", 0, 1)+"_"+random.getRandom();
				System.out.println("orgName = "+orgName);
		
//		Enter mandatory fields with valid data
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		//Enter Industry 
		WebElement industry = driver.findElement(By.name("industry"));
		Select sel = new Select(industry);
		
		String industryName=excel.readDataFromExcel("Organizations", 1, 1);
//		String industryName=sh.getRow(1).getCell(1).getStringCellValue();
		System.out.println("industryName = "+industryName);
		sel.selectByContainsVisibleText(industryName);
		
		//Enter Type
		WebElement type = driver.findElement(By.name("accounttype"));
		Select sel2 = new Select(type);
		String typeName=excel.readDataFromExcel("Organizations", 2, 1);
//		String typeName=sh.getRow(2).getCell(1).getStringCellValue();
		System.out.println("typeName = "+typeName);
		sel2.selectByContainsVisibleText(typeName);
		
//		click on 'Save' button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String actual = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(actual.contains(orgName)) {
			System.out.println("Organization created Successfully");
		}
		else {
			System.out.println("Organization is not created");
		}
		//sign out
		//click on Administrator icon
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		
		//mousehover on the signout link and click on that
		WebElement signout = driver.findElement(By.linkText("Sign Out"));
		
		Actions act = new Actions(driver);
		act.moveToElement(signout).click().perform();
		
		driver.quit();
	}

}
