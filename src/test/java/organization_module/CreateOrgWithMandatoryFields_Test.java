package organization_module;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

//Test case 1
public class CreateOrgWithMandatoryFields_Test {

	public static void main(String[] args) throws InterruptedException {
		//I have created another Class for this 
		Random ran = new Random();
		int random = ran.nextInt(500);
		
		//Lunch The Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("http://localhost:8888/");
//		Login to application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
//		Click on 'ORGANIZATIONS' link
		driver.findElement(By.linkText("Organizations")).click();
		
//		Click on 'ORGANIZATIONS'  lookup image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
//		Enter mandatory fields with valid data
		driver.findElement(By.name("accountname")).sendKeys("Q2"+random);
		
//		click on 'Save' button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		Thread.sleep(3000);
		//sign out
		//click on Administrator icon
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		
		//mousehover on the signout link and click on that
		WebElement signout = driver.findElement(By.linkText("Sign Out"));
		
		Actions act = new Actions(driver);
		act.moveToElement(signout).click().perform();
		
		driver.quit();
	}

}
