package lead_module;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import ObjectRepo.Creating_New_Lead_Page;
import ObjectRepo.HomePage;
import ObjectRepo.Lead_Verification_Msg_Page;
import ObjectRepo.LeadsPage;
import ObjectRepo.LoginPage;
import generic_libraries.ExcelUtil;
import generic_libraries.FileUtils;
import generic_libraries.JavaUtils;
import generic_libraries.WebDriver_Utils;

public class GUI_Creating_New_Lead_Test {

	@Test
	public  void creating_New_Lead() throws IOException {
		//Creating Objects of Util's
		FileUtils file = new FileUtils();
		WebDriver_Utils wd = new WebDriver_Utils();
		JavaUtils random = new JavaUtils();
		ExcelUtil excel = new ExcelUtil();
		
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
		//Creating the objects of POM Class
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		LeadsPage lead = new LeadsPage(driver);
		Creating_New_Lead_Page clp = new Creating_New_Lead_Page(driver);
		Lead_Verification_Msg_Page lvp = new Lead_Verification_Msg_Page(driver);
		
		//Login to application
		lp.login(username, password);
		
		//Click on leads
		hp.clickOnLeadsLink();
		
//		Click on 'Leads'  lookup image
		lead.clickoncreateLeadLookUp();
		
//		Enter mandatory fields with valid data
		String lastName=excel.readDataFromExcel("Leads", 0, 1)+"_"+random.getRandom();
		String companyName=excel.readDataFromExcel("Leads", 1, 1)+"_"+random.getRandom();
		
		clp.createLead(lastName, companyName);
		
		//Verification 
		lvp.verifyLeadCreated(lastName);
		
		//Sign out
		hp.signOut(wd, driver);
		
		driver.quit();
	}

}
