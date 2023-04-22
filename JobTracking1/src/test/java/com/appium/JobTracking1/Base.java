package com.appium.JobTracking1;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class Base {
public AppiumDriver driver;
	
	@BeforeTest
	public void start() throws MalformedURLException {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "11");
		capabilities.setCapability("udid", "7T49VGSC9LB6ZTVG");
		capabilities.setCapability("deviceName", "OPPO A16e");
		capabilities.setCapability("appPackage", "com.example.jobtracking");
		capabilities.setCapability("appActivity", "com.example.jobtracking.MainActivity");
		capabilities.setCapability("ignoreHiddenApiPolicyError", true);
		
		URL url= new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AppiumDriver(url,capabilities);

	}
	
	public void performScroll(){
		Dimension size = driver.manage().window().getSize();
		int StartX = size.getWidth()/2;
		int EndX =size.getWidth()/2;
		int StartY = size.getHeight()/2;
		int EndY = (int) (size.getHeight()*0.35);
		scrollSequence(StartX, StartY, EndX, EndY);
	}
	
	public void performTap() {
		Dimension size = driver.manage().window().getSize();
		int XAxis = size.getWidth()/2;
		int YAxis = (int) (size.getHeight()*0.876);
		tap(XAxis,YAxis);
	}
	
	public void scrollSequence(int StartX, int StartY, int EndX, int EndY)
	{
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	    Sequence sequence = new Sequence(finger, 1);
	    sequence.addAction(finger.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(), StartX, StartY ));
	    sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
	    sequence.addAction(finger.createPointerMove(Duration.ofMillis(300),PointerInput.Origin.viewport(), EndX, EndY ));
	    sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
	    driver.perform(Collections.singletonList(sequence));
	}
	
	public void tap(int XAxis, int YAxis) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence sequence = new Sequence(finger, 0);
		sequence.addAction(finger.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(), XAxis ,YAxis ));
		sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
		sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
		driver.perform(Collections.singletonList(sequence));
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	
	}
}
