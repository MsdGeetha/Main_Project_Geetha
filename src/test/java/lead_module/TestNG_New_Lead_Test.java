package lead_module;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ObjectRepo.Creating_New_Lead_Page;
import ObjectRepo.HomePage;
import ObjectRepo.Lead_Verification_Msg_Page;
import ObjectRepo.LeadsPage;
import ObjectRepo.LoginPage;
import generic_libraries.BaseClass;

@Listeners(generic_libraries.ListenerImplementationClass.class)
public class TestNG_New_Lead_Test extends BaseClass{
	@Test(groups = "Leads",retryAnalyzer = generic_libraries.RetryImplementationClass.class)
	public void creatingLead() throws EncryptedDocumentException, IOException {
		//Creating the objects of POM Class
				HomePage hp = new HomePage(driver);
				LeadsPage lead = new LeadsPage(driver);
				Creating_New_Lead_Page clp = new Creating_New_Lead_Page(driver);
				Lead_Verification_Msg_Page lvp = new Lead_Verification_Msg_Page(driver);
				
				//Click on leads
				hp.clickOnLeadsLink();
				
//				Click on 'Leads'  lookup image
				lead.clickoncreateLeadLookUp();
				
//				Enter mandatory fields with valid data
				String lastName=eLib.readDataFromExcel("Leads", 0, 1)+"_"+jLib.getRandom();
				String companyName=eLib.readDataFromExcel("Leads", 1, 1)+"_"+jLib.getRandom();
				
//				Assert.fail();
				clp.createLead(lastName, companyName);
				
				//Verification 
				lvp.verifyLeadCreated(lastName);
				
				System.out.println("======================creatingLead_Complete 5 ======================");
	}
}
