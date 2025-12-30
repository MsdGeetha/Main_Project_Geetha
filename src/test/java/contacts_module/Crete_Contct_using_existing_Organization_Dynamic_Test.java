package contacts_module;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import generic_libraries.ExcelUtil;
import generic_libraries.FileUtils;
import generic_libraries.JavaUtils;
import generic_libraries.WebDriver_Utils;

//Test Case 5
public class Crete_Contct_using_existing_Organization_Dynamic_Test {

	public static void main(String[] args) throws Exception {
		
		//Creating the Objects of Generic utils
		
				FileUtils utils = new FileUtils();
				JavaUtils random = new JavaUtils();
				ExcelUtil excel = new ExcelUtil();
				WebDriver_Utils webDriver = new WebDriver_Utils();
				
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
//				Login to application
				driver.findElement(By.name("user_name")).sendKeys(username);
				driver.findElement(By.name("user_password")).sendKeys(password);
				driver.findElement(By.id("submitButton")).click();
				
				
				// Reading data from excel
				String orgName=excel.readDataFromExcel("Organizations", 0, 1)+"_"+random.getRandom();
				System.out.println("orgName = "+orgName);

//		Click on 'ORGANIZATIONS' link
		driver.findElement(By.linkText("Organizations")).click();
		
//		Click on 'ORGANIZATIONS'  lookup image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
//		Enter mandatory fields with valid data
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
//		click on 'Save' button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String exp = orgName;
	String actual = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(actual.contains(exp)) {
			System.out.println("Organization created Successfully");
		}
		else {
			System.out.println("Organization is not created");
		}
		
//=========================================================================================================================
///		Navigate to Home Page Click on Contacts
		
		driver.findElement(By.linkText("Contacts")).click();

		
//		Click on Create Contact lookup image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
//		Enter 'Last Name' text field
		String lastname=excel.readDataFromExcel("Contacts", 0, 1)+"_"+random.getRandom();
		System.out.println("lastname = "+lastname);
		
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
//		Click on 'Organization Name' lookup image
		String homePage = driver.getWindowHandle();
//		System.out.println("Getting the parent window Id");
		driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img[@title='Select']")).click();
		
//		Set<String> allWindows = driver.getWindowHandles();
//		
//		Iterator<String> it = allWindows.iterator();
////		System.out.println("switching to next window");
//		while(it.hasNext()) {
//			String wid = it.next();
//			@Nullable
//			String currentTitle = driver.switchTo().window(wid).getTitle();
//			if(currentTitle.contains("Accounts&action")) {
//				break;
//			}
//		}
		webDriver.switchToWindow(driver,"Accounts&action" );
//		Click on 'Organization Name'
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();

		driver.switchTo().window(homePage);
//		webDriver.switchToWindow(driver, "Contacts&action");
		System.out.println("Switching back to home page");
		
//		Click on save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		System.out.println("lastname = "+lastname);
		
		String expectedContact = lastname;
		//Valid the Contact
		String actualContact = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(actualContact.contains(expectedContact)) {
			System.out.println("Contact Has been created Succefully");
		}
		else {
			System.out.println("Contacts Has not been created ");
		}
		//Sign out
		//click on Administrator icon
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		
		//mouse hover on the sign out link and click on that
		WebElement signout = driver.findElement(By.linkText("Sign Out"));
		
//		Actions act = new Actions(driver);
//		act.moveToElement(signout).click().perform();
		webDriver.mouseHoverAndClickOnEle(driver, signout);
		
		driver.quit();
		
	}

}
