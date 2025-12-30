package data_driven_testing;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CommonDataProperties_Test {

	public static void main(String[] args) throws Exception {
		//step1:get the path of the resource file
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
		
		//step 2 :create a object for a properties class
		Properties pObj = new Properties();
		
		//step 3 : load all the Keys by invoking file into object
		pObj.load(fis);
		
		//step 4: fetch the values using "getProperties" method 
		String Browser = pObj.getProperty("browser"); 
		String Url = pObj.getProperty("url");
		String Username = pObj.getProperty("username");
		String Password = pObj.getProperty("password");
		
		System.out.println(Browser);
		System.out.println(Url);
		System.out.println(Username);
		System.out.println(Password);
		
		//launch the browser
		WebDriver driver = null;
		if(Browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if (Browser.equalsIgnoreCase("edge")) {
			driver =new EdgeDriver();
		}
		else {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.get(Url);
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		Thread.sleep(3000);
		driver.findElement(By.id("submitButton")).click();
		
		Thread.sleep(3000);
		driver.quit();

	}

}

