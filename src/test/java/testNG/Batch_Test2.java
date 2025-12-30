package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic_libraries.BaseClass;

public class Batch_Test2 extends BaseClass{
	@Test(groups = "Smoke")
	public void view1() {
//		Assert.fail();
		System.out.println("view1() from Parallel_Test2 - Smoke");
	}
	@Test(groups = "Regression")
	public void view2() {
		System.out.println("view2() from Parallel_Test2 - Regression");
	}
}
