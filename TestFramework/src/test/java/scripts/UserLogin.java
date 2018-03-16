package scripts;

import org.testng.Assert;

import org.testng.annotations.AfterSuite;


import org.testng.annotations.Test;

import haikujamTesting.haikujam.commonMethods;

import subScripts.Login;

public class UserLogin {

	@Test(priority=0)
	public void userLogin() throws Exception {
		try {
			Login.login("username", "password");
			
		} catch (Exception e) {
			Thread.sleep(3000);

			Assert.fail();
			
		}
		
		
	}
	
	@AfterSuite
	public void sendEmail() throws Exception {
		//commonMethods.mailTheReport("emailable-Report.html");
		commonMethods.mailReport();
	}
}
