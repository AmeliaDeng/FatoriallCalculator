package util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserHelper {

	public static WebDriver getDriver(String browser, String url) {
		
		WebDriver driver = null;
		if(browser.equals("chrome")) {
			
			WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
		  
		}else if(browser.equals("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}else if(browser.equals("edge")) {
			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		}else {
			Assert.assertTrue(false, "No browser match!");
		}
		// Implicit wait once applied lasts for the whole session, this means till the time your IWebDriver object is alive
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
	    return driver;

	      
	}

}
