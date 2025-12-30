package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HardAssertTest {
	
	@Test
	public void m1() {
		System.out.println("---Step 1---");
		System.out.println("---Step 2---");
		Assert.assertEquals("A", "A");
//		Assert.assertEquals("B", "A"); //throws Exception
		System.out.println("---Step 3---");
		System.out.println("---Step 4---");
	}
	
	@Test
	public void m2() {
		int a = 10;
		System.out.println("---Step 1---");
		System.out.println("---Step 2---");
//		Assert.assertNull(a);//Exception
		Assert.assertNotNull(a);
		System.out.println("---Step 3---");
		System.out.println("---Step 4---");
	}
	@Test
	public void m3() {
		String exp = "vtiger CRM 5 - Commercial Open Source CRM";
		String exp1 = "vtiger";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, exp);
		Assert.assertTrue(title.contains(exp1));
		driver.quit();
	}

}
