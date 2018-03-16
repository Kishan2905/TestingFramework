package subScripts;


import org.testng.Reporter;
import haikujamTesting.haikujam.AppiumDriverBase;
import haikujamTesting.haikujam.commonMethods;
import pageObjects.AppConstants;
import pageObjects.LoginObjects;


public class Login extends AppiumDriverBase implements LoginObjects,AppConstants{
	
		public static void login(String emailId,String Password) throws InterruptedException {
	

		    commonMethods.locateElement(emailLogin).click();
		
		    Reporter.log("App launched");
		   
		    commonMethods.locateElement(login).click();
		
		    commonMethods.locateElement(txtbxEmail).sendKeys(emailId);
		    
		    commonMethods.locateElement(pass).sendKeys(Password);
		   
		    Reporter.log("Credentials Provided");
		
		    commonMethods.locateElement(signIn_btn).click();
    
    
}


}
