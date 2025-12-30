package ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Lead_Verification_Msg_Page {
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement msg;
	public Lead_Verification_Msg_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public WebElement getMsg() {
		return msg;
	}
	public void verifyLeadCreated(String exp) {
		String actual = msg.getText();
//		System.out.println("actual = "+actual);
//		System.out.println("exp = "+exp);
		
		Assert.assertTrue(actual.contains(exp), "Lead is not created");
		System.out.println("Lead is created");
//		if(actual.contains(exp)) {
//			System.out.println("Lead is created");
//		}
//		else {
//			System.out.println("Lead is not created");
//		}
	}
	
	public void verifyLeadCreated(String acutualContact,String expectedContact) {
		Assert.assertTrue(acutualContact.contains(expectedContact), "Lead not converted to contact");
		System.out.println("Lead should be converted to contact");
//		if(acutualContact.contains(expectedContact)) {
//			System.out.println("Lead should be converted to contact");
//		}
//		else {
//			System.out.println("Lead not converted to contact");
//		}
	}
}
