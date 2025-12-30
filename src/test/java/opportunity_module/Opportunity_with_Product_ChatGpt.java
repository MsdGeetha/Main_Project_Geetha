package opportunity_module;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Opportunity_with_Product_ChatGpt {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // *****************************************
        // 1. LOGIN
        // *****************************************
        driver.get("http://localhost:8888/");
        driver.findElement(By.name("user_name")).sendKeys("admin");
        driver.findElement(By.name("user_password")).sendKeys("admin");
        driver.findElement(By.id("submitButton")).click();

        // *****************************************
        // 2. CREATE PRODUCT
        // *****************************************
        driver.findElement(By.linkText("Products")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@title='Create Product...']"))).click();

        Random ran = new Random();
        int random = ran.nextInt(500);
        String productName = "Laptop" + random;
        String amount = "45000";

        driver.findElement(By.name("productname")).sendKeys(productName);
        driver.findElement(By.id("unit_price")).clear();
        driver.findElement(By.id("unit_price")).sendKeys(amount);

        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

        // Verify product created
        String headerProd = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='lvtHeaderText']")))
                .getText();

        if (headerProd.contains(productName)) {
            System.out.println("✔ Product Created: " + productName);
        } else {
            System.out.println("✘ Product Not Created");
        }

        // *****************************************
        // 3. CREATE OPPORTUNITY
        // *****************************************
        driver.findElement(By.linkText("Opportunities")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@title='Create Opportunity...']")))
                .click();

        String oppName = "Laptop Deal " + random;
        driver.findElement(By.name("potentialname")).sendKeys(oppName);

        driver.findElement(By.name("amount")).clear();
        driver.findElement(By.name("amount")).sendKeys(amount);

        // --------- Select Organization ----------
        String parentWindow = driver.getWindowHandle();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@id='related_to_display']/following-sibling::img"))).click();

        Set<String> allWindows = driver.getWindowHandles();
        for (String win : allWindows) {
            if (!win.equals(parentWindow)) {
                driver.switchTo().window(win);
                break;
            }
        }

        // Select ANY organization (use your existing org name)
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Corp')]"))).click();

        driver.switchTo().window(parentWindow);

        // Save Opportunity
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

        // Verify Opportunity Created
        String headerOpp = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='dvHeaderText']")))
                .getText();

        if (headerOpp.contains(oppName)) {
            System.out.println("✔ Opportunity Created: " + oppName);
        } else {
            System.out.println("✘ Opportunity Not Created");
        }

        // *****************************************
        // 4. ADD PRODUCT TO OPPORTUNITY
        // *****************************************
        WebElement moreTab = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("More Information")));
        new Actions(driver).moveToElement(moreTab).perform();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Products']"))).click();

        // Click Select Product button
        String oppMainWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Select Products']"))).click();

        // Switch to popup
        Set<String> wins2 = driver.getWindowHandles();
        for (String win : wins2) {
            if (!win.equals(oppMainWindow)) {
                driver.switchTo().window(win);
                break;
            }
        }

        // Select our product
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='" + productName + "']"))).click();

        driver.switchTo().window(oppMainWindow);

        // Re-open Products Tab (very important)
        WebElement moreTab2 = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("More Information")));
        new Actions(driver).moveToElement(moreTab2).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Products']"))).click();

        // *****************************************
        // 5. VERIFY PRODUCT IS ADDED
        // *****************************************
        WebElement productRow = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//table//a[normalize-space(text())='" + productName + "']")));

        String actualProduct = productRow.getText();

        System.out.println("Expected Product: " + productName);
        System.out.println("Actual Product:   " + actualProduct);

        if (actualProduct.equals(productName)) {
            System.out.println("✔ Product Successfully Linked to Opportunity");
        } else {
            System.out.println("✘ Product Not Linked");
        }

        // *****************************************
        // 6. SIGN OUT
        // *****************************************
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='themes/softed/images/user.PNG']")))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out"))).click();

        driver.quit();
    }
}
