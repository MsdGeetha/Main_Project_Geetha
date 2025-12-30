package ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Creating_New_Product_Page {
	
	@FindBy(name = "productname")
	private WebElement productName;
	
	@FindBy(id = "unit_price")
	private WebElement amount;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save;
	
	public Creating_New_Product_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getProductName() {
		return productName;
	}

	public WebElement getSave() {
		return save;
	}

	public WebElement getAmount() {
		return amount;
	}
	
	public void createProduct(String productname) {
		productName.sendKeys(productname);
//		Assert.fail();
		save.click();
	}
	public void createProduct(String productname,String amt) {
		productName.sendKeys(productname);
		amount.clear();
		amount.sendKeys(amt);
		save.click();
	}
}
