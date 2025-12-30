package product_module;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ObjectRepo.Creating_New_Product_Page;
import ObjectRepo.HomePage;
import ObjectRepo.LoginPage;
import ObjectRepo.Product_verification_Msg_Page;
import ObjectRepo.Products_Page;
import generic_libraries.BaseClass;

@Listeners(generic_libraries.ListenerImplementationClass.class)
public class TestNG_Creating_New_Product_Test extends BaseClass 
{
	@Test(groups = "Products",retryAnalyzer = generic_libraries.RetryImplementationClass.class)
	public void Creating_New_Product() throws EncryptedDocumentException, IOException {
		//Create the objects of the POM Class
		HomePage hp = new HomePage(driver);
		Products_Page pp = new Products_Page(driver);
		Creating_New_Product_Page cp = new Creating_New_Product_Page(driver);
		Product_verification_Msg_Page pvm = new Product_verification_Msg_Page(driver);

//		Navigate to Home Page Click on 'Products'
		hp.clickOnProductsLinks();
		
		
//		Click on 'Products'  lookup image
		pp.clickonProductLookUp();
		
//		Enter mandatory fields with valid data
		String productName=eLib.readDataFromExcel("Products", 0, 1)+"_"+jLib.getRandom();
		
//		Assert.fail();
		cp.createProduct(productName);
		
		//verifying the product is created or not 
		pvm.verifyProductCreated(productName);
		
		System.out.println("======================Creating_New_Product_Complete 3 ======================");
	}

}
