package campaign_module;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ObjectRepo.Campaign_Verification_Msg_Page;
import ObjectRepo.CampaignsPage;
import ObjectRepo.Creating_New_Campaign_Page;
import ObjectRepo.HomePage;
import ObjectRepo.LoginPage;
import generic_libraries.BaseClass;

@Listeners(generic_libraries.ListenerImplementationClass.class)
public class TestNG_Creating_Campaigns_Test extends BaseClass {

	@Test(groups = "Campaigns",retryAnalyzer = generic_libraries.RetryImplementationClass.class)
	public void create_Campaigns() throws EncryptedDocumentException, IOException {
		//Creating objects of POM Class
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		CampaignsPage cp = new CampaignsPage(driver);
		Creating_New_Campaign_Page ccp = new Creating_New_Campaign_Page(driver);
		Campaign_Verification_Msg_Page cvm = new Campaign_Verification_Msg_Page(driver);
		
//		Navigate to Home Page Click on 'More'
//		driver.findElement(By.linkText("More")).click();
		hp.clickOnMoreLink();
		
//		Marketing under that Click on 'Campaign'
//		driver.findElement(By.name("Campaigns")).click();
		hp.clickOnCampaignsLink();
		
//		Click on 'Campaign'  lookup image
//		driver.findElement(By.xpath("//img[@title=\"Create Campaign...\"]")).click();
		cp.clickonCampaignLookUp();
		
		
//		Enter mandatory fields with valid data
		String campaignname = eLib.readDataFromExcel("Campaign", 0, 1)+"_"+jLib.getRandom();
		System.out.println("campaignname = "+campaignname);
		
//		Assert.fail();
		ccp.createCampaign(campaignname);
		
		//Verification
		cvm.verifyCampaigCreated(campaignname);
		
		System.out.println("======================Creating_Campaigns_Complete 4======================");
	}
}
