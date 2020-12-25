package com.demoblaze.resource;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class TestBase {
	WebDriver driverinstance;
	BrowserFactory browser = new BrowserFactory();

	public void launchBrowser() {
		DriverFactory.getInstance().setDriver(browser.createBrowserInstance()); // launch browser
		driverinstance = DriverFactory.getInstance().getdriver();
		driverinstance.manage().window().maximize();
		driverinstance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driverinstance.get(MyConfig.getBundle().get("url"));
	}

	public WebDriver getDriver() {
		return driverinstance;
	}

	public void testTearDown() { // close browser
		DriverFactory.getInstance().closeBrowser();
	}
}
