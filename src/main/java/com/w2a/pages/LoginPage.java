package com.w2a.pages;

import org.testng.Assert;

import com.w2a.base.Page;

public class LoginPage extends Page{
	
	public OMLDirectoryPage doLogin(String username,String password) {
		
		type("username_ID", username);
		type("password_XPATH", password);
		click("loginSubmitBtn_CSS");
		Assert.assertTrue(isElementPresent("firstName_XPATH"),"OML member not logged in");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new OMLDirectoryPage();
	}

}
