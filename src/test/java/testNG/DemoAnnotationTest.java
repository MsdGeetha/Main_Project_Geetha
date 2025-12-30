package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoAnnotationTest {
	@BeforeSuite
	public void before_suite() {
		System.out.println("Demo Before suite");
	}
	@AfterSuite
	public void after_suite() {
		System.out.println("Demo after suite");
	}
	@BeforeTest
	public void before_test() {
		System.out.println("Demo before test");
	}
	@AfterTest
	public void after_test() {
		System.out.println("Demo after test");
	}
	@BeforeClass
	public void before_class() {
		System.out.println("Demo before class");
	}
	@AfterClass
	public void after_class() {
		System.out.println("Demo after class");
	}
	@BeforeMethod
	public void before_method() {
		System.out.println("Demo before method");
	}
	@AfterMethod
	public void after_method() {
		System.out.println("Demo after method");
	}
	@Test
	public void m1() {
		System.out.println("Demo m1() ");
	}
	@Test
	public void m2() {
		System.out.println("Demo m2()");
	}
}
