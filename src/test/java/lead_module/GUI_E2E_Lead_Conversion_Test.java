package lead_module;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import ObjectRepo.ContactsPage;
import ObjectRepo.Creating_New_Lead_Page;
import ObjectRepo.HomePage;
import ObjectRepo.Lead_Verification_Msg_Page;
import ObjectRepo.LeadsPage;
import ObjectRepo.LoginPage;
import ObjectRepo.OrganizationPage;
import ObjectRepo.Organization_Verification_Msg_Page;
import generic_libraries.ExcelUtil;
import generic_libraries.FileUtils;
import generic_libraries.JavaUtils;
import generic_libraries.WebDriver_Utils;

public class GUI_E2E_Lead_Conversion_Test {

	@Test
	public void E2E_Lead_Conversion() throws IOException, InterruptedException {
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
		}
		driver.get(url);
		//Creating Objects of the POM Class
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		LeadsPage lead = new LeadsPage(driver);
		Creating_New_Lead_Page clp = new Creating_New_Lead_Page(driver);
		Lead_Verification_Msg_Page lvm = new Lead_Verification_Msg_Page(driver);
		ContactsPage cp = new ContactsPage(driver);
		OrganizationPage op = new OrganizationPage(driver);
		Organization_Verification_Msg_Page ovp = new Organization_Verification_Msg_Page(driver);
		
		// login to application
		lp.login(username, password);
		
//		2. Go to Leads
		hp.clickOnLeadsLink();
		
//		Click on 'Leads'  lookup image
		lead.clickoncreateLeadLookUp();
		
//		Enter mandatory fields with valid data
		String lastName = excel.readDataFromExcel("Leads", 3, 1)+"_"+random.getRandom();
		String companyName=excel.readDataFromExcel("Leads", 4, 1)+"_"+random.getRandom();
		String email = excel.readDataFromExcel("Leads", 5, 1);
		
		System.out.println("lastName = "+lastName);
		System.out.println("companyName = "+companyName);
		System.out.println("email = "+email);
		
		clp.createLead(lastName, companyName, email);
		
		lvm.verifyLeadCreated(lastName);
		
		//Covert to lead
		lead.convertToLead();
		
//		
		//Switch To Contacts
		hp.clickOnContactsLink();
		
		//Searching for the Contact last name
		String contact = cp.searchForContacts(driver, lastName);
		
		//Verify the Contact
		lvm.verifyLeadCreated(contact, lastName);
		
//		===========================================================================================
//		click on organization
		
		hp.clickOnOrganizationsLink();
		
		//Searching for that org
		String actualOrg=op.searchForOrgs(driver, companyName);
		
		Thread.sleep(4000);
		//Verifying whether it is coming or not
		ovp.verifyOrgCreated(actualOrg, companyName);
		
//		==============================================================================================
		//Sign Out
		hp.signOut(wd, driver);
		
		driver.quit();
		
		
		
		
		
		
		
		
	}

}
