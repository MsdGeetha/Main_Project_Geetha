package product_module;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import ObjectRepo.Creating_New_Product_Page;
import ObjectRepo.HomePage;
import ObjectRepo.LoginPage;
import ObjectRepo.Product_verification_Msg_Page;
import ObjectRepo.Products_Page;
import generic_libraries.BaseClass;
import generic_libraries.ExcelUtil;
import generic_libraries.FileUtils;
import generic_libraries.JavaUtils;
import generic_libraries.WebDriver_Utils;

public class GUI_Creating_New_Product_Test extends BaseClass {

	@Test
	public void creating_New_Product() throws IOException {
//		creating the objects of util's
//		WebDriver_Utils wd = new WebDriver_Utils();
//		FileUtils file = new FileUtils();
//		JavaUtils random = new JavaUtils();
//		ExcelUtil excel = new ExcelUtil();
//		
//		WebDriver driver = null;
//		
//		String browser = file.readDataFromProperties("browser");
//		String url = file.readDataFromProperties("url");
//		String username = file.readDataFromProperties("username");
//		String password = file.readDataFromProperties("password");
//		
//		if(browser.equalsIgnoreCase("chrome")) {
//			driver = new ChromeDriver();
//			wd.maximizeWindow(driver);
//			wd.waitForPageLoad(driver, 10);
//		}
//		driver.get(url);
		
		//Create the objects of the POM Class
//		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		Products_Page pp = new Products_Page(driver);
		Creating_New_Product_Page cp = new Creating_New_Product_Page(driver);
		Product_verification_Msg_Page pvm = new Product_verification_Msg_Page(driver);
		
		//login to application 
//		lp.login(username, password);
		
//		Navigate to Home Page Click on 'Products'
		hp.clickOnProductsLinks();
		
//		Click on 'Products'  lookup image
		pp.clickonProductLookUp();
		
//		Enter mandatory fields with valid data
		String productName = eLib.readDataFromExcel("Products", 0, 1)+"_"+jLib.getRandom();
		cp.createProduct(productName);
		
		//verifying the product is created or not 
		pvm.verifyProductCreated(productName);
		
//		//Sign out
//		hp.signOut(wd, driver);
//		
//		driver.quit();	
		
	}

}
