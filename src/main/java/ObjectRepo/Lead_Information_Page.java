package ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Lead_Information_Page {
	@FindBy(linkText = "Convert Lead")
	private WebElement convertLead;
	
	@FindBy(name = "Save")
	private WebElement save;
	
	public Lead_Information_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getConvertLead() {
		return convertLead;
	}

	public WebElement getSave() {
		return save;
	}
	
	
}
