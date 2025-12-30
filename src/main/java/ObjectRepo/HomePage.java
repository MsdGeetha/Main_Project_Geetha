package ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_libraries.WebDriver_Utils;

public class HomePage {
	//Declaration
	@FindBy(linkText = "Organizations")
	private WebElement organizationsLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;
	
	@FindBy(linkText = "Opportunities")
	private WebElement opportunitiesLink;
	
	@FindBy(linkText = "Leads")
	private WebElement leadsLink;
	
	@FindBy(linkText = "Products")
	private WebElement productsLinks;
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(name = "Campaigns")
	private WebElement campaignsLink;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOut;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administrator;
	
	//Initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization

	public WebElement getOrganizationsLink() {
		return organizationsLink;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getOpportunitiesLink() {
		return opportunitiesLink;
	}

	public WebElement getLeadsLink() {
		return leadsLink;
	}

	public WebElement getProductsLinks() {
		return productsLinks;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}

	public WebElement getSignOut() {
		return signOut;
	}

	public WebElement getImgSignOut() {
		return administrator;
	}
	
	//Business Logic 
	
	public void clickOnOrganizationsLink() {
		organizationsLink.click();
	}
	public void clickOnCampaignsLink() {
		campaignsLink.click();
	}
	public void clickOnMoreLink() {
		moreLink.click();
	}
	public void clickOnProductsLinks() {
		productsLinks.click();
	}
	public void clickOnLeadsLink() {
		leadsLink.click();
	}
	public void clickOnContactsLink() {
		contactsLink.click();
	}
	public void clickOnOpportunitiesLink() {
		opportunitiesLink.click();
	}
	public void signOut(WebDriver_Utils wlib,WebDriver driver) {
		administrator.click();
		wlib.waitUntillEleToBeVisible(driver, 10, signOut);
//		wlib.mouseHoverAndClickOnEle(driver, signOut);
		wlib.clickOnElementUsingJSE(driver, signOut);
	}
}
