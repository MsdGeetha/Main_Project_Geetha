package contacts_module;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ObjectRepo.Contact_Verification_Msg_Page;
import ObjectRepo.ContactsPage;
import ObjectRepo.Creating_New_Contact_Page;
import ObjectRepo.Creating_New_Organization_Page;
import ObjectRepo.HomePage;
import ObjectRepo.LoginPage;
import ObjectRepo.OrganizationPage;
import ObjectRepo.Organization_Verification_Msg_Page;
import generic_libraries.BaseClass;

@Listeners(generic_libraries.ListenerImplementationClass.class)
public class TestNG_Creating_Contacts_Test extends BaseClass {
	
	String orgName = null;
	@Test(priority = -1, groups = "Contacts",retryAnalyzer = generic_libraries.RetryImplementationClass.class)
	public void createOrganization() throws EncryptedDocumentException, IOException {
		//Creating the Objects of the POM Class
		HomePage hp = new HomePage(driver);
		OrganizationPage op = new OrganizationPage(driver);
		Creating_New_Organization_Page cop = new Creating_New_Organization_Page(driver);
		Organization_Verification_Msg_Page oppVerify = new Organization_Verification_Msg_Page(driver);
		
		//Click on Organization
				hp.clickOnOrganizationsLink();
				
				//Click on on create Org link
				op.clickonOrganizationLookUp();
				
				// Reading data from excel
				orgName=eLib.readDataFromExcel("Organizations", 0, 1)+"_"+jLib.getRandom();
				System.out.println("orgName = "+orgName);
				
				//Create org page 
				cop.createOrganization(orgName);
				
				//Verify org is create or not 
				oppVerify.verifyOrgCreated(orgName);
				
//				return orgName;
				System.out.println("======================Creating_Organization_Complete 2======================");

	}
	
	@Test(groups = "Contacts")
	public void createContacts() throws EncryptedDocumentException, IOException {
		//Creating the Objects of the POM Class
		HomePage hp = new HomePage(driver);
		ContactsPage cp = new ContactsPage(driver);
		Creating_New_Contact_Page ccp = new Creating_New_Contact_Page(driver);
		Contact_Verification_Msg_Page cvm = new Contact_Verification_Msg_Page(driver);

		//		Navigate to Home Page Click on Contacts
		hp.clickOnContactsLink();
		
//		Click on Create Contact lookup image
		cp.clickonContactLookUp();
		
		//Getting it from the excel sheet
		String lastname=eLib.readDataFromExcel("Contacts", 0, 1)+"_"+jLib.getRandom();
		System.out.println("lastname = "+lastname);
		
//		Enter 'Last Name' text field
		System.out.println("orgName = "+orgName);
		ccp.createContact(lastname, driver, orgName);
		
//    Verifying the Contact is created or not 
		cvm.verifyContactCreated(lastname);
		
		System.out.println("======================Creating_Contacts_Complete 2======================");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
