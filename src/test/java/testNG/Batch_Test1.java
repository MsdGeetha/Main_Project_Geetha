package testNG;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import generic_libraries.BaseClass;

@Listeners(generic_libraries.ListenerImplementationClass.class)
public class Batch_Test1 extends BaseClass {
	@Test(groups = "Smoke",retryAnalyzer = generic_libraries.RetryImplementationClass.class)
	public void display1() {
//		Assert.fail("Testing");
		System.out.println("display1() from Parallel_Test1 - Smoke");
	}
	
	@Test(groups = "Regression",retryAnalyzer = generic_libraries.RetryImplementationClass.class)
	public void display2() {
//		Assert.fail();
		System.out.println("display2() from Parallel_Test1 - Regression");
	}
}
