package ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Products_Page {
	//Declaration
	@FindBy(xpath = "//img[@title='Create Product...']")
	private WebElement createProduct;
	//Initialization
	public Products_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//Utilization
	public WebElement getCreateProduct() {
		return createProduct;
	}
	//Business logic
	public void clickonProductLookUp() {
		createProduct.click();
	}
}
