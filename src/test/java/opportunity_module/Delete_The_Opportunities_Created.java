package opportunity_module;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import generic_libraries.ExcelUtil;
import generic_libraries.FileUtils;
import generic_libraries.JavaUtils;
import generic_libraries.WebDriver_Utils;

public class Delete_The_Opportunities_Created {

//	public static void main(String[] args) throws IOException {
	@Test
	public void delete() throws IOException {
		//create the objects of the util's
		
		FileUtils file = new FileUtils();
		ExcelUtil excel = new ExcelUtil();
		JavaUtils jUtil = new JavaUtils();
		WebDriver_Utils wd = new WebDriver_Utils();
		WebDriver driver = null;
		
		String browser=file.readDataFromProperties("browser");
		String url = file.readDataFromProperties("url");
		String username = file.readDataFromProperties("username");
		String password = file.readDataFromProperties("password");
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			wd.maximizeWindow(driver);
			wd.waitForPageLoad(driver, 15);
		}
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
//		Navigate to Home Page Click on 'Products'
		driver.findElement(By.linkText("Opportunities")).click();
		
		driver.findElement(By.id("selectCurrentPageRec")).click();
		
//		driver.findElement(By.xpath("//input[@name='selected_id']")).click();
		driver.findElement(By.xpath("//input[@class='crmbutton small delete']")).click();
		
		driver.switchTo().alert().accept();
		
		System.out.println("Contacts are deleted");
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		WebElement signOut = driver.findElement(By.linkText("Sign Out"));
		
		wd.mouseHoverAndClickOnEle(driver, signOut);
		driver.quit();

	}

}
