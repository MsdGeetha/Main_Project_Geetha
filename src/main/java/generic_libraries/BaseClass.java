package generic_libraries;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import ObjectRepo.HomePage;
import ObjectRepo.LoginPage;

public class BaseClass {
	//Creating the Objects of the Util's
	public WebDriver_Utils wLib = new WebDriver_Utils();
	public JavaUtils jLib = new JavaUtils();
	public ExcelUtil eLib = new ExcelUtil();
	public FileUtils fLib = new FileUtils();
	public DataBaseUtils dLib = new DataBaseUtils();
	
	public WebDriver driver;
	public static WebDriver sdriver;
//	public static Object wdriver;
	public static ThreadLocal<WebDriver>wdriver=new ThreadLocal<WebDriver>();
	
	
	@BeforeSuite(alwaysRun = true)
	public void connectToDB() throws SQLException {
//		Connection con = dLib.connectToDB();
		System.out.println("Connected to DB");
		System.out.println("Before Suite Is executing");
	}
	@AfterSuite(alwaysRun = true)
	public void disConnectToDb() throws SQLException {
//		dLib.disconnectDB();
		System.out.println("Disconnected from DB");
		System.out.println("After Suite Is executing");
	}
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void lunchBrowser(@Optional("chrome") String browser) throws IOException {
		
		//String browser = fLib.readDataFromProperties("browser");
		String url = fLib.readDataFromProperties("url");
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty(
		            "webdriver.edge.driver",
		            "C:\\Drivers\\msedgedriver.exe"
		        );

		       driver = new EdgeDriver();
		}
		else {
			driver = new FirefoxDriver();
		}
		
		sdriver = driver;
		driver.get(url);
		//maximize window
		wLib.maximizeWindow(driver);
		
		//Wait for the page load 
		wLib.waitForPageLoad(driver, 10);
		
		System.out.println("Browser Lunched Successfully");
	}
	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
		System.out.println("Browser Closed");
	}
	
	@BeforeMethod(alwaysRun = true)
	public void loginToApp() throws IOException {
		String username=fLib.readDataFromProperties("username");
		String password = fLib.readDataFromProperties("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);
		
		
		System.out.println("Logged into application");
	}
	
	@AfterMethod(alwaysRun = true)
	public void logoutFromApp() {
		HomePage hp = new HomePage(driver);
		hp.signOut(wLib, driver);
		System.out.println("Logged Out");
	}
	
	
}
