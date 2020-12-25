package com.demoblaze.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
	WebDriver driver;
	WebDriverWait wait;

	public WaitUtil(WebDriver driver) {
		this.driver = driver;

	}

	public void waituntillElementIsVisible(WebElement element, int Timout) {
		wait = new WebDriverWait(driver, Timout);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waituntillElementIsInvisible(WebElement element, int Timout) {
		wait = new WebDriverWait(driver, Timout);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waituntillElementIsClickable(WebElement element, int Timout) {
		wait = new WebDriverWait(driver, Timout);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
