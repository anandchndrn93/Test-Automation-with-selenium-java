package com.demoblaze.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoblaze.resource.MyConfig;
import com.demoblaze.tests.ContactTest;

public class HomePage {

	WebDriver driver;
	public static Logger log = LogManager.getLogger(HomePage.class.getName());

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(id = "login2")
	WebElement loginLink;

	@FindBy(id = "loginusername")
	WebElement usernameTextBox;

	@FindBy(id = "loginpassword")
	WebElement passwordTextBox;

	@FindBy(id = "logInModalLabel")
	WebElement loginText;

	@FindBy(xpath = "//button[@onclick='logIn()']")
	WebElement loginButton;

	@FindBy(id = "nava")
	WebElement productStoreText;

	@FindBy(id = "nameofuser")
	WebElement nameOfUser;

	@FindBy(id = "logout2")
	WebElement logoutLink;

	@FindBy(xpath = "//button[@onclick='logIn()']/preceding-sibling::button")
	WebElement closeLoginButton;

	WebElement productLink;

	WebElement productCategoryLink;

	@FindBy(xpath = "//a[text()='Contact']")
	WebElement contactLink;

	@FindBy(id = "exampleModalLabel")
	WebElement newMessageText;

	@FindBy(id = "recipient-email")
	WebElement emailTextbox;

	@FindBy(id = "recipient-name")
	WebElement nameTextbox;
	
	@FindBy(id = "message-text")
	WebElement messageTextbox;
	
	@FindBy(xpath = "//button[@onclick='send()']")
	WebElement sendMessageButton;
	
	public void setUserName(String strUserName) {
		usernameTextBox.clear();
		usernameTextBox.sendKeys(strUserName);
	}

	public void setPassword(String strpassword) {
		passwordTextBox.clear();
		passwordTextBox.sendKeys(strpassword);
	}

	public WebElement getLoginLink() {
		return loginLink;
	}

	public WebElement getLoginText() {
		return loginText;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getProductStoreText() {
		return productStoreText;
	}

	public WebElement getNameOfUser() {
		return nameOfUser;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public WebElement getCloseLoginButton() {
		return closeLoginButton;
	}

	public WebElement getProductCategoryLink() {
		productCategoryLink = driver
				.findElement(By.xpath("//a[contains(text(),'" + MyConfig.getBundle().get("productCategory") + "')]"));

		return productCategoryLink;
	}

	public WebElement getProductLink() {
		productLink = driver
				.findElement(By.xpath("//a[contains(text(),'" + MyConfig.getBundle().get("productName") + "')]"));
		return productLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getNewMessageText() {
		return newMessageText;
	}

	public void fillMessageDetails() {
		emailTextbox.clear();
		emailTextbox.sendKeys(MyConfig.getBundle().get("demoblaze.login.username"));
		log.debug("email " + MyConfig.getBundle().get("demoblaze.login.username") + " was entered");
		
		nameTextbox.clear();
		nameTextbox.sendKeys(MyConfig.getBundle().get("name"));
		log.debug("name " + MyConfig.getBundle().get("name") + " was entered");
		
		messageTextbox.clear();
		messageTextbox.sendKeys("Thanks for the DemoSite.");
		log.debug("message Thanks for the DemoSite was entered");
			
	}
	
	public WebElement getSendMessageButton() {
		return sendMessageButton;
	}
}
