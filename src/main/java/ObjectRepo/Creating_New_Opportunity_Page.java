package ObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_libraries.WebDriver_Utils;

public class Creating_New_Opportunity_Page extends WebDriver_Utils{
	@FindBy(name = "potentialname")
	private WebElement oppName;
	
	@FindBy(name = "amount")
	private WebElement amount;
	
	@FindBy(xpath = "//input[@id='related_to_display']/following-sibling::img[@title='Select']")
	private WebElement relatedTo;
	
	@FindBy(id = "search_txt")
	private WebElement searchOrg;
	
	@FindBy(name = "search")
	private WebElement searchNow;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save;
	
	public Creating_New_Opportunity_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getOppName() {
		return oppName;
	}

	public WebElement getAmount() {
		return amount;
	}

	public WebElement getRelatedTo() {
		return relatedTo;
	}

	public WebElement getSave() {
		return save;
	}
	
	public void createOpportunity(WebDriver driver,String oppname,String amt,String orgName) {
		oppName.sendKeys(oppname);
		amount.clear();
		amount.sendKeys(amt);
		relatedTo.click();
		switchToWindow(driver, "Accounts&action");
		searchOrg.sendKeys(orgName);
		searchNow.click();
		driver.findElement(By.xpath("//a[contains(text(),'"+orgName+"')]")).click();
		switchToWindow(driver, "Potentials&action");
		save.click();
		
	}
	
}
