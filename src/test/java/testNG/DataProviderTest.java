package testNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

	@DataProvider
	public Object[][] storeData() {
		Object[][] obj = new Object[3][2];
		obj[0][0] = "TestYantra";
		obj[0][1] = "Banglore";
		
		obj[1][0] = "TekPyramid";
		obj[1][1] = "Hydrabad";
		
		obj[2][0]= "Infosys";
		obj[2][1] = "Mysore";
		
		return obj;
	}
	
	@Test(dataProvider = "storeData")
	public void getData(String company,String location) {
		System.out.println("Company = "+company+" Location = "+location);
	}
}
