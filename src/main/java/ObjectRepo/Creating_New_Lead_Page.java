package ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Creating_New_Lead_Page {
	@FindBy(name = "lastname")
	private WebElement lastname;
	
	@FindBy(name = "company")
	private WebElement company;
	
	@FindBy(id = "email")
	private WebElement mail;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save;
	
	public Creating_New_Lead_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getLastname() {
		return lastname;
	}

	public WebElement getCompany() {
		return company;
	}

	public WebElement getSave() {
		return save;
	}
	
	//business logic
	public void createLead(String lastName,String cmp) {
		lastname.sendKeys(lastName);
		company.sendKeys(cmp);
		Assert.fail();
		save.click();
	}
	public void createLead(String lastName,String cmp,String email) {
		lastname.sendKeys(lastName);
		company.sendKeys(cmp);
		mail.sendKeys(email);
		save.click();
	}
	
}
