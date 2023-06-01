package com.appium.JobTracking1;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Painting extends Base{
	@Test(priority =0)
	public void invalidLogin() throws InterruptedException {
		WebElement username = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]"));
		username.click();
		username.sendKeys("sean");
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
		Thread.sleep(50);
	}
	@Test(priority=1)
	public void validLogin() {
		WebElement username = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]"));
		username.click();
		username.sendKeys("sean");
		driver.navigate().back();
		WebElement password = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]"));
		password.click();
		password.sendKeys("password");
		driver.navigate().back();
		WebElement login = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Log In\"]"));
		login.click();
		String userName = driver.findElement(By.xpath("//android.view.View[@content-desc=\"sean\"]")).getAttribute("content-desc");
		Assert.assertEquals(userName, "sean");
	}
	@Test(priority=2)
	public void alreadyCompletedBarcode() {
		WebElement scanner = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[4]"));
		scanner.click();
		driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
		performTap();// to type barcode number
		WebElement barCode = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText"));
		barCode.click();
		barCode.sendKeys("183-1978-1-3562");
		driver.navigate().back();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"FETCH\"]")).click();
		WebElement alert = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View"));
		boolean completedAlert = alert.isEnabled();
		Assert.assertEquals(completedAlert, true);
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"Close\"]")).click();
	}
	@Test(priority=3)
	public void assignedindividualBarcode() {
		WebElement scanner = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[4]"));
		scanner.click();
		performTap();// to type barcode number
		WebElement barCode = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText"));
		barCode.click();
		barCode.sendKeys("187-2170-1-3899");
		driver.navigate().back();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"FETCH\"]")).click();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"CONFIRM\"]")).click();
		driver.findElement(By.xpath("//android.widget.CheckBox[@content-desc=\"Check 1\"]")).click();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"SUBMIT\"]")).click();
		WebElement successMsg =driver.findElement(By.xpath("//android.view.View[@content-desc=\"OK\"]"));
		boolean completedMsg=successMsg.isEnabled();
		Assert.assertEquals(completedMsg, true);
		successMsg.click();
	}
	@Test(priority=4)
	public void assignedCommonBarcode() {
		WebElement scanner = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[4]"));
		scanner.click();
		performTap();// to type barcode number
		WebElement barCode = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText"));
		barCode.click();
		barCode.sendKeys("187-2168-2-3896");
		driver.navigate().back();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"FETCH\"]")).click();
		WebElement quantity = driver.findElement(By.xpath("//android.view.View[@content-desc=\"How Many quantity you have done?\"]/android.widget.EditText"));
		quantity.click();
		quantity.sendKeys("2");
		driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Confirm\"]")).click();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"CONFIRM\"]")).click();
		driver.findElement(By.xpath("//android.widget.CheckBox[@content-desc=\"Check 1\"]")).click();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"SUBMIT\"]")).click();
		WebElement successMsg =driver.findElement(By.xpath("//android.view.View[@content-desc=\"OK\"]"));
		boolean completedMsg=successMsg.isEnabled();
		Assert.assertEquals(completedMsg, true);
		successMsg.click();
	}
	@Test(priority=5)
	public void notCompletedPreviousLevel() {
		WebElement scanner = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[4]"));
		scanner.click();
		performTap();// to type barcode number
		WebElement barCode = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText"));
		barCode.click();
		barCode.sendKeys("187-2170-1-3905");
		driver.navigate().back();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"FETCH\"]")).click();
		WebElement alert = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View"));
		boolean notCompletedAlert = alert.isEnabled();
		Assert.assertEquals(notCompletedAlert, true);
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"Close\"]")).click();
	}


}
