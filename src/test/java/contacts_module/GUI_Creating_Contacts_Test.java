package contacts_module;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import ObjectRepo.Contact_Verification_Msg_Page;
import ObjectRepo.ContactsPage;
import ObjectRepo.Creating_New_Contact_Page;
import ObjectRepo.Creating_New_Organization_Page;
import ObjectRepo.HomePage;
import ObjectRepo.LoginPage;
import ObjectRepo.OrganizationPage;
import ObjectRepo.Organization_Verification_Msg_Page;
import generic_libraries.ExcelUtil;
import generic_libraries.FileUtils;
import generic_libraries.JavaUtils;
import generic_libraries.WebDriver_Utils;

public class GUI_Creating_Contacts_Test {

	@Test
	public  void creating_Contacts() throws IOException {
		//Objects Of Util's
		FileUtils file = new FileUtils();
		ExcelUtil excel = new ExcelUtil();
		JavaUtils random = new JavaUtils();
		WebDriver_Utils wd = new WebDriver_Utils();
		
		//Getting the Data from file to login 
		String browser=file.readDataFromProperties("browser");
		String url = file.readDataFromProperties("url");
		String username=file.readDataFromProperties("username");
		String password=file.readDataFromProperties("password");
		
		WebDriver driver = null;
		//Lunch the Browser
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			wd.maximizeWindow(driver);
			wd.waitForPageLoad(driver, 10);
		}
		driver.get(url);
		
		//Creating the Objects of the POM Class
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		ContactsPage cp = new ContactsPage(driver);
		Creating_New_Contact_Page ccp = new Creating_New_Contact_Page(driver);
		Contact_Verification_Msg_Page cvm = new Contact_Verification_Msg_Page(driver);
		OrganizationPage op = new OrganizationPage(driver);
		Creating_New_Organization_Page cop = new Creating_New_Organization_Page(driver);
		Organization_Verification_Msg_Page oppVerify = new Organization_Verification_Msg_Page(driver);
		
		
		
		//Login to Application
		lp.login(username, password);
		
//=======================================================================================================================//
		//Creating Organization
		
		//Click on Organization
		hp.clickOnOrganizationsLink();
		
		//Click on on create Org link
		op.clickonOrganizationLookUp();
		
		// Reading data from excel
		String orgName=excel.readDataFromExcel("Organizations", 0, 1)+"_"+random.getRandom();
		System.out.println("orgName = "+orgName);
		
		//Create org page 
		cop.createOrganization(orgName);
		
		//Verify org is create or not 
		oppVerify.verifyOrgCreated(orgName);
		
//=======================================================================================================================//
			
//		Navigate to Home Page Click on Contacts
		hp.clickOnContactsLink();
		
//		Click on Create Contact lookup image
		cp.clickonContactLookUp();
		
		//Getting it from the excel sheet
		String lastname=excel.readDataFromExcel("Contacts", 0, 1)+"_"+random.getRandom();
		System.out.println("lastname = "+lastname);
		
//		Enter 'Last Name' text field
		ccp.createContact(lastname, driver, orgName);
		
//    Verifying the Contact is created or not 
		cvm.verifyContactCreated(lastname);
		
		//SignOut
		hp.signOut(wd, driver);
		
		driver.quit();		

	}

}
