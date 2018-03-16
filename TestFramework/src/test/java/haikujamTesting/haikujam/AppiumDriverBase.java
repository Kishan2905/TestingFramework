package haikujamTesting.haikujam;
 
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AppiumCommandExecutor;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.internal.ApacheHttpClient;
import org.openqa.selenium.remote.internal.HttpClientFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
 
public class AppiumDriverBase {
 
   // protected AppiumDriver<WebElement> driver;
	protected static AppiumDriver driver;
    //protected UiAutomator driver;
  
    protected WebDriverWait wait;
 
    //before Test Annotation makes a java function to run every time before a TestNG test case
    @BeforeTest
 
    public void setUp() throws MalformedURLException, InterruptedException {
 
    //relative path to apk file
    final File classpathRoot = new File(System.getProperty("user.dir"));
    final File appDir = new File(classpathRoot, "src/test/resources/apps/");
    final File app = new File(appDir, "something.apk");
 
    //setting up desired capability
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("replace", true);
    caps.setCapability("browserName", "");
    caps.setCapability("platform", "ANDROID");
    caps.setCapability("platformVersion", "6.0");
    caps.setCapability("deviceName", "ANDROID");
    caps.setCapability("app", app.getAbsolutePath());
    caps.setCapability("resetKeyboard", true);
    caps.setCapability("unicodeKeyboard", false);
    caps.setCapability("noReset", false);
    
    driver=new AppiumDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }
 
    //After Test Annotation makes a java function to run every time after a TestNG test case
    @AfterTest
    public void afterTest(){
 
    //quit the driver
    	driver.quit();
    }
 
}