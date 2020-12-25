package com.demoblaze.utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class commonUtil {

	WebDriver driver;

	public commonUtil(WebDriver driver) {
		this.driver = driver;
	}

	public Alert switchToAlert() {
		Alert alert = null;
		int timeout = 0;
		while (timeout++ < 10) {
			try {
				Thread.sleep(1000);
				alert = driver.switchTo().alert();
				break;
			} catch (NoAlertPresentException | InterruptedException ex) {
				// Alert not present
				continue;
			}
		}
		return alert;
	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor executer = (JavascriptExecutor) driver;
		executer.executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
