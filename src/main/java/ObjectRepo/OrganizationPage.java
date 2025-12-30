package ObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_libraries.WebDriver_Utils;

public class OrganizationPage extends WebDriver_Utils{
	//Declaration
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createOrg;
	
	@FindBy(id = "bas_searchfield")
	private WebElement in;
	
	@FindBy(name = "search_text")
	private WebElement searchFor;
	
	@FindBy(name = "submit")
	private WebElement searchNow;
	
	//Initialization
	public OrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//Utilization

	public WebElement getCreateOrg() {
		return createOrg;
	}

	public WebElement getIn() {
		return in;
	}

	public WebElement getSearchFor() {
		return searchFor;
	}

	public WebElement getSearchNow() {
		return searchNow;
	}
	public void clickonOrganizationLookUp() {
		createOrg.click();
	}
	public String searchForOrgs(WebDriver driver,String companyName) {
		dropdownUsingContainsVisibleText(in, "Organization Name");
		searchFor.sendKeys(companyName);
		searchNow.click();
		String actualOrg = driver.findElement(By.xpath("//a[text()='"+companyName+"' and @title='Organizations']")).getText();
		return actualOrg;
	}
	
}
