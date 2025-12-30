package opportunity_module;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
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
import generic_libraries.BaseClass;

@Listeners(generic_libraries.ListenerImplementationClass.class)
public class TestNG_Opportunity_with_Product_Test extends BaseClass {

	@Test(groups = "Opportunity",retryAnalyzer = generic_libraries.RetryImplementationClass.class)
	public void opportunity_with_Product() throws EncryptedDocumentException, IOException {
		//Creating Objects of the POM Class
		HomePage hp = new HomePage(driver);
		Products_Page pp = new Products_Page(driver);
		Creating_New_Product_Page cpp= new Creating_New_Product_Page(driver);
		Product_verification_Msg_Page pvm = new Product_verification_Msg_Page(driver);
		OpportunitiesPage op = new OpportunitiesPage(driver);
		Creating_New_Opportunity_Page cop = new Creating_New_Opportunity_Page(driver);
		Organization_Verification_Msg_Page ovm = new Organization_Verification_Msg_Page(driver);
		Opportunity_Information_Page oIP = new Opportunity_Information_Page(driver);
		Opportunity_More_Information_Page oMIP = new Opportunity_More_Information_Page(driver);
		

//		Navigate to Home Page Click on 'Products'
		hp.clickOnProductsLinks();
		
//		3. Click Create Product
		pp.clickonProductLookUp();
		
//		4. Enter Product Name & Unit Price
		String productName =eLib.readDataFromExcel("Products", 1, 1)+"_"+jLib.getRandom();
		String amount = eLib.readDataFromExcel("Products", 2, 1);
		
		System.out.println("productName = "+productName);
		System.out.println("amount = "+amount);
		
		cpp.createProduct(productName, amount);
		
		//verifying the product is created or not 
		pvm.verifyProductCreated(productName);
		
//		====================================================================================
//		6. Go to Opportunities
		hp.clickOnOpportunitiesLink();
		
//		7. Create Opportunity
		op.clickOnOppLookUp();
		
//		8. Enter Opportunity Name, Amount
		String oppName = eLib.readDataFromExcel("Opportunities", 0, 1)+"_"+jLib.getRandom();
		String orgName = eLib.readDataFromExcel("Opportunities", 1, 1);
		System.out.println("orgName = "+orgName);
		System.out.println("oppName = "+oppName);
		
		cop.createOpportunity(driver, oppName, amount, orgName);
		
		
		//verifying the product is created or not 
		String actualoppName=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String exptedoppName = oppName;
		System.out.println("oppName = "+oppName);
		
		Assert.assertTrue(actualoppName.contains(exptedoppName), "Opportunity is not created");
		System.out.println("Opportunity has been created ");

//		if(actualoppName.contains(exptedoppName)) {
//			System.out.println("Opportunity has been created ");
//		}
//		else
//		{
//			System.out.println("Opportunity is not created");
//		}
		
//===============================================================================================================
		//		10. Click More → Add Product
		
		oIP.MoreInfoClickOnProuct(driver);
		
//		11. Select the created product	
		String actualProd = oMIP.selctProduct(driver, productName);
		
//		Verify that product is added
		ovm.verifyOrgCreated(actualProd, productName);
		
//		Assert.assertTrue(actualProd.contains(productName), "Not showing");
//		System.out.println("Opportunity should show the linked product with correct price ");
		
//		if(actualProd.contains(productName)) {
//			System.out.println("Opportunity should show the linked product with correct price");
//		}
//		else {
//			System.out.println("Not showing");
//		}
		
		System.out.println("======================Opportunity_with_Product_Complete 8 ======================");
	}
}
