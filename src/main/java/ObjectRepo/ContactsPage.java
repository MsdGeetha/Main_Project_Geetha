package ObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_libraries.WebDriver_Utils;

public class ContactsPage extends WebDriver_Utils {
	//Declaration
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createContact;
	
	@FindBy(id = "bas_searchfield")
	private WebElement in;
	
	@FindBy(name = "search_text")
	private WebElement searchFor;
	
	@FindBy(name = "submit")
	private WebElement searchNow;
	
	//Initialization
	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	
	//Utilization
	public WebElement getCreateContact() {
		return createContact;
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

	public void clickonContactLookUp() {
		createContact.click();
	}
	public String searchForContacts(WebDriver driver,String lastName) {
		dropdownUsingContainsVisibleText(in, "Last Name");
		searchFor.sendKeys(lastName);
		searchNow.click();
		String acutualContact = driver.findElement(By.xpath("//a[text()='"+lastName+"']")).getText();
		return acutualContact;
	}
	
	
	
}
