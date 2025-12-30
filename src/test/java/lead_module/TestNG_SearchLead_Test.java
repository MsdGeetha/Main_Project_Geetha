package lead_module;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ObjectRepo.HomePage;
import ObjectRepo.LeadsPage;
import ObjectRepo.LoginPage;
import generic_libraries.BaseClass;

@Listeners(generic_libraries.ListenerImplementationClass.class)
public class TestNG_SearchLead_Test extends BaseClass{

	@Test(groups = "Leads",retryAnalyzer = generic_libraries.RetryImplementationClass.class)
	public void searchLead() throws EncryptedDocumentException, IOException, InterruptedException {
		//Creating Objects of the POM Class

				HomePage hp = new HomePage(driver);
				LeadsPage lead = new LeadsPage(driver);
				
				//Click on leads
				hp.clickOnLeadsLink();
				
//				3. Enter name in search box
				String in = eLib.readDataFromExcel("Leads",2 , 1);
				String search=eLib.readDataFromExcel("Leads", 0, 1);
//				WebElement web = driver.findElement(By.xpath("//a[contains(text(),'Olekar')]"));
//				System.out.println(web.getText());
				lead.searchForLead(driver,in, search);
				
				System.out.println("======================SearchLead_Complete 6======================");
	}
}
