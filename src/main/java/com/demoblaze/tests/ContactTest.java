package com.demoblaze.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.demoblaze.pages.HomePage;
import com.demoblaze.resource.Listners;
import com.demoblaze.resource.TestBase;
import com.demoblaze.utilities.WaitUtil;
import com.demoblaze.utilities.commonUtil;

public class ContactTest extends TestBase {
	WaitUtil wait;
	HomePage home;
	commonUtil common;
	public static Logger log = LogManager.getLogger(ContactTest.class.getName());

	WebDriver driverlocal;

	@BeforeTest(alwaysRun=true)
	public void setConfig() {
		launchBrowser();
		driverlocal = getDriver();
		home = new HomePage(driverlocal);
		wait = new WaitUtil(driverlocal);
		common = new commonUtil(driverlocal);
	}

	@Test(groups = { "regression" })
	public void sendMessageTest() {
		// Test case to see if user can send a message to demoBlaze
		Listners.getReporter().log(Status.INFO, "if user can send a message to demoBlaze");
		home.getContactLink().click();
		log.info("accessing contact");
		wait.waituntillElementIsVisible(home.getNewMessageText(), 5);// wait till message window opens
		log.debug(" message window has opened");
		Listners.getReporter().log(Status.PASS, "successfully accessed message window");
		log.info("filling message details");
		home.fillMessageDetails();
		home.getSendMessageButton().click();
		Alert sendMessageAlert = common.switchToAlert(); // switch to alert
		String alertText = sendMessageAlert.getText();
		Listners.getReporter().log(Status.PASS, "message was sent. Alert pop up is displayed");
		log.debug("alert opened with text: " + alertText);
		// verify alert text
		Assert.assertEquals(alertText, "Thanks for the message!!", "the alert text does not match expected");
		Listners.getReporter().log(Status.PASS, "the alert text matches expected");
		log.debug("the alert text matches expected");
		// accept and close alert
		sendMessageAlert.accept();
		log.debug("alert accepted");
	}

	@AfterTest(alwaysRun=true)
	public void testTearDown() {
		driverlocal.close();
	}
}
