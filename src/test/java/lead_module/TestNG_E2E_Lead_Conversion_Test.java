package lead_module;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ObjectRepo.ContactsPage;
import ObjectRepo.Creating_New_Lead_Page;
import ObjectRepo.HomePage;
import ObjectRepo.Lead_Verification_Msg_Page;
import ObjectRepo.LeadsPage;
import ObjectRepo.LoginPage;
import ObjectRepo.OrganizationPage;
import ObjectRepo.Organization_Verification_Msg_Page;
import generic_libraries.BaseClass;

@Listeners(generic_libraries.ListenerImplementationClass.class)
public class TestNG_E2E_Lead_Conversion_Test extends BaseClass {
	@Test(groups = "Leads",retryAnalyzer = generic_libraries.RetryImplementationClass.class)
	public void E2E_Lead_Conversion() throws EncryptedDocumentException, IOException, InterruptedException {
		
		//Creating Objects of the POM Class
				LoginPage lp = new LoginPage(driver);
				HomePage hp = new HomePage(driver);
				LeadsPage lead = new LeadsPage(driver);
				Creating_New_Lead_Page clp = new Creating_New_Lead_Page(driver);
				Lead_Verification_Msg_Page lvm = new Lead_Verification_Msg_Page(driver);
				ContactsPage cp = new ContactsPage(driver);
				OrganizationPage op = new OrganizationPage(driver);
				Organization_Verification_Msg_Page ovp = new Organization_Verification_Msg_Page(driver);
				
//				2. Go to Leads
				hp.clickOnLeadsLink();
				
//				Click on 'Leads'  lookup image
				lead.clickoncreateLeadLookUp();
				
//				Enter mandatory fields with valid data
				String lastName = eLib.readDataFromExcel("Leads", 3, 1)+"_"+jLib.getRandom();
				String companyName=eLib.readDataFromExcel("Leads", 4, 1)+"_"+jLib.getRandom();
				String email = eLib.readDataFromExcel("Leads", 5, 1);
				
				System.out.println("lastName = "+lastName);
				System.out.println("companyName = "+companyName);
				System.out.println("email = "+email);
				
				clp.createLead(lastName, companyName, email);
				
				lvm.verifyLeadCreated(lastName);
				
				//Covert to lead
				lead.convertToLead();
				
				Thread.sleep(5000);
				//Switch To Contacts
				hp.clickOnContactsLink();
				
				//Searching for the Contact last name
				String contact = cp.searchForContacts(driver, lastName);
				
				//Verify the Contact
				lvm.verifyLeadCreated(contact, lastName);
				
//				===========================================================================================
//						click on organization
						
						hp.clickOnOrganizationsLink();
						
						//Searching for that org
						String actualOrg=op.searchForOrgs(driver, companyName);
						
						Thread.sleep(4000);
						//Verifying whether it is coming or not
						ovp.verifyOrgCreated(actualOrg, companyName);
						
						System.out.println("======================E2E_Lead_Conversion_Complete 7 ======================");
						
				
	}
}
