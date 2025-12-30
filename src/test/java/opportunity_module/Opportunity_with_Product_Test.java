package opportunity_module;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generic_libraries.ExcelUtil;
import generic_libraries.FileUtils;
import generic_libraries.JavaUtils;
import generic_libraries.WebDriver_Utils;

//Test Case 11
public class Opportunity_with_Product_Test {

	public static void main(String[] args) throws Exception{
//		FileInputStream file = new FileInputStream("./src/test/resources/CommonData.properties");
//		Properties pObj = new Properties();
//		pObj.load(file);
		
//		Creating the Objects of Util's
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

		//		2. Go to Products

		//		Navigate to Home Page Click on 'Products'
		driver.findElement(By.linkText("Products")).click();

		//		3. Click Create Product
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		
		//		4. Enter Product Name & Unit Price
//		FileInputStream fExcel = new FileInputStream("./src/test/resources/TestData.xlsx");
//		Workbook wb = WorkbookFactory.create(fExcel);
//		Sheet sh = wb.getSheet("Products");
//		
//		Random ran = new Random();
//		int random = ran.nextInt(500);
//		
//		String name=sh.getRow(1).getCell(1).getStringCellValue();
//		String productName = name+"_"+random;
//		
//		String amt = sh.getRow(2).getCell(1).getStringCellValue();
//		String amount =amt; 
		
		String productName =excel.readDataFromExcel("Products", 1, 1)+"_"+random.getRandom();
		String amount = excel.readDataFromExcel("Products", 2, 1);
		
		System.out.println("productName = "+productName);
		System.out.println("amount = "+amount);

		driver.findElement(By.name("productname")).sendKeys(productName);
		driver.findElement(By.name("unit_price")).clear();
		driver.findElement(By.id("unit_price")).sendKeys(amount);

		//		5. Save
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
//=============================================================================================================================
		
		//		6. Go to Opportunities
		driver.findElement(By.linkText("Opportunities")).click();
		
		//		7. Create Opportunity
		driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();
		
//		FileInputStream fExcel1 = new FileInputStream("./src/test/resources/TestData.xlsx");
//		Workbook wb1 = WorkbookFactory.create(fExcel1);
//		Sheet sh1 = wb1.getSheet("Opportunities");
//		
//		//		8. Enter Opportunity Name, Amount
//		String opp=sh1.getRow(0).getCell(1).getStringCellValue();
//		String oppName = opp+"_"+random;
		String oppName = excel.readDataFromExcel("Opportunities", 0, 1)+"_"+random.getRandom();
		
		
		driver.findElement(By.name("potentialname")).sendKeys(oppName);
		driver.findElement(By.name("amount")).clear();
		driver.findElement(By.name("amount")).sendKeys(amount);
		
		String homePage = driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@id='related_to_display']/following-sibling::img[@title='Select']")).click();
		
//		Set<String> all1 = driver.getWindowHandles();
//		for(String win : all1) {
//			driver.switchTo().window(win);
//			String currentWinow = driver.getWindowHandle();
//			if(!(currentWinow.equals(homePage))){
//				break;
//			}
//		}
		wd.switchToWindow(driver, "Accounts&action");
//		String orgName=sh1.getRow(1).getCell(1).getStringCellValue();
		String orgName = excel.readDataFromExcel("Opportunities", 1, 1);
		System.out.println("orgName = "+orgName);
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		driver.switchTo().window(homePage);
		
		//		9. Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verifying the product is created or not 
				String actualoppName=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				String exptedoppName = oppName;
				System.out.println("oppName = "+oppName);

				if(actualoppName.contains(exptedoppName)) {
					System.out.println("Opportunity has been created ");
				}
				else
				{
					System.out.println("Opportunity is not created");
				}
	
//====================================================================================================================================
				
		//		10. Click More → Add Product
		WebElement more = driver.findElement(By.linkText("More Information"));
//		Actions act = new Actions(driver);
//		act.moveToElement(more).perform();
		wd.mouseHover(driver, more);
		
		driver.findElement(By.xpath("//a[@class='drop_down' and text()='Products']")).click();
		
		//		11. Select the created product
		String homePage1 = driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@title='Select Products']")).click();
		
//		Set<String> all = driver.getWindowHandles();
//		for(String win : all) {
//			driver.switchTo().window(win);
//			String currentWinow = driver.getWindowHandle();
//			if(!(currentWinow.equals(homePage1))){
//				break;
//			}
//		}
		wd.switchToWindow(driver, "Products&return");
		driver.findElement(By.id("search_txt")).sendKeys(productName,Keys.ENTER);
		
		driver.findElement(By.xpath("//a[text()='"+productName+"']")).click();
		
		// --- After selecting product from popup ---
		driver.switchTo().window(homePage1);

		// Open Products tab again
		String expPod = productName;
		String actualProd = driver.findElement(By.xpath("//a[text()='"+productName+"']")).getText();
//		String actualProd =driver.findElement(By.xpath("//a[@href=\"index.php?action=DetailView&module=Products&record=55&parenttab=Sales\"]")).getText();
		System.out.println("expPod = "+expPod);
		System.out.println("actualProd = "+actualProd);
	
		
		if(actualProd.contains(expPod)) {
			System.out.println("Opportunity should show the linked product with correct price");
		}
		else {
			System.out.println("Not showing");
		}
		
		//SignOut
				driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
				WebElement signout = driver.findElement(By.linkText("Sign Out"));
				
				//Actions act = new Actions(driver);
//				act.moveToElement(signout).click().perform();
				wd.mouseHoverAndClickOnEle(driver, signout);
				
				driver.quit();

	}

}
