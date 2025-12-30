package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DemoTest {
	@BeforeSuite
	public void before_suite() {
		System.out.println("before suite");
	}
	@AfterSuite
	public void after_suite() {
		System.out.println("After suite");
	}
	@BeforeClass
	public void before_class() {
		System.out.println("before class");
	}
	@AfterClass
	public void after_class() {
		System.out.println("After Class");
	}
	@AfterClass
	public void after_class2() {
		System.out.println("After Class2");
	}
	@BeforeMethod
	public void before_method() {
		System.out.println("before method");
	}
	@BeforeMethod
	public void before_method2() {
		System.out.println("before method 2");
	}
	@AfterMethod
	public void after_method() {
		System.out.println("After Method");
	}
	@Test
	public void m1() {
		System.out.println("@Test 1 method");
	}
	@Test
	public void m2() {
		System.out.println("@Test 2 method");
	}
}
