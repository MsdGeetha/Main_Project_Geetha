package ObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_libraries.WebDriver_Utils;

public class Opportunity_More_Information_Page extends WebDriver_Utils {
	
	@FindBy(xpath = "//input[@title='Select Products']")
	private WebElement selProducts;
	
	@FindBy(id = "search_txt")
	private WebElement search;
	
	public Opportunity_More_Information_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public WebElement getSelProducts() {
		return selProducts;
	}
	
	public WebElement getSearch() {
		return search;
	}
	public String selctProduct(WebDriver driver,String productName) {
		selProducts.click();
		switchToWindow(driver, "Products&return");
		search.sendKeys(productName,Keys.ENTER);
		driver.findElement(By.xpath("//a[text()='"+productName+"']")).click();
		switchToWindow(driver, "CallRelatedList&module");
		WebElement prdName = driver.findElement(By.xpath("//div[@id='tbl_Potentials_Products']/table[2]/descendant::a[contains(@href,'DetailView&module=Products&record')]"));
		waitUntillEleToBeVisible(driver, 50, prdName);
		String actualProd = driver.findElement(By.xpath("//div[@id='tbl_Potentials_Products']/table[2]/descendant::a[contains(@href,'DetailView&module=Products&record')]")).getText();
		System.out.println("actualProd = "+actualProd);
		return actualProd;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

