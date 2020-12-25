package com.demoblaze.resource;

import com.demoblaze.resource.MyConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {
	public static WebDriver driver;

	private String browser = MyConfig.getBundle().get("browser");
	public static Logger baselog = LogManager.getLogger(BrowserFactory.class.getName());

	public WebDriver createBrowserInstance() { // create chrome instance
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			System.setProperty("webdriver.chrome.driver", MyConfig.getBundle().get("chromeDriverPath"));
			driver = new ChromeDriver(options);
			baselog.info("Launching google chrome");

		}

		else if (browser.equalsIgnoreCase("firefox")) { // create firefor browser
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			System.setProperty("webdriver.gecko.driver", MyConfig.getBundle().get("geckoDriverPath"));
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			options.addArguments("-private");
			firefoxOptions.setCapability("marionette", true);
			driver = new FirefoxDriver(options);
			baselog.info("Launching mozilla firefox");
		}

		// other browsers can be added. the browser to use can be specified in
		// ./src/main/resources/config/config.properties file
		return driver;
	}

}
