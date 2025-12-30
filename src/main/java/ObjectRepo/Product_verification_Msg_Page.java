package ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Product_verification_Msg_Page {
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement msg;
	public Product_verification_Msg_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public WebElement getMsg() {
		return msg;
	}
	public void verifyProductCreated(String expted) {
		String actual = msg.getText();
		Assert.assertTrue(actual.contains(expted), "Product is not created");
		System.out.println("Product has been created ");
//		if(actual.contains(expted)) {
//			System.out.println("Product has been created ");
//		}
//		else
//		{
//			System.out.println("Product is not created");
//		}
	}
}
