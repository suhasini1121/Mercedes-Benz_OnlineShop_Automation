/***  
 * End to end test steps:
 * ***** 1.  Open the browser and launch the url
 * ***** 2.  Click on Collection & Accessories
 * ***** 3.  Click on - Model Cars => Classic Models => select the category - 1:18
 * ***** 4.  Verify that the list of products displayed are correct
 * ***** 5.  Click on the product - 190 SL W 121 (1954 - 1963)
 * ***** 6.  Verify that the product name is correct
 * ***** 7.  Click on Add to Basket button
 * ***** 8.  Click on Continue Shopping button to add one more product
 * ***** 9.  Add the product : 300 CE AMG 6.0L (1988) C 124
 * ***** 10. Verify that the product name is correct
 * ***** 11. Click on Add to Basket button
 * ***** 12. Click on Go to shopping basket button
 * ***** 13. Click on Continue to address and delivery button
 * ***** 14. Enter email address
 * ***** 15. Click on Place order as guest button
 * ***** 16. Entered all the required details and the postal code: SP2 0FL
 * ***** 17. Verify that the Payment page is successfully displayed
 ***/

package com.mercedes.benz.tests;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.mercedes.benz.pages.AddItemToCartPage;
import com.mercedes.benz.util.RetryAnalyzer;
import com.relevantcodes.extentreports.LogStatus;

public class EndtoEnd_TC1 extends BaseTest {
	String testCaseName="EndtoEnd_TC1";

	@Test(retryAnalyzer = RetryAnalyzer.class)
	@Parameters({"browser"})
	public void endtoEnd_TC1(String browser) throws Exception {
		test = extent.startTest(testCaseName+"_"+browser+ " - Iteration "+iterator, "Starting the "+testCaseName+" on browser -  "+browser+ " - Iteration "+iterator);
		System.out.println(testCaseName);
		launchApp(browser);

		AddItemToCartPage add = new AddItemToCartPage(driver, test, prop);
		add.searchItem();

		//Waiting for elements to be clickable
		add.waitForElementClickable("allProductsList_118_xpath");
		//Verifying the actual and expected size of the products for 1:18 category under Classic Models
		Assert.assertEquals(add.getActualProductListSize("allProductsList_118_xpath"), add.getExpectedProductList("expSizeItems_css"), "The actual and expected are not equal");
		//Logging to extent reports
		test.log(LogStatus.INFO, "Actual Product list: "+add.getActualProductListSize("allProductsList_118_xpath"));
		test.log(LogStatus.INFO, "Expected Product list: "+add.getExpectedProductList("expSizeItems_css"));
		test.log(LogStatus.PASS, "Actual and Expected list of items are equal");

		add.selectItem("finalProduct1");
		//Verifying the actual and expected product names are equal or not
		Assert.assertEquals(add.getText("producName_css"), prop.getProperty("finalProduct1"), "The product added is not the expected one");
		test.log(LogStatus.INFO, "Actual Product Name: "+add.getText("producName_css"));
		test.log(LogStatus.INFO, "Expected Product Name: "+prop.getProperty("finalProduct1"));
		test.log(LogStatus.PASS, "Actual and Expected product names are equal");

		add.addItemToBasket();

		add.continueShopping();

		// Adding another product to the Basket 
		add.selectItem("finalProduct2");
		//Verifying the actual and expected product names are equal or not
		Assert.assertEquals(add.getText("producName_css"), prop.getProperty("finalProduct2"), "The new product added is not the expected one");
		test.log(LogStatus.INFO, "Actual New Product Name: "+add.getText("producName_css"));
		test.log(LogStatus.INFO, "Expected New Product Name: "+prop.getProperty("finalProduct2"));
		test.log(LogStatus.PASS, "Actual and Expected new product names are equal");

		add.addItemToBasket();

		add.gotoShoppingBasket();
		//Verifying the size of my basket
		Assert.assertEquals(add.getMyBasketSize(), 2, "Size of my basket is not as expected");
		test.log(LogStatus.PASS, "Items in my Basket: "+add.getMyBasketSize());

		add.addAddressToDeliver();
		add.waitForElementClickable("paymentTypePage_xpath");
		//Verifying whether the user is in Payment step
		Assert.assertTrue(add.getWebElement("paymentTypePage_xpath").isDisplayed(), "Not in payment step");
		test.log(LogStatus.PASS, "Payment page is successfully displayed");
	}
}