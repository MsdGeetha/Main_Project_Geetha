package organization_module;

import java.io.FileInputStream;
import java.io.IOException;
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

public class CreateOrgWithMandatoryFields_DDT_Test {

	public static void main(String[] args) throws IOException {
		
		WebDriver driver = null;
		Random ran = new Random();
		int random = ran.nextInt(500);
		
		FileInputStream file = new FileInputStream("./src/test/resources/CommonData.properties");
		Properties pObj = new Properties();
		pObj.load(file);
		
		String browser = pObj.getProperty("browser");
		String url = pObj.getProperty("url");
		String username = pObj.getProperty("username");
		String password = pObj.getProperty("password");
		
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
		
		// Reading data from excel
		FileInputStream fileExcel = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fileExcel);
		Sheet sh = wb.getSheet("Organizations");
		
		String orgName =sh.getRow(0).getCell(1).getStringCellValue()+"_"+random;
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
