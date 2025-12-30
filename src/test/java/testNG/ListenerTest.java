package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import generic_libraries.BaseClass;

@Listeners(generic_libraries.ListenerImplementationClass.class)
public class ListenerTest extends BaseClass{
	@Test
	public void test() {
		Assert.fail();
		System.out.println("Testing the Listener");
	}
}
