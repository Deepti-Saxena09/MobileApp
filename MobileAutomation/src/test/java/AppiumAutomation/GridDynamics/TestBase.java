package AppiumAutomation.GridDynamics;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class TestBase {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void cofigureAppium() throws MalformedURLException {
		
		// Create the android driver instance. The first argument is for sever.
		// Capabilties are next argument
		
		//Code to start the appium serverye
		
		service =	new AppiumServiceBuilder().withAppiumJS(new File("C://Users//Prakarsh gupta//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
				
		service.start();
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Depti Pixel 2 XL API 34");
		options.setApp("C://Users//Prakarsh gupta//eclipse-workspace//GridDynamics//src//test//java//resources//ApiDemos-debug.apk");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void LongPressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId()
			));
	}
	
	public void swipe(WebElement ele, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",((RemoteWebElement) ele).getId(),"direction",direction, "percent",0.1));
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
