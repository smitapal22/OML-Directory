package com.w2a.pages;

import com.w2a.base.Page;

public class OMLDirectoryPage extends Page{

	public void goToFirstname() {
		
		type("firstName_XPATH", "kay");
		click("submitOmlDrirectory_XPATH");
		click("clear_XPATH");
		
	}
	
	public void goToLastName() {
		type("lastName_XPATH", "Joseph");
		click("submitOmlDrirectory_XPATH");
		click("clear_XPATH");
	}
		
	public void goToTitle() {
		
		select("title_XPATH", "Accountant");
		click("submitOmlDrirectory_XPATH");
		click("clear_XPATH");
	}
	
	public void goToMemberType() {
		
		select("memberTypeCode_XPATH", "Cities");
		click("submitOmlDrirectory_XPATH");
		click("clear_XPATH");
		
	}
	
	public void goToOmlDistrict() {
		
		select("oml_District_XPATH", "2");
		click("submitOmlDrirectory_XPATH");
		click("clear_XPATH");
		
	}
	
	public void goToStateSenateDistrict() {
		
		select("stateStateDistrict_XPATH", "10");
		click("submitOmlDrirectory_XPATH");
		click("clear_XPATH");
		
	}
	
	public void goToStateHouseDistrict() {
		
		select("stateHouseDistrict_XPATH", "11");
		click("submitOmlDrirectory_XPATH");
		click("clear_XPATH");
		
	}
	
	public void goToCityOrTown() {
		
		select("city_XPATH", "Adair");
		click("submitOmlDrirectory_XPATH");
		click("clear_XPATH");
	}
	
	public void goToLogout() {
		
		click("welcomeEmpoweredMarginsBtn_XPATH");
		click("logout_XPATH");
	}
	
	}


