package com.demoblaze.resource;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(); // create local thread

	private DriverFactory() { // to restrict access to calss. this will make the class singleton

	}

	private static DriverFactory instance = new DriverFactory(); // create an instance of this class

	public static DriverFactory getInstance() { // to access the instance from outside
		return instance;
	}

	public void setDriver(WebDriver driver) { // set webdriver instance to local thread
		this.driver.set(driver);
	}

	public WebDriver getdriver() { // get webdriver local thread instance
		return driver.get();
	}

	public void closeBrowser() {
		driver.get().close();
		driver.remove();
	}
}
