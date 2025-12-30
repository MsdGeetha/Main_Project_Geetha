package ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_libraries.WebDriver_Utils;

public class Opportunity_Information_Page extends WebDriver_Utils {
	@FindBy(linkText = "More Information")
	private WebElement moreInfo;
	
	@FindBy(xpath = "//a[@class='drop_down' and text()='Products']")
	private WebElement products;
	
	public Opportunity_Information_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getMoreInfo() {
		return moreInfo;
	}

	public WebElement getProducts() {
		return products;
	}
	
	public void MoreInfoClickOnProuct(WebDriver driver) {
		mouseHover(driver, moreInfo);
		products.click();
	}
	
}
