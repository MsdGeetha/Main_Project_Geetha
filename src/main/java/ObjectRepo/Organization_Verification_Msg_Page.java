package ObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Organization_Verification_Msg_Page {
	//Declaration
		@FindBy(xpath = "//span[@class='dvHeaderText']")
		private WebElement msg;
		//initialization
		public Organization_Verification_Msg_Page(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		//Utilization
		public WebElement getMsg() {
			return msg;
		}
		//business logic
		public void verifyOrgCreated(String exp) {
			String actual =msg.getText();
			Assert.assertTrue(actual.contains(exp), "Organization is not created");
			System.out.println("Organization created Successfully");
//			if(actual.contains(exp)) {
//				System.out.println("Organization created Successfully");
//			}
//			else {
//				System.out.println("Organization is not created");
//			}
		}
		
		public void verifyOrgCreated(String actualOrg,String expcted) {
			Assert.assertTrue(actualOrg.contains(expcted), "Org is not appered");
			System.out.println("org should appear");
//			if(actualOrg.contains(expcted)) {
//				System.out.println("org should appear");
//			}
//			else {
//				System.out.println("Org is not appered");
//			}
		}
		
}
