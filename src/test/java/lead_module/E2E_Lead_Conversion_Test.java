package lead_module;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import generic_libraries.ExcelUtil;
import generic_libraries.FileUtils;
import generic_libraries.JavaUtils;
import generic_libraries.WebDriver_Utils;

//Test Case 10
public class E2E_Lead_Conversion_Test {

	public static void main(String[] args) throws Exception{
//		FileInputStream file = new FileInputStream("./src/test/resources/CommonData.properties");
//		Properties pObj = new Properties();
//		pObj.load(file);
		
//		creating objects of Util's Class
		FileUtils file = new FileUtils();
		ExcelUtil excel = new ExcelUtil();
		JavaUtils random = new JavaUtils();
		WebDriver_Utils wd = new WebDriver_Utils();
		
		String browser=file.readDataFromProperties("browser");
		String url=file.readDataFromProperties("url");
		String username=file.readDataFromProperties("username");
		String password=file.readDataFromProperties("password");
		
		WebDriver driver = null;
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
//			driver.manage().window().maximize();
			wd.maximizeWindow(driver);
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			wd.waitForPageLoad(driver, 15);
		}
		driver.get(url);
//		Login to application
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
//		2. Go to Leads
		driver.findElement(By.linkText("Leads")).click();
		
//		Click on 'Leads'  lookup image
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		
//		Enter mandatory fields with valid data
//		FileInputStream fExcel = new FileInputStream("./src/test/resources/TestData.xlsx");
//		Workbook wb = WorkbookFactory.create(fExcel);
//		Sheet sh = wb.getSheet("Leads");
//		
//		Random ran = new Random();
//		int random = ran.nextInt(500);
//		
//		String last=sh.getRow(3).getCell(1).getStringCellValue();
//		String lastName = last+"_"+random;
//		
//		String company = sh.getRow(4).getCell(1).getStringCellValue();
//		String companyName = company+"_"+random;
//		
//		String email = sh.getRow(5).getCell(1).getStringCellValue();
//      String email = "test@abc.com";

		String lastName = excel.readDataFromExcel("Leads", 3, 1)+"_"+random.getRandom();
		String companyName=excel.readDataFromExcel("Leads", 4, 1)+"_"+random.getRandom();
		String email = excel.readDataFromExcel("Leads", 5, 1);
		
		System.out.println("lastName = "+lastName);
		System.out.println("companyName = "+companyName);
		System.out.println("email = "+email);
		
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("company")).sendKeys(companyName);
		driver.findElement(By.id("email")).sendKeys(email);
		
//		click on 'Save' button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String exp = lastName;
		String actual=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		
		if(actual.contains(exp)) {
			System.out.println("Lead is created");
		}
		else {
			System.out.println("Lead is not created");
		}
// ======================================================================================================================================================		
//		String homePage = driver.getWindowHandle();
		driver.findElement(By.linkText("Convert Lead")).click();
		
//		Set<String> allTabs = driver.getWindowHandles();
//		
//		for(String eachWindow : allTabs) {
//			driver.switchTo().window(eachWindow);
//			String currentWinow = driver.getWindowHandle();
//			if(!(currentWinow.equals(homePage))) {
//				break;
//			}
//		}
		driver.findElement(By.name("Save")).click();
		
//		driver.switchTo().window(homePage);
		
		driver.findElement(By.linkText("Contacts")).click();
		
		WebElement element = driver.findElement(By.id("bas_searchfield"));
		wd.dropdownUsingContainsVisibleText(element, "Last Name");
		
		driver.findElement(By.name("search_text")).sendKeys(lastName);
		driver.findElement(By.name("submit")).click();
		
		String expectedContact = lastName; 
		String acutualContact = driver.findElement(By.xpath("//a[text()='"+lastName+"']")).getText();
		
		if(acutualContact.contains(expectedContact)) {
			System.out.println("Lead should be converted to contact");
		}
		else {
			System.out.println("Lead not converted to contact");
		}
//		=====================================================================================================================================
		
		driver.findElement(By.linkText("Organizations")).click();
		String expcted = companyName;
		
		WebElement ele = driver.findElement(By.id("bas_searchfield"));
//		Select sel = new Select(ele);
//		sel.selectByVisibleText("Organization Name");
		wd.dropdownUsingContainsVisibleText(ele, "Organization Name");
		
		driver.findElement(By.name("search_text")).sendKeys(companyName,Keys.ENTER);
		
		 WebElement element1 =driver.findElement(By.xpath("//a[text()='"+companyName+"' and @title='Organizations']"));
		 wd.waitUntillEleToBeVisible(driver, 30, element1);
		
		String actualOrg = driver.findElement(By.xpath("//a[text()='"+companyName+"' and @title='Organizations']")).getText();
		
		if(actualOrg.contains(expcted)) {
			System.out.println("org should appear");
		}
		else {
			System.out.println("Org is not appered");
		}
		
//		sigout
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		WebElement signout = driver.findElement(By.linkText("Sign Out"));
				
//		Actions act = new Actions(driver);
//		act.moveToElement(signout).click().perform();
		wd.mouseHoverAndClickOnEle(driver, signout);
				
		driver.quit();
	}

}
