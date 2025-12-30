package lead_module;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import ObjectRepo.HomePage;
import ObjectRepo.LeadsPage;
import ObjectRepo.LoginPage;
import generic_libraries.ExcelUtil;
import generic_libraries.FileUtils;
import generic_libraries.JavaUtils;
import generic_libraries.WebDriver_Utils;

public class GUI_Search_Lead_Test {
	
	@Test
	public void search_Lead() throws IOException, InterruptedException {
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
		
		// login to application
		lp.login(username, password);
		
		//Click on leads
		hp.clickOnLeadsLink();
		
//		3. Enter name in search box
		String in = excel.readDataFromExcel("Leads",2 , 1);
		String search=excel.readDataFromExcel("Leads", 0, 1);
		lead.searchForLead(driver,in, search);
		
		//Sign Out
		hp.signOut(wd, driver);
		
		driver.quit();
	

	}

}
