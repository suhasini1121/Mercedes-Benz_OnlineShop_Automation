package com.mercedes.benz.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BasePage {
	public WebDriver driver;
	public ExtentTest test;
	public Properties prop;

	public BasePage(WebDriver driver,ExtentTest test, Properties prop) {
		this.driver = driver;
		this.test=test;
		this.prop=prop;
	}

	public void click(String eleLocator) {
		getWebElement(eleLocator).click();
	}

	public void type(String eleLocator, String data) {
		getWebElement(eleLocator).sendKeys(prop.getProperty(data));
	}

	public void clear(String eleLocator) {
		getWebElement(eleLocator).clear();
	}

	public void scrollToEnd() throws Exception {
		((JavascriptExecutor) driver)
		.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollToElement(String eleLocator) throws Exception {
		WebElement element = getWebElement(eleLocator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
	}

	public String getText(String eleLocator) throws Exception {

		try {
			String text = getWebElement(eleLocator).getText();
			return text;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	public boolean verifyText(String eleLocator, String expectedText) {
		Boolean text = null;
		if(getWebElement(eleLocator).getText().equals(expectedText)) {
			text = true;
		}else {
			text = false;
		}

		return text;
	}

	public List<String> getAllDropDownValues(String eleLocator){

		List<String> values = new ArrayList<String>();
		List<WebElement> allDDElements = new Select(getWebElement(eleLocator)).getOptions();

		for(WebElement menuitem:allDDElements) {
			values.add(menuitem.getText());
		}
		return values;
	}

	public void selectDDByVisibleText(String eleLocator, String text){
		Select dd = new Select(getWebElement(eleLocator));
		dd.selectByVisibleText(prop.getProperty(text));
	}

	public int getActualProductListSize(String eleLocator){
		List<WebElement> productsList = driver.findElements(By.xpath(prop.getProperty(eleLocator)));
		int size = productsList.size();
		System.out.println("Actual product size: "+size);
		return size;
	}

	public void clickWebTableData(String eleLocator, String product) throws Exception{
		List <WebElement> productsList = driver.findElements(By.xpath(prop.getProperty(eleLocator)));
		try {
			for(WebElement e : productsList){
				System.out.println("Actual Product: "+e.getText());
				if(e.getText().contains(prop.getProperty(product))) {
					waitForElementClickable(eleLocator);
					e.click();
					break;
				}
			}
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed the action"+ e.getMessage());
			e.printStackTrace();
			Assert.fail("Failed the action"+ e.getMessage());
		}
	}

	public void waitForElementClickable(String eleLocator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		if(eleLocator.endsWith("_xpath")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(eleLocator))));
		}else if(eleLocator.endsWith("_css")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(prop.getProperty(eleLocator))));
		}else if(eleLocator.endsWith("id")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty(eleLocator))));
		}else if(eleLocator.endsWith("name")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.name(prop.getProperty(eleLocator))));
		}else if(eleLocator.endsWith("linktext")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(prop.getProperty(eleLocator))));
		}

	}

	public WebElement getWebElement(String eleLocator) {
		WebElement element = null;
		if(eleLocator.endsWith("_xpath")) {
			element = driver.findElement(By.xpath(prop.getProperty(eleLocator)));
		}else if(eleLocator.endsWith("_css")) {
			element = driver.findElement(By.cssSelector(prop.getProperty(eleLocator)));
		}else if(eleLocator.endsWith("id")) {
			element = driver.findElement(By.id(prop.getProperty(eleLocator)));
		}else if(eleLocator.endsWith("name")) {
			element = driver.findElement(By.name(prop.getProperty(eleLocator)));
		}else if(eleLocator.endsWith("linktext")) {
			element = driver.findElement(By.linkText(prop.getProperty(eleLocator)));
		}

		return element;
	}

}
