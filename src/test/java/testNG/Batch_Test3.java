package testNG;

import org.testng.annotations.Test;

import generic_libraries.BaseClass;

public class Batch_Test3 extends BaseClass{
	@Test(groups = "Smoke")
	public void sample() {
		System.out.println("Sample() from Parallel_Test3 - Smoke");
	}
}
