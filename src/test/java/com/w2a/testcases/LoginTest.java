package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.pages.LoginPage;
import com.w2a.pages.OMLDirectoryPage;
import com.w2a.utitilities.Utilities;

public class LoginTest {
	
	
	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void loginTest(Hashtable<String,String> data) throws InterruptedException{
		
		LoginPage lp= new LoginPage();
		
		lp.doLogin(data.get("username"), data.get("password"));
	//	Assert.assertTrue(alert.getText().contains("Customer added successfully"),"Customer not added successfully");
		
	//	Thread.sleep(2000);
		//Assert.fail("Add Customer Test failed");
		
		//Page.click("loginSubmitBtn_XPATH");
		/*Assert.assertTrue(Page.isElementPresent("firstName_XPATH"),"OML member not logged in");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		//Assert.fail("OML Login Test failed");
	
	OMLDirectoryPage oml = new OMLDirectoryPage();
		oml.goToMemberType();
		oml.goToLogout();
		
		//Assert.fail("Login test failed");
		
		//HomePage home = new HomePage();
		//LoginPage lp = home.goToLogin();
		//lp.doLogin(data.get("username"), data.get("password"));
		//Assert.fail("Login test failed");
		//<listener class-name="com.w2a.errorcollectors.TestListenerAdapter" />
	
	}
	} 
	


