package com.mercedes.benz.pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class EditAccountPage extends BasePage{
	public EditAccountPage(WebDriver driver, ExtentTest test, Properties prop) {
		super(driver, test, prop);
	}
	public void changePassword() {
		test.log(LogStatus.INFO, "Clicking on user account to change the password"+prop.getProperty("userName"));
		click("editAccount_css");
		// changing the password
		type("oldPwd_css", "password");
		type("newPwd_css", "newpassword");
		type("newPwdRepeat_css", "newpassword");
		click("updateAccount_css");
		test.log(LogStatus.PASS, "Successfully changed the password from: "+prop.getProperty("password")+" to: "+prop.getProperty("newpassword"));
	}
	
	public String getUserAccount() throws Exception {
		String str = getText("editAccount_css");
		System.out.println("User Account: "+str);
		return str;
	}
}
