package ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_libraries.WebDriver_Utils;

public class Creating_New_Organization_Page extends WebDriver_Utils{
	//Declaration
	@FindBy(name = "accountname")
	private WebElement orgName;
	
	@FindBy(name = "industry")
	private WebElement industryName;
	
	@FindBy(name = "accounttype")
	private WebElement type;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save;
	
	//Initialization
	
	public Creating_New_Organization_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization

	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getIndustryName() {
		return industryName;
	}

	public WebElement getType() {
		return type;
	}

	public WebElement getSave() {
		return save;
	}
	
	//Business Logic
	
	public void createOrganization(String name) {
		orgName.sendKeys(name);
		save.click();
	}
	public void createOrganization(String name,String industryType) {
		orgName.sendKeys(name);
		dropdownUsingContainsVisibleText(industryName, industryType);
		save.click();
	}
	public void createOrganization(String name,String industryType,String typeValue ) {
		orgName.sendKeys(name);
		dropdownUsingContainsVisibleText(industryName, industryType);
		dropdownUsingContainsVisibleText(type, typeValue);
		save.click();
		
	}

	
	
}
