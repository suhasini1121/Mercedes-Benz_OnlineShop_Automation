package com.mercedes.benz.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AddItemToCartPage extends BasePage{
	public List<String> allClassicModel_118 = new ArrayList<String>();
	public AddItemToCartPage(WebDriver driver, ExtentTest test, Properties prop) {
		super(driver, test, prop);
	}

	public void searchItem() {
		try {
			click("collectionAndAccessories_linktext");
			test.log(LogStatus.INFO, "Clicked on - Collection & accessories");
			click("modelCars_linktext");
			test.log(LogStatus.INFO, "Clicked on - Model Cars");
			click("classicModels_linktext");
			test.log(LogStatus.INFO, "Clicked on - Classic Models");
			click("category_linktext");
			test.log(LogStatus.INFO, "Clicked on category - 1:18");
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed the action on click"+ e.getMessage());
			e.printStackTrace();
			Assert.fail("Failed the action on click"+ e.getMessage());
		}
	}

	public void selectItem(String finalProduct) throws Exception {
		waitForElementClickable("allProductsList_118_xpath");
		clickWebTableData("allProductsList_118_xpath", finalProduct);
	}

	public void addItemToBasket() {
		try {
			click("addToBasketButton_css");
			test.log(LogStatus.INFO, "Clicked on Add to Basket button");
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to click on the Add to Basket button"+ e.getMessage());
			e.printStackTrace();
			Assert.fail("Failed to click on the Add to Basket button"+ e.getMessage());
		}
	}

	public void continueShopping() {
		waitForElementClickable("continueShoppingButton_css");
		try {
			click("continueShoppingButton_css");
			test.log(LogStatus.INFO, "Clicked on Continue Shopping button");
			test.log(LogStatus.INFO, "*********** Now Adding another item to Basket ***********");
			searchItem();
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed"+ e.getMessage());
			e.printStackTrace();
			Assert.fail("Failed"+ e.getMessage());
		}
	}

	public int getExpectedProductList(String eleLocator) throws Exception {
		int expectedSize = 0;
		String text = getText(eleLocator);
		String[] value = text.split("[\\sa-zA-Z()]");
		try {
			for(String s:value) {
				System.out.println(s);
				if(!s.equals("")) {
					expectedSize = Integer.parseInt(s);
				}
			}
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed"+ e.getMessage());
			e.printStackTrace();
			Assert.fail("Failed"+ e.getMessage());
		}
		return expectedSize;
	}

	public void gotoShoppingBasket() {
		waitForElementClickable("gotoShoppingBasketButton_css");
		try {
			click("gotoShoppingBasketButton_css");
			test.log(LogStatus.INFO, "Clicked on Go to shopping basket button");
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed"+ e.getMessage());
			e.printStackTrace();
			Assert.fail("Failed"+ e.getMessage());
		}
	}

	public int getMyBasketSize() throws Exception {
		waitForElementClickable("itemsInBasket_css");
		try {
			List<WebElement> myBasketList = driver.findElements(By.cssSelector(prop.getProperty("itemsInBasket_css")));
			System.out.println("My basket: "+myBasketList.size());
			return myBasketList.size();
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed"+ e.getMessage());
			e.printStackTrace();
			Assert.fail("Failed"+ e.getMessage());
			return 0;
		}

	}

	public void addAddressToDeliver() throws Exception {
		try {
			click("continueToAddressAndDeliveryButton_css");
			test.log(LogStatus.INFO, "Continue to address and delivery button");
			type("emailAddress_css", "emailAddress");
			test.log(LogStatus.INFO, "Entered email address: "+ prop.getProperty("emailAddress"));
			click("placeOrderAsGuestButton_xpath");
			test.log(LogStatus.INFO, "Clicked on Place order as guest button");
			click("salutation_xpath");
			type("firstName_css", "firstName");
			type("lastName_css", "lastName");
			type("streetNo_css", "streetNumber");
			type("streetName_css", "streetName");
			type("city_css", "city");
			type("postalCode_css", "postalCode");
			test.log(LogStatus.INFO, "Entered the postal code: "+prop.getProperty("postalCode"));
			scrollToElement("continuePaymentTypeButton_css");
			click("continuePaymentTypeButton_css");
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed"+ e.getMessage());
			e.printStackTrace();
			Assert.fail("Failed"+ e.getMessage());
		}
	}
}
