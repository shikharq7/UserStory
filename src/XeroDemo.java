package co.edureka.selenium.basics;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.jboss.aerogear.security.otp.Totp;

public class XeroDemo {

	WebDriver driver ;

	/*Either Firefox or Chrome will open based on 
	 * what parameters/arguments the sending from main function*/
public void openBrowser(String Browser , String URL)  
{ 
   if(Browser.equalsIgnoreCase("Firefox"))
     {
	   driver = new FirefoxDriver();
     }
     
   else if(Browser.equalsIgnoreCase("Chrome"))
     {
	   System.setProperty("webdriver.chrome.driver","C:\\Users\\NINDIYA PC\\Downloads\\chromedriver.exe");
	   driver= new ChromeDriver();
     }
   if(URL.isEmpty())
     {
	   URL = "about:blank";
	   System.out.println("URL is missing");
	  }
   driver.manage().window().maximize();   // maximize the window that will open
   driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   driver.get(URL);
}

public void signin()

{
driver.findElement(By.id("email")).clear();
driver.findElement(By.id("email")).sendKeys("shikharq7@gmail.com"); // passing email id

driver.findElement(By.id("password")).clear();
driver.findElement(By.id("password")).sendKeys("abcd1234");     // passing password 

driver.findElement(By.id("submitButton")).click();              // clicking the Login Button

String otpKeyStr = "N53V ESKE IM2H SYSH KJDH OMKE HE";          // <- this 2FA secret key.

Totp totp = new Totp(otpKeyStr);
String twoFactorCode = totp.now();                              // <- got 2FA code at this time!

driver.findElement(By.xpath("//input[@placeholder='Authentication code']")).sendKeys(twoFactorCode); // passing authentication code
driver.findElement(By.xpath("//button[@class='xui-button xui-button-main xui-u-fullwidth']")).click();

driver.findElement(By.xpath("//button[contains(@data-navigation-id,'accounting')]")).click();

driver.findElement(By.xpath("//a[contains(@data-navigation-id,'accounting-bank-accounts')]")).click();

driver.findElement(By.xpath("//span[contains(@data-automationid,'Add Bank Account-button')]")).click();

driver.findElement(By.xpath("//li[contains(text(),'ANZ (AU)')]")).click();

driver.findElement(By.xpath("//input[contains(@id,'accountname-1037-inputEl')]")).sendKeys("SSharma"); // passing account name
WebElement elem = driver.findElement(By.cssSelector("[data-automationid='accountType'] [data-ref] div:nth-of-type(2)"));
elem.click();
Actions builder = new Actions(driver);
Action doubleclickopt = builder.moveToElement(elem).moveByOffset(-10, 20).click().build();
doubleclickopt.perform(); 

driver.findElement(By.xpath("//input[contains(@id,'branchnumber-1045-inputEl')]")).sendKeys("123456"); // Account Name 
driver.findElement(By.xpath("//input[contains(@id,'accountnumber-1046-inputEl')]")).sendKeys("87654321");
driver.findElement(By.xpath("//span[contains(@id,'common-button-submit-1015-btnInnerEl')]")).click();

}
	
/*This function will close the browser window */
public void closebrowser()
{
	if(driver != null)
	driver.quit();
}

public static void main(String[] args) {
		// TODO Auto-generated method stub

		
  XeroDemo driver = new XeroDemo();
  driver.openBrowser("chrome", "https://login.xero.com/");
  driver.signin();
  //driver.closebrowser();
		
		
	}
}



