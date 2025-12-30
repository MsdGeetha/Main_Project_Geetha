package organization_module;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import ObjectRepo.Creating_New_Organization_Page;
import ObjectRepo.HomePage;
import ObjectRepo.LoginPage;
import ObjectRepo.OrganizationPage;
import ObjectRepo.Organization_Verification_Msg_Page;
import generic_libraries.ExcelUtil;
import generic_libraries.FileUtils;
import generic_libraries.JavaUtils;
import generic_libraries.WebDriver_Utils;

public class GUI_Creating_Org_Test {
	
	@Test
	public  void creating_Org() throws IOException {
		//Creating the Objects of Generic utils

		FileUtils utils = new FileUtils();
		JavaUtils random = new JavaUtils();
		ExcelUtil excel = new ExcelUtil();
		WebDriver_Utils webDriver = new WebDriver_Utils();

		WebDriver driver= null;

		//Calling the methods
		String browser = utils.readDataFromProperties("browser");
		String url=utils.readDataFromProperties("url");
		String username=utils.readDataFromProperties("username");
		String password=utils.readDataFromProperties("password");

		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
			webDriver.maximizeWindow(driver);
			webDriver.waitForPageLoad(driver, 10);
		}
		driver.get(url);
	
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);

		//		Click on 'ORGANIZATIONS' link

		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationsLink();

		//		Click on 'ORGANIZATIONS'  lookup image

		OrganizationPage op = new OrganizationPage(driver);
		op.clickonOrganizationLookUp();
		
		// Reading data from excel
		String orgName=excel.readDataFromExcel("Organizations", 0, 1)+"_"+random.getRandom();
		System.out.println("orgName = "+orgName);
		String industryName=excel.readDataFromExcel("Organizations", 1, 1);
		System.out.println("industryName = "+industryName);
		String typeName=excel.readDataFromExcel("Organizations", 2, 1);
		System.out.println("typeName = "+typeName);
		
		Creating_New_Organization_Page cop = new Creating_New_Organization_Page(driver);
		cop.createOrganization(orgName, industryName, typeName);

		Organization_Verification_Msg_Page m = new Organization_Verification_Msg_Page(driver);
		m.verifyOrgCreated(orgName);

		hp.signOut(webDriver, driver);

		driver.quit();

	}

}
