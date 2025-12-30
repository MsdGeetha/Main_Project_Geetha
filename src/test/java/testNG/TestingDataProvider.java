package testNG;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import ObjectRepo.LoginPage;
import generic_libraries.ExcelUtil;

public class TestingDataProvider {
	
	@Test(dataProviderClass = StoreDataProviderTest.class,dataProvider = "store_Proucts")
	public void getData(String pname,int price) {
		System.out.println(pname+"------->"+price);
	}
	
	@Test(dataProviderClass = StoreDataProviderTest.class,  dataProvider = "Crds")
	public void test(String un,String pw) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("http://localhost:8888/");
		LoginPage lp = new LoginPage(driver);
		lp.login(un, pw);
	}
	@Test(dataProviderClass = StoreDataProviderTest.class,dataProvider = "store_Company")
	public void getcompany(String cmpName,String place) {
		System.out.println(cmpName+"---------->"+place);
	}
	
	//Reading data from generic util's
	@Test(dataProviderClass = StoreDataProviderTest.class,dataProvider = "readData")
	public void getCompany(String cmpName,String place) {
		System.out.println(cmpName+"---------->"+place);
		
	}

}
