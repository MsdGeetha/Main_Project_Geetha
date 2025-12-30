package ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Contact_Verification_Msg_Page {
	// Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement msg;
	
	//Initialization
	public Contact_Verification_Msg_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//Utilization
	public WebElement getMsg() {
		return msg;
	}
	
	//Business Logic
	public void verifyContactCreated(String lastname) {
		String actual = msg.getText();
		
		Assert.assertTrue(actual.contains(lastname), "Contacts has not been created");
		System.out.println("Contact has been crated");
//		if(actual.contains(lastname)) {
//			System.out.println("Contact has been crated");
//		}
//		else {
//			System.out.println("Contacts has not been created ");
//		}
	}

}
