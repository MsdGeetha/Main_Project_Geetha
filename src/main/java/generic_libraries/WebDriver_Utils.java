package generic_libraries;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriver_Utils {
	/**
	 * This method which is used to maximize the window 
	 * @author GEETHA
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	/**
	 * This method is used to wait for the page load
	 * @author GEETHA
	 * @param driver
	 * @param seconds
	 */
	public void waitForPageLoad(WebDriver driver,int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
	/**
	 * This method will wait until element is available on webPage
	 * @param driver
	 * @param seconds
	 * @param element
	 */
	public void waitUntillEleToBeVisible(WebDriver driver,int seconds,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * This method will wait until the element to be click able
	 * @param driver
	 * @param seconds
	 * @param element
	 */
	public void waitUtillTheElementTobeClickable(WebDriver driver,int seconds,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * This method which is used to get the values to the drop down
	 * @param element
	 * @return
	 */
	public Select dropdown(WebElement element) {
		Select sel=new Select(element);
		return sel;
	}
	/**
	 * This method which is used to get the values to the drop down by using the value
	 * @param element
	 * @return
	 */
	public void dropdownUsingValue(WebElement element,String value) {
		dropdown(element).selectByValue(value);
	}
	/**
	 * This method which is used to get the values to the drop down by using the Index
	 * @param element
	 * @return
	 */
	public void dropdownUsingIndex(WebElement element,int index) {
		dropdown(element).selectByIndex(index);
	}
	/**
	 * This method which is used to get the values to the drop down by using the Visible text
	 * @param element
	 * @return
	 */
	public void dropdownUsingVisibleText(WebElement element,String text) {
		dropdown(element).selectByVisibleText(text);
		/**
		 * This method which is used to get the values to the drop down by using the Contains Visible text
		 * @param element
		 * @return
		 */
	}
	public void dropdownUsingContainsVisibleText(WebElement element,String text) {
		dropdown(element).selectByContainsVisibleText(text);
	}
	/**
	 * This is Actions Class methods which is use to do the keyboard and move actions
	 * @param driver
	 * @return
	 */
	
	public Actions actions(WebDriver driver) {
		Actions act=new Actions(driver);
		return act;
	}
	/**
	 * This is Actions Class methods which is use to do mouse Hover on element
	 * @param driver
	 * @param element
	 */
	public void mouseHover(WebDriver driver,WebElement element) {
		actions(driver).moveToElement(element).perform();
	}
	/**
	 * This is Actions Class methods which is use to do move the element
	 * @param driver
	 * @param element
	 */
	public void mouseHoverAndClickOnEle(WebDriver driver,WebElement element) {
		actions(driver).moveToElement(element).click(element).perform();
	}
	/**
	 * This is Actions Class methods which is use to drag and drop the element
	 * @param driver
	 * @param srcElemet
	 * @param destElement
	 */
	public void dragAndDrop(WebDriver driver,WebElement srcElemet,WebElement destElement) {
		actions(driver).dragAndDrop(srcElemet, destElement).perform();
	}
	/**
	 * This is Actions Class methods which is use to double click on the page
	 * @param driver
	 * @param element
	 */
	public void doubbleClick(WebDriver driver,WebElement element) {
		actions(driver).doubleClick().perform();
	}
	/**
	 * This is Actions Class methods which is use to scroll to the element
	 * @param driver
	 * @param element
	 */
	public void scrolToElement(WebDriver driver,WebElement element) {
		actions(driver).scrollToElement(element).perform();
	}
	/**
	 * This is Actions Class methods which is use to double click on the element
	 * @param driver
	 * @param element
	 */
	public void doubbleClickOnEle(WebDriver driver,WebElement element) {
		actions(driver).doubleClick(element).perform();
	}
	/**
	 * This is Actions Class methods which is use to right click on the element
	 * @param driver
	 * @param element
	 */
	public void rightClickOnElement(WebDriver driver,WebElement element) {
		actions(driver).contextClick(element).perform();
	}
	/**
	 * This is Actions Class methods which is use to right click on the web page
	 * @param driver
	 */
	public void rightClickOnWebPage(WebDriver driver) {
		actions(driver).contextClick().perform();
	}
	/**
	 * This is Actions Class methods which is use to click and hold the element 
	 * @param driver
	 * @param element
	 */
	public void clickAndHold(WebDriver driver,WebElement element) {
		actions(driver).clickAndHold(element).perform();
	}
	/**
	 * This is Actions Class methods which is use to click, hold and release the element 
	 * @param driver
	 * @param element
	 */
	public void clickHoldAndRelease(WebDriver driver,WebElement element) {
		actions(driver).clickAndHold(element).release().perform();
	}
	/**
	 * This method used to switch to the child windows
	 * @param driver
	 * @param expTitle
	 */
	public void switchToWindow(WebDriver driver,String expTitle) {
//		String parent = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		while(it.hasNext()) {
			String currentWin = it.next();
			String actualTitle = driver.switchTo().window(currentWin).getTitle();
			if(actualTitle.contains(expTitle)) {
				break;
			}
		}
	}
	/**
	 * This method is used to switch back to the parent window
	 * @param driver
	 * @param parent
	 */
//	public void swichToParent(WebDriver driver,String parent) {
//		driver.switchTo().window(parent);
//	}
	/**
	 * This the Methods which me used to switch to Frames using index
	 * @param driver
	 * @param index
	 */
	public void switchToChildFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	/**
	 * This the Methods which me used to switch to Frames using element
	 * @param driver
	 * @param element
	 */
	public void switchToChildFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
	/**
	 * This the Methods which me used to switch to Frames using name
	 * @param driver
	 * @param name
	 */
	public void switchToChildFrame(WebDriver driver,String name) {
		driver.switchTo().frame(name);
	}
	/**
	 * This the Methods which me used to switch back to parent Frame
	 * @param driver
	 */
	public void switchToParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}
	/**
	 * This the Methods which me used to switch back to main page
	 * @param driver
	 */
	public void switchToMainFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	/**
	 * This me methods which is used to switch to the alerts and accept the popup
	 * @param driver
	 */
	public void  acceptAlertPopUps(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	/**
	 * This me methods which is used to switch to the alerts and dismiss the popup
	 * @param driver
	 */
	public void  cancleAlertPopUps(WebDriver driver) {
		driver.switchTo().alert().dismiss();;
	}
	/**
	 * This me methods which is used to switch to the alerts and get the text from the pop up
	 * @param driver
	 */
	public void  getTextFromAlertPopUps(WebDriver driver) {
		driver.switchTo().alert().getText();
	}
	/**
	 * This me methods which is used to switch to the alerts and send data to the pop up
	 * @param driver
	 * @param value
	 */
	public void  enterValueToAlertTextField(WebDriver driver,String value) {
		driver.switchTo().alert().sendKeys(value);
	}
	/**
	 * This methods used when the web elements are not responsive 
	 * @param driver
	 * @return
	 */
	public JavascriptExecutor javaScriptExcutor(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js;
	}
	/**
	 * This is JavaScript Class methods which is use to do scrollHeight
	 * @param driver
	 */
	public void scrollHeight(WebDriver driver) {
		javaScriptExcutor(driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	/**
	 * This is JavaScript Class methods which is use to do scroll Until Element Is Visible
	 * @param driver
	 * @param element
	 */
	//Scroll Till Element
	public void scrollUntillEleIsVisibleUSingJSE(WebDriver driver,WebElement element) {
		javaScriptExcutor(driver).executeScript("arguments[0].scrollIntoView()", element);
	}
	/**
	 * This is JavaScript Class methods which is use to do click On Element Using
	 * @param driver
	 * @param element
	 */
	//click on element 
	public void clickOnElementUsingJSE(WebDriver driver,WebElement element) {
		javaScriptExcutor(driver).executeScript("arguments[0].click()", element);
	}
	/**
	 * This is JavaScript Class methods which is use to enter Text Value
	 * @param driver
	 * @param element
	 */
	//sendKeys
	public void enterTextValueUsingJSE(WebDriver driver,WebElement element) {
		javaScriptExcutor(driver).executeScript("arguments[0].value=arguments[1]", element);
	}
	/**
	 * This is JavaScript Class methods which is use to scroll To Element Using X Y Co-Ordinates
	 * @param driver
	 * @param element
	 */
	public void scrollToEleUsingXYCoOrdinate(WebDriver driver,WebElement element) {
		Point loc = element.getLocation();
		int x=loc.getX();
		int y =loc.getY();
		javaScriptExcutor(driver).executeScript("window.scrollBy("+x+","+y+")");
	}
	
	/**
	 * This is method which is used to take the screenshot for the whole page
	 * @param driver
	 * @param filePath
	 * @return 
	 * @throws IOException
	 */
	public  static String takeScreenshotWholePage(WebDriver driver,String fileName) throws IOException {
		String filepath = ".\\Screenshot\\"+fileName+".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File fsrc = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(filepath);
//		FileHandler.copy(fsrc, dest);
		FileUtils.copyFile(fsrc, dest);
		String path = dest.getAbsolutePath();
		return path;
	}
}
