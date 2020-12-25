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
import com.demoblaze.resource.MyConfig;
import com.demoblaze.resource.TestBase;
import com.demoblaze.utilities.WaitUtil;
import com.demoblaze.utilities.commonUtil;;

public class AuthenticationTest extends TestBase {
	WaitUtil wait;
	HomePage home;
	commonUtil common;
	public static Logger log = LogManager.getLogger(AuthenticationTest.class.getName());
	WebDriver driverlocal;

	@BeforeTest(alwaysRun=true)
	public void setConfig() {
		launchBrowser();
		driverlocal = getDriver();
		home = new HomePage(driverlocal);
		wait = new WaitUtil(driverlocal);
		common = new commonUtil(driverlocal);
	}

	@Test(groups = { "sanity", "regression" }, priority = 0)
	public void userLoginTest() {
		// test case to see if user is able to login
		Listners.getReporter().log(Status.INFO, "verifying if user is able to login");
		String username = MyConfig.getBundle().get("demoblaze.login.username");
		String password = MyConfig.getBundle().get("demoblaze.login.password");
		log.info("Loging in as user " + username);
		home.getLoginLink().click();
		wait.waituntillElementIsVisible(home.getLoginText(), 2); // wait till login text in login window is visible
		log.debug("login window opened");
		home.setUserName(username);
		log.debug("Entered user name " + username);
		home.setPassword(password);
		log.debug("entered password " + password);
		home.getLoginButton().click();
		wait.waituntillElementIsVisible(home.getLogoutLink(), 15); // wait till logout link is visible
		Listners.getReporter().log(Status.PASS, "user is able to login");
		String nameOfLoggedInUser = home.getNameOfUser().getText();
		// assert if logged in username is visible in home page
		Assert.assertTrue(nameOfLoggedInUser.contains(username), "The user is not logged in");
		log.debug("logged in username is visible in home page");
		Listners.getReporter().log(Status.PASS, "currently logged in user is verified");
		log.debug("user was successfully logged in");
	}

	// this test will only run if userLoginTest has passed
	@Test(dependsOnMethods = "userLoginTest", groups = { "sanity", "regression" }, priority = 1)
	public void userLogoutTest() {
		// test case to see if logged in user is able to logout
		Listners.getReporter().log(Status.INFO, "verifying if logged in user is able to logout");
		log.info("loging out");
		home.getLogoutLink().click();
		log.info("logout link was clicked");
		wait.waituntillElementIsVisible(home.getLoginLink(), 5); // wait till login link is visible
		// verify is user name is no more present in home page
		Assert.assertFalse(home.getNameOfUser().isDisplayed(), "user name is still present in home page");
		log.debug("logged out username is not visible in home page");
		Listners.getReporter().log(Status.PASS, "user was successfully logged out");
		log.debug("user was successfully logged out");
	}

	@Test(groups = { "sanity", "regression" }, priority = 2)
	public void invaliduserLoginTest() {
		// test case to see user cannot login with out appropriate login id
		Listners.getReporter().log(Status.INFO, "verifying that user cannot login with out appropriate login id");
		String username = "invalid_user@demo.com";
		String password = MyConfig.getBundle().get("demoblaze.login.password");
		log.info("Loging in without valid user id ");
		home.getLoginLink().click();
		wait.waituntillElementIsVisible(home.getLoginText(), 2); // wait till login text in login window is visible
		log.debug("login window opened");
		home.setUserName(username);
		log.debug("Entered user name " + username);
		home.setPassword(password);
		log.debug("entered password " + password);
		home.getLoginButton().click();
		Alert invaliduserAlert = common.switchToAlert(); // switch to alert
		String alertText = invaliduserAlert.getText();
		Listners.getReporter().log(Status.PASS,
				"user cannot login with out appropriate login id. Alert pop up is displayed");
		log.debug("alert opened with text: " + alertText);
		// verify alert text
		Assert.assertEquals(alertText, "User does not exist.", "the alert text does not match expected");
		Listners.getReporter().log(Status.PASS, "the alert text matches expected");
		log.debug("the alert text matches expected");
		// accept and close alert
		invaliduserAlert.accept();
		log.debug("alert accepted");
		home.getCloseLoginButton().click();// close login window
		log.debug("login window was closed");
	}

	@Test(groups = { "regression" }, priority = 3)
	public void cancelLogintest() {
		// test case to verify user can cancel loging in
		Listners.getReporter().log(Status.INFO, "verifying if user can cancel loging in");
		String username = MyConfig.getBundle().get("demoblaze.login.username");
		String password = MyConfig.getBundle().get("demoblaze.login.password");
		log.info("opening login window");
		home.getLoginLink().click();
		wait.waituntillElementIsVisible(home.getLoginText(), 2); // wait till login text in login window is visible
		log.debug("login window opened");
		home.setUserName(username);
		log.debug("Entered user name " + username);
		home.setPassword(password);
		log.debug("entered password " + password);
		log.info("closing login window");
		home.getCloseLoginButton().click();
		wait.waituntillElementIsVisible(home.getLoginLink(), 5); // wait till login link is visible
		// verify is user name is no more present in home page
		Assert.assertFalse(home.getNameOfUser().isDisplayed(), "user name is still present in home page");
		log.debug("username is not visible in home page");
		Listners.getReporter().log(Status.PASS, "user was able to cancel login window");
		log.debug("user was successfully  able to cancel login window");

	}

	@AfterTest(alwaysRun=true)
	public void testTearDown() {
		driverlocal.close();
	}
}
