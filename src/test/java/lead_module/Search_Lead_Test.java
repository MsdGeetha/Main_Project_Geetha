package lead_module;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;import org.apache.xmlbeans.impl.xpath.XPath;
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

//Test Case 09
public class Search_Lead_Test {

	public static void main(String[] args) throws Exception{
//		FileInputStream file = new FileInputStream("./src/test/resources/CommonData.properties");
//		Properties pObj = new Properties();
//		pObj.load(file);
		
//		Create the Objects of the Util's
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
		
//		3. Enter name in search box
//		FileInputStream fExcel = new FileInputStream("./src/test/resources/TestData.xlsx");
//		Workbook wb = WorkbookFactory.create(fExcel);
//		Sheet sh = wb.getSheet("Leads");
//		
		WebElement web = driver.findElement(By.id("bas_searchfield"));
//		Select sel = new Select(web);
//		String in=sh.getRow(2).getCell(1).getStringCellValue();
//		sel.selectByVisibleText(in);
//		
//		String search=sh.getRow(0).getCell(1).getStringCellValue();
		
		String in = excel.readDataFromExcel("Leads",2 , 1);
		wd.dropdownUsingVisibleText(web, in);
		
		String search=excel.readDataFromExcel("Leads", 0, 1);
		
		driver.findElement(By.name("search_text")).sendKeys(search);
		
		//a[contains(text(),'l') and @title='Leads']
//		4. Press Enter
		driver.findElement(By.name("submit")).click();
		
	String result = driver.findElement(By.xpath("//td[contains(text(),'Showing Records')]")).getText();
	System.out.println(result);
		//sigout
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		WebElement signout = driver.findElement(By.linkText("Sign Out"));
				
//		Actions act = new Actions(driver);
//		act.moveToElement(signout).click().perform();
		wd.mouseHoverAndClickOnEle(driver, signout);
				
		driver.quit();


	}

}
