package testNG;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAAssertTest {
	@Test
	public void m1() {
		SoftAssert sa = new SoftAssert();
		System.out.println("----Step 1-----");
		System.out.println("----Step 2-----");
//		sa.assertEquals("A", "G"); //Exception at the end
		sa.assertEquals("A","A" );
		System.out.println("----Step 3-----");
		System.out.println("----Step 4-----");
		sa.assertAll();
	}
	@Test
	public void m2() {
		int a = 1;
		SoftAssert sa = new SoftAssert();
		System.out.println("----Step 1-----");
		System.out.println("----Step 2-----");
//		sa.assertEquals("A", "G"); Exception at the end
		sa.assertEquals("A","A" );
//		sa.assertNull(a); Exception
		sa.assertNotNull(a);
		System.out.println("----Step 3-----");
		System.out.println("----Step 4-----");
		sa.assertAll();
	}
}
