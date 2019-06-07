package com.mercedes.benz.tests;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import com.mercedes.benz.util.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {

	public WebDriver driver;
	public Properties prop;

	public ExtentReports extent = ExtentManager.getInstance();
	public ExtentTest test;

	public static int iterator = 1;
	// Launching the Application

	public void launchApp(String browser) {

		if(prop ==null) {
			prop = new Properties();
			String path = System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties";
			try {
				FileInputStream fis = new FileInputStream(path);
				prop.load(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//Opening the browser and launching application
		if(prop.getProperty("os").equalsIgnoreCase("windows")) {
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Browser Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				test.log(LogStatus.INFO, "Launched the browser: "+browser);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			}else if(browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\Browser Drivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				test.log(LogStatus.INFO, "Launched the browser: "+browser);
			}else if(browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Browser Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				test.log(LogStatus.INFO, "Launched the browser: "+browser);
			}
		}else if(prop.getProperty("os").equalsIgnoreCase("mac")) {
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Browser Drivers/chromedriver");
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				test.log(LogStatus.INFO, "Launched the browser: "+browser);
			}
		}


		try {
			test.log(LogStatus.INFO, "Launching the Mercedes-Benz Online Web Application: "+prop.getProperty("url"));
			driver.get(prop.getProperty("url"));
			if(driver.findElement(By.linkText("Close")).isDisplayed()) {
				driver.findElement(By.linkText("Close")).click();
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Application did not launch "+ e.getMessage());
			e.printStackTrace();
			Assert.fail("Application did not launch"+ e.getMessage());
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void quit(){
		if(extent!=null){
			iterator++;
			extent.endTest(test);
			extent.flush();
		}
		if(driver!=null)
			driver.quit();
	}
}