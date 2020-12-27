package com.demoblaze.utilities;

import java.util.List;

import org.openqa.selenium.By;
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

	public void waituntillElementIsPresent(String xpath, int Timeout) {
		wait = new WebDriverWait(driver, Timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

	}

	public void waituntillAllElementsVisible(List<WebElement> elements, int Timeout) {
		wait = new WebDriverWait(driver, Timeout);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
}
