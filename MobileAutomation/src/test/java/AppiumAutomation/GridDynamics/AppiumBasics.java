package AppiumAutomation.GridDynamics;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AppiumBasics extends TestBase {

	@Test
	public void WifiSettingName() throws MalformedURLException {
	
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		driver.findElement(AppiumBy.xpath("	\r\n"
				+ "//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"WiFi settings\"]")).click();
		String AlertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(AlertTitle, "WiFi settings");
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"android:id/edit\"]")).sendKeys("Tanya");
		driver.findElement(AppiumBy.id("android:id/button1")).click();
		

	}
	
	@Test
	public void LongPress() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
		
		// Long Click Gesture, method is created in base class
		LongPressAction(ele);
		
		// Asserting the variables and its text
		List<WebElement> options = driver.findElements(By.id("android:id/title"));
		Assert.assertEquals((options.get(0).getText()), "Sample menu");
		Assert.assertEquals((options.get(1).getText()), "Sample action");
		
		Thread.sleep(2000);
	}
	
	@Test
	public void ScrollDown() {
		
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));
		
		
	}
	
	@Test
	public void Swipe() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
		WebElement firstImage = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
		Assert.assertEquals(firstImage.getAttribute("focusable"),"true");
		// doing swipe
		swipe(firstImage, "left");
		Assert.assertEquals((driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable")),"false");
		WebElement SecondImage = driver.findElement(By.xpath("(//android.widget.ImageView)[2]"));
		Assert.assertEquals((SecondImage.getAttribute("focusable")),"true");
		
	}
	
	@Test
	public void DragAndDrop() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		WebElement SourceDot = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		//WebElement TargetDot = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_3"));
		
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) SourceDot).getId(),
			    "endX", 820,
			    "endY", 727
			));
		
		Assert.assertEquals("Dropped!", (driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText()));
		
		
	}
	
	@Test
	public void AlertTest() throws InterruptedException {
		
		driver.findElement(AppiumBy.accessibilityId("App")).click();
		driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();
		
		// Testing ok and cancel button from alert modal
		
		driver.findElement(AppiumBy.accessibilityId("OK Cancel dialog with a message")).click();
		
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();

		
	}
	
	@Test
	public void ListBox() throws InterruptedException {
		
		driver.findElement(AppiumBy.accessibilityId("App")).click();
		driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();
		driver.findElement(AppiumBy.accessibilityId("List dialog")).click();
		
		// Testing text from modal
		
		String Heading = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/alertTitle\"]")).getText();
		Assert.assertEquals(Heading, "Header title");
		
		List<WebElement> texts = driver.findElements(By.id("android:id/text1"));
		Assert.assertEquals((texts.get(0).getText()), "Command one");
		Assert.assertEquals((texts.get(1).getText()), "Command two");
		Assert.assertEquals((texts.get(2).getText()), "Command three");
		Assert.assertEquals((texts.get(3).getText()), "Command four");
				

		
	}
	
	@Test
	public void RadioButton() {

		driver.findElement(AppiumBy.accessibilityId("App")).click();
		driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();
		driver.findElement(AppiumBy.accessibilityId("Single choice list")).click();
		
		// To check first option is by default selected
		
		
		List<WebElement> options = driver.findElements(By.id("android:id/text1"));
		
		//boolean Beforestate = options.get(0).isSelected();
		//Assert.assertTrue(Beforestate);
		
		options.get(2).click();
		//boolean AfterState = options.get(0).isSelected();
		//Assert.assertFalse(AfterState);
	}
	
	@Test
	public void MiscActivities() throws InterruptedException {

		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		
		// Turning the device
		DeviceRotation landscape = new DeviceRotation(0, 0, 90);
		driver.rotate(landscape);
		
		driver.findElement(AppiumBy.xpath("	\r\n"
				+ "//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"WiFi settings\"]")).click();
		String AlertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(AlertTitle, "WiFi settings");
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"android:id/edit\"]")).sendKeys("Tanya");
		DeviceRotation portrait = new DeviceRotation(0,0,0);
		driver.rotate(portrait);
		Thread.sleep(5000);
		
		driver.findElement(AppiumBy.id("android:id/button1")).click();
		driver.pressKey(new KeyEvent(Androidkey.))
		
		
		
	}
	
	
}
