package com.demoblaze.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	WebDriver driver;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = "//a[contains(@onclick,'addToCart')]")
	WebElement addToCartLink;

	@FindBy(xpath = "//div[@id='tbodyid']/h3")
	WebElement priceText;

	@FindBy(id = "cartur")
	WebElement cartLink;

	public WebElement getAddToCartLink() {
		return addToCartLink;
	}

	public WebElement getCartLink() {
		return cartLink;
	}

	public String getPriceText() {
		return priceText.getText();
	}
}
