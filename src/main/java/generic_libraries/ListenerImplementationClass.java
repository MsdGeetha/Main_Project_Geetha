package generic_libraries;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementationClass implements ITestListener,ISuiteListener {
	
	ExtentReports report;
	ExtentTest test;
	
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
//	String filename=new JavaUtils().sysdate();
	
	@Override
	public void onStart(ISuite suite) {
		ExtentSparkReporter htmlreport = new ExtentSparkReporter(".\\ExtentReport\\report"+new JavaUtils().sysdate()+".html");
		htmlreport.config().setDocumentTitle("TP-30");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setReportName("VTiger");
		
		report = new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("base_browser", "chrome");
		report.setSystemInfo("base_platform", "windows");
		report.setSystemInfo("base_url", "http://localhost:8888");
		report.setSystemInfo("ReporterName", "Geetha");
	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		test = report.createTest(methodName);
//		test.log(Status.INFO, methodName+"Excecution Starts");
		extentTest.set(test);
//		extentTest.get().log(Status.INFO, methodName+"Excecution Starts");
		extentTest.get().log(Status.INFO,"<b>"+methodName+"Excecution Starts </b>");//bold text
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	String methodName=result.getMethod().getMethodName();
//	test.log(Status.PASS, methodName+"---->passed");
//	extentTest.get().log(Status.PASS, methodName+"----->Passed");
	extentTest.get().log(Status.PASS, "<i>"+methodName+"----->Passed </i>");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
//		String methodName = result.getMethod().getMethodName();
//		String fileName = methodName+new JavaUtils().sysdate();
		String methodName=result.getMethod().getMethodName();
		String filename=methodName+new JavaUtils().sysdate();
		
//		This is for normal Implementation
		try {
//		String filePath = ".\\Screenshot\\"+filename+".png";
//		
//		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
//		File src = ts.getScreenshotAs(OutputType.FILE);
//		File dst = new File(filePath);
//		
////			FileHandler.copy(src, dst);
//			FileUtils.copyFile(src, dst);
//			String path = dst.getAbsolutePath();
//			test.addScreenCaptureFromPath(path);//attaching failed screenshot into the extent report
			TakesScreenshot ts = (TakesScreenshot) BaseClass.wdriver.get();
			
			// Base64 screenshot

			String base64 = ts.getScreenshotAs(OutputType.BASE64);
			
			// attach to report
			extentTest.get().addScreenCaptureFromBase64String(base64, methodName);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			extentTest.get().log(Status.WARNING, "Screenshot failed: " + e.getMessage());
		}
		
		test.log(Status.FAIL, result.getThrowable());
//		test.log(Status.FAIL, methodName+"---->Failed");
		test.log(Status.FAIL, "<i>"+methodName+"---->Failed</i>");//italic text
		
//		This is trying from the Util's Class
//		try {
//			String path=WebDriver_Utils.takeScreenshotWholePage(BaseClass.sdriver, fileName);
//			test.addScreenCaptureFromPath(path);
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		test.log(Status.FAIL, result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		String methodName=result.getMethod().getMethodName();
//		test.log(Status.SKIP, methodName+"-------->Skipped");
//		test.log(Status.SKIP, "<i>"+methodName+"-------->Skipped</i>");
		extentTest.get().log(Status.SKIP, "<i>\"+methodName+\"-------->Skipped</i>");
		
	}
//
//	@Override
//	public void onStart(ITestContext context) {
//		ExtentSparkReporter htmlreport = new ExtentSparkReporter(".\\ExtentReport\\report"+new JavaUtils().sysdate()+".html");
//		htmlreport.config().setDocumentTitle("TP-30");
//		htmlreport.config().setTheme(Theme.DARK);
//		htmlreport.config().setReportName("VTiger");
//		
//		report = new ExtentReports();
//		report.attachReporter(htmlreport);
//		report.setSystemInfo("base_browser", "chrome");
//		report.setSystemInfo("base_platform", "windows");
//		report.setSystemInfo("base_url", "http://localhost:8888");
//		report.setSystemInfo("ReporterName", "Geetha");
//	}
//
//	@Override
//	public void onFinish(ITestContext context) {
//	report.flush();
//	}
	
}
