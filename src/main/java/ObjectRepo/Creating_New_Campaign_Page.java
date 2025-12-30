package ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Creating_New_Campaign_Page {
	
	@FindBy(name = "campaignname")
	private WebElement campaignName;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save;
	
	public Creating_New_Campaign_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getCampaignName() {
		return campaignName;
	}

	public WebElement getSave() {
		return save;
	}
//	Business Logic
	public void createCampaign(String campaignname) {
		campaignName.sendKeys(campaignname);
		save.click();
	}
	
}
