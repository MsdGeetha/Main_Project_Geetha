package campaign_module;

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

//Test case 7
public class Creating_new_Campaign_Test {

	public static void main(String[] args) throws Exception{
		FileInputStream file = new FileInputStream("./src/test/resources/CommonData.properties");
		Properties pObj = new Properties();
		pObj.load(file);
		
		String browser=pObj.getProperty("browser");
		String url=pObj.getProperty("url");
		String username=pObj.getProperty("username");
		String password=pObj.getProperty("password");
		
		WebDriver driver = null;
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		}
		driver.get(url);
//		Login to application
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
//		Navigate to Home Page Click on 'More'
		driver.findElement(By.linkText("More")).click();
		
//		Marketing under that Click on 'Campaign'
		driver.findElement(By.name("Campaigns")).click();
		
//		Click on 'Campaign'  lookup image
		driver.findElement(By.xpath("//img[@title=\"Create Campaign...\"]")).click();
		
//		Enter mandatory fields with valid data
		FileInputStream fExcel = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fExcel);
		Sheet sh = wb.getSheet("Campaign");
		String cellValue=sh.getRow(0).getCell(1).getStringCellValue();
		
		Random ran = new Random();
		int random = ran.nextInt(500);
		
		String campaignname = cellValue+"_"+random;
		driver.findElement(By.name("campaignname")).sendKeys(campaignname);
		
//		click on 'Save' button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String exp = campaignname;
		String actual=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		
		if(actual.contains(exp)) {
			System.out.println("campaig nname is created");
		}
		else {
			System.out.println("campaign name is not created");
		}
		//sigout
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		WebElement signout = driver.findElement(By.linkText("Sign Out"));
		
		Actions act = new Actions(driver);
		act.moveToElement(signout).click().perform();
		
		driver.quit();


	}

}
