package ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Campaign_Verification_Msg_Page {
	//Declaration
			@FindBy(xpath = "//span[@class='dvHeaderText']")
			private WebElement msg;
			//initialization
			public Campaign_Verification_Msg_Page(WebDriver driver) {
				PageFactory.initElements(driver, this);
			}
			//Utilization
			public WebElement getMsg() {
				return msg;
			}
			//business logic
			public void verifyCampaigCreated(String exp) {
				String actual =msg.getText();
				Assert.assertTrue(actual.contains(exp), "campaign name is not created");
				System.out.println("campaig name is created");
//				if(actual.contains(exp)) {
//					System.out.println("campaig name is created");
//				}
//				else {
//					System.out.println("campaign name is not created");
//				}
			}
}
