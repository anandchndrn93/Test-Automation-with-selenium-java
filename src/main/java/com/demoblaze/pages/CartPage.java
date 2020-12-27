package com.demoblaze.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoblaze.resource.MyConfig;

public class CartPage {
	WebDriver driver;
	public static Logger log = LogManager.getLogger(CartPage.class.getName());

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = "//h3[@id='totalp']/ancestor::div/button")
	WebElement placeOrderButton;

	@FindBy(xpath = "//tbody[@id='tbodyid']/tr/td[2]")
	WebElement addedProductText;

	@FindBy(xpath = "//tbody[@id='tbodyid']/tr/td[3]")
	WebElement addedProductPriceText;

	@FindBy(xpath = "//tbody[@id='tbodyid']")
	WebElement addedProductTable;

	@FindBy(id = "name")
	WebElement nameTextbox;

	@FindBy(id = "country")
	WebElement countryTextbox;

	@FindBy(id = "city")
	WebElement cityTextbox;

	@FindBy(id = "card")
	WebElement cardTextbox;

	@FindBy(id = "month")
	WebElement monthTextbox;

	@FindBy(id = "year")
	WebElement yearTextbox;

	@FindBy(xpath = "//button[@onclick='purchaseOrder()']")
	WebElement purchaseButton;

	@FindBy(id = "orderModalLabel")
	WebElement placeOrderText;

	@FindBy(xpath = "//h2[contains(text(),'Thank you')]")
	WebElement thankText;

	@FindBy(xpath = "//button[contains(text(),'OK')]")
	WebElement okButton;

	@FindBy(xpath = "//a[contains(@onclick,'deleteItem')]")
	List<WebElement> allDeleteLinks;

	public WebElement getPlaceOrderButton() {
		return placeOrderButton;
	}

	public String getAddedProductText() {
		return addedProductText.getText();
	}

	public String getAddedProductPriceText() {
		return addedProductPriceText.getText();
	}

	public WebElement getAddedProductTable() {
		return addedProductTable;
	}

	public WebElement getPurchaseButton() {
		return purchaseButton;
	}

	public WebElement getPlaceOrderText() {
		return placeOrderText;
	}

	public WebElement getThankText() {
		return thankText;
	}

	public WebElement getokButton() {
		return okButton;
	}

	public void fillPaymentInfo() {
		nameTextbox.clear();
		nameTextbox.sendKeys(MyConfig.getBundle().get("name"));
		log.debug("name " + MyConfig.getBundle().get("name") + " entered");

		countryTextbox.clear();
		countryTextbox.sendKeys(MyConfig.getBundle().get("country"));
		log.debug("country " + MyConfig.getBundle().get("country") + " entered");

		cityTextbox.clear();
		cityTextbox.sendKeys(MyConfig.getBundle().get("city"));
		log.debug("city " + MyConfig.getBundle().get("city") + " entered");

		cardTextbox.clear();
		cardTextbox.sendKeys(MyConfig.getBundle().get("card"));
		log.debug("card " + MyConfig.getBundle().get("card") + " entered");

		monthTextbox.clear();
		monthTextbox.sendKeys(MyConfig.getBundle().get("month"));
		log.debug("month " + MyConfig.getBundle().get("month") + " entered");

		yearTextbox.clear();
		yearTextbox.sendKeys(MyConfig.getBundle().get("year"));
		log.debug("month " + MyConfig.getBundle().get("year") + " entered");
	}

	public List<WebElement> getAllDeleteLinks() {
		return allDeleteLinks;
	}
}
