package ObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_libraries.WebDriver_Utils;

public class Creating_New_Contact_Page extends WebDriver_Utils {
	//Declaration
	@FindBy(name = "lastname")
	private WebElement lastName;
	
	@FindBy(xpath = "//input[@name='account_id']/following-sibling::img[@title='Select']")
	private WebElement selectOrg;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save;
	
	@FindBy(id = "search_txt")
	private WebElement searchOrg;
	
	@FindBy(name = "search")
	private WebElement searchNow;
	
	//Initialization
	public Creating_New_Contact_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getSelectOrg() {
		return selectOrg;
	}

	public WebElement getSave() {
		return save;
	}

	public WebElement getSearchOrg() {
		return searchOrg;
	}

	public WebElement getSearchNow() {
		return searchNow;
	}
	//Business Logic
	public void createContact(String name) {
		lastName.sendKeys(name);
		save.click();
	}
	public void createContact(String name,WebDriver driver,String orgName) {
		lastName.sendKeys(name);
		selectOrg.click();
		switchToWindow(driver,"Accounts&action" );
		searchOrg.sendKeys(orgName);
		searchNow.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		switchToWindow(driver, "Contacts&action");
		save.click();
	}
	
}
