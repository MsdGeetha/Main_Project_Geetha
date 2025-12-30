package testNG;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ObjectRepo.LoginPage;

public class DataProviderExample {
	@DataProvider
	public Object[][] storeData() {
		Object[][] obj = new Object[2][2];
		obj[0][0] = "admin";
		obj[0][1] = "admin";
		
		obj[1][0] = "admin";
		obj[1][1] = "password";
		
		return obj;
	}
	
	@Test(dataProvider = "storeData")
	public void getData(String username,String password) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("http://localhost:8888/");
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);
	}
}
