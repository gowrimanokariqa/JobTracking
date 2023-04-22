package com.appium.JobTracking1;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Shipment extends Base{
	ExtentReports extent;

	@BeforeSuite
	public void extentReport()
	{
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/index.html");
		extent.attachReporter(spark);
	}

	@Test(priority=0)
	public void invalidLogin() {
		WebElement username = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]"));
		username.click();
		username.sendKeys("matt");
		driver.navigate().back();
		WebElement password = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]"));
		password.click();
		password.sendKeys("passwo");
		driver.navigate().back();
		WebElement login = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Log In\"]"));
		login.click();
		WebElement toast = driver.findElement(By.xpath("(//android.widget.Toast)[1]"));
		String toastMsg = toast.getAttribute("text");
		Assert.assertEquals(toastMsg, "Password Not Match");

		ExtentTest invalidLogin = extent.createTest("Verify user able to login with invalid credentials").assignAuthor("Gowri");
		invalidLogin.pass("Can not able to Login");
		if(toastMsg.isEmpty()) {
			invalidLogin.fail("Test case failed");
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOf(toast));
	}
	@Test(priority=1)
	public void validLogin() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebElement username = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]"));
		username.click();
		username.sendKeys("matt");
		driver.navigate().back();
		WebElement password = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]"));
		password.click();
		password.sendKeys("password");
		driver.navigate().back();
		WebElement login = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Log In\"]"));
		login.click();
		String screenName = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Shipment\"]")).getAttribute("content-desc");
		Assert.assertEquals(screenName, "Shipment");

		ExtentTest validLogin = extent.createTest("Verify user able to login with valid credentials").assignAuthor("Gowri");
		validLogin.pass("Sucessfull Login");
		if(screenName.isEmpty()){
			validLogin.fail("Doesnt navigated to Shipment screen");
		}
	}

	@Test(priority=2)
	public void shipmentSelectionByEmpty() {

		WebElement process = driver.findElement(By.xpath("//android.view.View[@content-desc=\"PROCESS\"]"));
		process.click();
		boolean Alert = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View")).isEnabled();
		Assert.assertEquals(Alert, true);

		ExtentTest emptySelection = extent.createTest("Verify without selecting the job shipment team can process").assignAuthor("Gowri");
		if(Alert==true) {
			emptySelection.pass("Shipment team can not able to prcess");
		}else {
			emptySelection.fail("Alert pop-up not enabled");
		}
		driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Close\"]")).click();
	}

	@Test(priority=3)
	public void validShipmentSelection() {
		WebElement chooseJob = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Choose Job\"]"));
		chooseJob.click();

		//Scroll
		performScroll();

		WebElement selectJob = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Test_New( New_123 )\"]"));
		selectJob.click();
		WebElement chooseVehicle = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Choose Vehicle\"]"));
		chooseVehicle.click();
		WebElement selectVehicle = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Bonnie( 91915-2005 )\"]"));
		selectVehicle.click();
		WebElement chooseDate = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Choose Date\"]"));
		chooseDate.click();
		WebElement selectDate = driver.findElement(By.xpath("//android.view.View[@content-desc=\"30, Sunday, April 30, 2023\"]"));
		selectDate.click();
		driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"OK\"]")).click();
		WebElement process = driver.findElement(By.xpath("//android.view.View[@content-desc=\"PROCESS\"]"));
		process.click();
		WebElement addShipmentScreen = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Add Shipment\"]"));
		boolean newScreen = addShipmentScreen.isEnabled();
		Assert.assertEquals(newScreen, true);

		ExtentTest validSelection = extent.createTest("Verify user can able to add shipment details").assignAuthor("Gowri");
		if (addShipmentScreen.isEnabled()) {
			validSelection.pass("Shipment details added");
		}else {
			validSelection.fail("Can not able to add shipment details");
		}	
	}

	@Test(priority=4)
	public void viewFunctionofJob() {
		WebElement viewJob = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Test_New\"]"));
		viewJob.click();
		WebElement jobDetails =driver.findElement(By.xpath("//android.view.View[@content-desc=\"Job Details\"]"));
		String viewPopup= jobDetails.getAttribute("content-desc");	
		Assert.assertEquals(viewPopup, "Job Details");

		ExtentTest viewFunction = extent.createTest("Verify view function of Job and Vehicle").assignAuthor("Gowri");
		if (jobDetails.isEnabled()) {
			viewFunction.pass("Details can be viewed");
		}else {
			viewFunction.fail("Can not able to view the details");
		}
		driver.navigate().back();
	}

	@Test(priority=5)
	public void emptyCart() {
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"CONFIRM\"]")).click();
		WebElement alert = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View"));
		boolean emptyCartAlert = alert.isEnabled();
		Assert.assertEquals(emptyCartAlert, true);

		ExtentTest viewFunction = extent.createTest("Verify system allows the empty cart").assignAuthor("Gowri");
		if(alert.isDisplayed()){
			viewFunction.pass("System shows the alert");
		}else {
			viewFunction.fail("System allows the empty cart");
		}
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"Close\"]")).click();
	}

	@Test(priority=6)
	public void addShipmentOfAlreadyAdded() {
		driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView")).click();
		driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
		
		//tap on keyboard
		performTap();
		
		WebElement barCode = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText"));
		barCode.click();
		barCode.sendKeys("183-1978-1-3562");
		driver.navigate().back();

		driver.findElement(By.xpath("//android.view.View[@content-desc=\"FETCH\"]")).click();
		WebElement alert2 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View"));
		boolean completedAlert = alert2.isEnabled();
		Assert.assertEquals(completedAlert, true);

		ExtentTest completeAlert = extent.createTest("Verify system allows the empty cart").assignAuthor("Gowri");
		if(alert2.isEnabled()) {
			completeAlert.pass("System shows the alert");
		}else {
		completeAlert.fail("System doesnt show the alert");
		}
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"Close\"]")).click();
	}

	@Test(priority=7)
	public void invalidBarcode() {
		//tap on keyboard
		performTap();

		//Invalid Barcode
		WebElement shipBarcode = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText"));
		shipBarcode.click();
		shipBarcode.sendKeys("183-6");
		driver.navigate().back();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"FETCH\"]")).click();
		WebElement alert3 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View"));
		boolean invalidBarcodeAlert = alert3.isEnabled();
		Assert.assertEquals(invalidBarcodeAlert,true);
		
		ExtentTest invalidBarcode = extent.createTest("Verify Shipment function").assignAuthor("Gowri");
		if(alert3.isEnabled()) {
			invalidBarcode.pass("Invalid Barcode alert has displayed");
		}else {
			invalidBarcode.fail("Accept the invalid barcode");
		}		
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"Close\"]")).click();
	}


	@Test(priority=8)
	public void overCapacityItem() {
		//tap on keyboard
		performTap();

		WebElement shipBarcode = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText"));
		shipBarcode.click();
		shipBarcode.sendKeys("183-1979-2-3582");
		driver.navigate().back();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"FETCH\"]")).click();

		WebElement quantity =driver.findElement(By.xpath("//android.view.View[@content-desc=\"How Many quantity you have done?\"]/android.widget.EditText"));
		quantity.click();
		quantity.sendKeys("15");

		driver.findElement(By.xpath("//android.view.View[@content-desc=\"How Many quantity you have done?\"]/android.view.View[1]")).click();
		System.out.println("Item Quantiry has reduced");
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"How Many quantity you have done?\"]/android.view.View[2]")).click();
		System.out.println("Item Quantiry has increased");
		driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Confirm\"]")).click();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"YES\"]")).click();

		WebElement alert4 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View"));
		boolean overCapacityAlert =alert4.isEnabled();
		Assert.assertEquals(overCapacityAlert, true);
	
		ExtentTest completeShipment = extent.createTest("Verify Shipment function").assignAuthor("Gowri");
		if(alert4.isDisplayed()) {
			completeShipment.pass("Item has shiped");
		}else {
		completeShipment.fail("Not able to ship the item");
		}
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"Close\"]")).click();
	}

	@Test(priority=9)
	public void deleteItemFromCart() {
		WebElement deleteIcon = driver.findElement(By.xpath("//android.widget.ImageView[@bounds='[570,675][642,711]']"));
		deleteIcon.click();
		WebElement deleteQuantity = driver.findElement(By.xpath("//android.widget.EditText[@bounds='[250,736][470,850]']"));
		deleteQuantity.click();
		deleteQuantity.sendKeys("14");
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"Confirm\"]")).click();
		
		//wait for toast to arrive
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		WebElement toast1 =driver.findElement(By.xpath("(//android.widget.Toast)[1]"));
		String deleteToast = toast1.getAttribute("text");
		
		Assert.assertEquals(deleteToast, "Selected Item Deleted successfully");
		
		ExtentTest deleteItem = extent.createTest("Verify Shipment function").assignAuthor("Gowri");
		if(toast1.isEnabled()) {
			deleteItem.pass("Item has deleted");
		}else {
			deleteItem.fail("Not able to delete the item");
		}
	}
	
	@Test(priority=10)
	public void previousLevelNotCompleted() {
		driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView")).click();

		//tap on keyboard
		performTap();

		WebElement shipBarcode = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText"));
		shipBarcode.click();
		shipBarcode.sendKeys("183-1978-1-3570"); 
		driver.navigate().back();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"FETCH\"]")).click();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"YES\"]")).click();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"CONFIRM\"]")).click();
		WebElement alert5 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View"));
		boolean levelNotCompleted= alert5.isDisplayed();
		
		Assert.assertEquals(levelNotCompleted, true);

		ExtentTest completeShipment = extent.createTest("Verify shipping the not completed Barcode").assignAuthor("Gowri");
		if(alert5.isEnabled()) {
		completeShipment.pass("Alert should open as previous level not completed");
		}else {
		completeShipment.fail("Alert not enabled");
		}
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"Close\"]")).click();
	}

	@Test(priority=10)
	public void addShipmentOfNewOne() {
		driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView")).click();

		//tap on keyboard
		performTap();

		WebElement shipBarcode = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText"));
		shipBarcode.click();
		shipBarcode.sendKeys("183-1978-1-3571"); 
		driver.navigate().back();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"FETCH\"]")).click();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"YES\"]")).click();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"CONFIRM\"]")).click();
		WebElement alert5 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View"));
		boolean sucessMessage= alert5.isDisplayed();
		
		Assert.assertEquals(sucessMessage, true);

		ExtentTest completeShipment = extent.createTest("Verify Shipment function").assignAuthor("Gowri");
		if(alert5.isEnabled()) {
		completeShipment.pass("Item has shiped");
		}else {
		completeShipment.fail("Not able to ship the item");
		}
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"OK\"]")).click();
	}

	@AfterSuite
	public void close() {
		extent.flush();	
	}
}
