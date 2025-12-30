package testNG;

import org.testng.annotations.Test;

public class SampleTest {
	@Test(priority = -1)
//	@Test
	public void Sample_m4() {
//		int a[] = {1,2,3};
//		System.out.println(a[5]);
		System.out.println("---Create-----");
	}
	@Test(dependsOnMethods = "Sample_m4",priority = -1)
	public void Sample_m2() {
		System.out.println("----Edit-------");
	}
	
//	@Test(invocationCount = 3)
	@Test
	public void Sample_m3() {
		System.out.println("----Delete-----");
	}
	
//	@Test(priority = -2)
	@Test
	public void Sample_m1() {
		System.out.println("----Test------");
	}
	
	
}
