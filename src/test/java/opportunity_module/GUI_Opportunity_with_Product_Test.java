package opportunity_module;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import ObjectRepo.Creating_New_Opportunity_Page;
import ObjectRepo.Creating_New_Product_Page;
import ObjectRepo.HomePage;
import ObjectRepo.LoginPage;
import ObjectRepo.OpportunitiesPage;
import ObjectRepo.Opportunity_Information_Page;
import ObjectRepo.Opportunity_More_Information_Page;
import ObjectRepo.Organization_Verification_Msg_Page;
import ObjectRepo.Product_verification_Msg_Page;
import ObjectRepo.Products_Page;
import generic_libraries.ExcelUtil;
import generic_libraries.FileUtils;
import generic_libraries.JavaUtils;
import generic_libraries.WebDriver_Utils;

public class GUI_Opportunity_with_Product_Test {
	
	//NEED TO CHECK IT IS NOT COMPLETED
	public static void main(String[] args) throws IOException {
		// Creating the objects of Util's
				FileUtils file = new FileUtils();
				WebDriver_Utils wd = new WebDriver_Utils();
				ExcelUtil excel = new  ExcelUtil();
				JavaUtils random = new JavaUtils();
				
				WebDriver driver = null;
				
				String browser = file.readDataFromProperties("browser");
				String url = file.readDataFromProperties("url");
				String username = file.readDataFromProperties("username");
				String password = file.readDataFromProperties("password");
				
				if(browser.equalsIgnoreCase("chrome")) {
					driver = new ChromeDriver();
					wd.maximizeWindow(driver);
					wd.waitForPageLoad(driver, 10);
					driver.get(url);
				}
				
				//Creating Objects of the POM Class
				LoginPage lp = new LoginPage(driver);
				HomePage hp = new HomePage(driver);
				Products_Page pp = new Products_Page(driver);
				Creating_New_Product_Page cpp= new Creating_New_Product_Page(driver);
				Product_verification_Msg_Page pvm = new Product_verification_Msg_Page(driver);
				OpportunitiesPage op = new OpportunitiesPage(driver);
				Creating_New_Opportunity_Page cop = new Creating_New_Opportunity_Page(driver);
				Organization_Verification_Msg_Page ovm = new Organization_Verification_Msg_Page(driver);
				Opportunity_Information_Page oIP = new Opportunity_Information_Page(driver);
				Opportunity_More_Information_Page oMIP = new Opportunity_More_Information_Page(driver);
				
				//Login
				lp.login(username, password);
				
//				Navigate to Home Page Click on 'Products'
				hp.clickOnProductsLinks();
				
//				3. Click Create Product
				pp.clickonProductLookUp();
//				
//				4. Enter Product Name & Unit Price
				String productName =excel.readDataFromExcel("Products", 1, 1)+"_"+random.getRandom();
				String amount = excel.readDataFromExcel("Products", 2, 1);
//				
				System.out.println("productName = "+productName);
				System.out.println("amount = "+amount);
				
				cpp.createProduct(productName, amount);
				
				//verifying the product is created or not 
				pvm.verifyProductCreated(productName);
				
//				====================================================================================
//				6. Go to Opportunities
				hp.clickOnOpportunitiesLink();
				
//				7. Create Opportunity
				op.clickOnOppLookUp();
				
//				8. Enter Opportunity Name, Amount
				String oppName = excel.readDataFromExcel("Opportunities", 0, 1)+"_"+random.getRandom();
				String orgName = excel.readDataFromExcel("Opportunities", 1, 1);
				System.out.println("orgName = "+orgName);
				System.out.println("oppName = "+oppName);
				
				cop.createOpportunity(driver, oppName, amount, orgName);
				
				
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
				
//	===============================================================================================================
				//		10. Click More → Add Product
				
				oIP.MoreInfoClickOnProuct(driver);
				
//				11. Select the created product	
				String actualProd = oMIP.selctProduct(driver, productName);
				
//				Verify that product is added
//				ovm.verifyOrgCreated(actualProd, productName);
				
				if(actualProd.contains(productName)) {
					System.out.println("Opportunity should show the linked product with correct price");
				}
				else {
					System.out.println("Not showing");
				}
				
				//SignOut
				hp.signOut(wd, driver);
				driver.quit();
//				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				

	}

}
