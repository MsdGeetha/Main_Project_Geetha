package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test_Browser {
    public static void main(String[] args) {

//        System.setProperty(
//            "webdriver.edge.driver",
//            "C:\\Drivers\\msedgedriver.exe"
//        );
//
//        WebDriver driver = new EdgeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://www.google.com");
    	
    	WebDriver driver = new FirefoxDriver();
    	driver.manage().window().maximize();
      driver.get("https://www.google.com");
        driver.quit();
    }
}

