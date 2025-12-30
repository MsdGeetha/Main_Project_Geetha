package ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_libraries.WebDriver_Utils;

public class LeadsPage extends WebDriver_Utils{
	@FindBy(xpath = "//img[@title='Create Lead...']")
	private WebElement createLead;
	
	@FindBy(id = "bas_searchfield")
	private WebElement in;
	
	@FindBy(name = "search_text")
	private WebElement searchFor;
	
	@FindBy(name = "submit")
	private WebElement searchNow;
	
	@FindBy(xpath = "//td[contains(text(),'Showing Records')]")
	private WebElement msg;
	
	@FindBy(linkText = "Convert Lead")
	private WebElement convert;
	
	@FindBy(name = "Save")
	private WebElement saving;
	
	@FindBy(xpath = "//a[contains(text(),'Olekar')]")
	private WebElement lastName;
	
	public LeadsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public WebElement getCreateLead() {
		return createLead;
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
	public WebElement getMsg() {
		return msg;
	}
	public WebElement getConvert() {
		return convert;
	}
	public WebElement getSaving() {
		return saving;
	}
	//business logic
	public void clickoncreateLeadLookUp() {
		createLead.click();
	}
	public void searchForLead(WebDriver driver,String value,String search) throws InterruptedException {
		dropdownUsingContainsVisibleText(in, value);
		searchFor.sendKeys(search);
		searchNow.click();
//		waitUntillEleToBeVisible(driver, 20, lastName);
//		System.out.println(lastName.getText());
		Thread.sleep(3000);
		System.out.println(msg.getText());
	}
	public void convertToLead() throws InterruptedException {
		Thread.sleep(5000);
		convert.click();
		Thread.sleep(9000);
		saving.click();
		
	}
}
