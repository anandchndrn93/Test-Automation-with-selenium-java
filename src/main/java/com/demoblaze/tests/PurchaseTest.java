package com.demoblaze.tests;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.demoblaze.pages.CartPage;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.ProductPage;
import com.demoblaze.resource.Listners;
import com.demoblaze.resource.MyConfig;
import com.demoblaze.resource.TestBase;
import com.demoblaze.utilities.WaitUtil;
import com.demoblaze.utilities.commonUtil;

public class PurchaseTest extends TestBase {
	WaitUtil wait;
	HomePage home;
	ProductPage product;
	CartPage cart;
	commonUtil common;
	public static Logger log = LogManager.getLogger(PurchaseTest.class.getName());
	WebDriver driverlocal;

	@BeforeTest(alwaysRun=true)
	public void setConfig() {
		launchBrowser();
		driverlocal = getDriver();
		home = new HomePage(driverlocal);
		wait = new WaitUtil(driverlocal);
		common = new commonUtil(driverlocal);
		product = new ProductPage(driverlocal);
		cart = new CartPage(driverlocal);
	}

	@Test(groups = { "sanity", "regression" })
	public void purchaseProduct() {
		// test case to see verify if user can purchase Nexus 6
		Listners.getReporter().log(Status.INFO,
				"verifying if user can purchase " + MyConfig.getBundle().get("productName"));
		// login as valid user
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
		log.debug("user succsfully logged in");
		Listners.getReporter().log(Status.PASS, "user succsfully logged in");

		home.getProductCategoryLink().click(); // select product category
		log.debug("opening product category " + MyConfig.getBundle().get("productCategory"));
		wait.waituntillElementIsClickable(home.getProductLink(), 5);
		common.scrollIntoView(home.getProductLink());
		home.getProductLink().click();
		log.debug("opening product page for  " + MyConfig.getBundle().get("productName"));

		wait.waituntillElementIsClickable(product.getAddToCartLink(), 5);// wait till add to cart button is clickable
		String price = StringUtils.substringBefore(product.getPriceText(), "*").trim(); // get price of product
		log.debug("Price of " + MyConfig.getBundle().get("productName") + " is " + price);
		product.getAddToCartLink().click();
		log.info("adding " + MyConfig.getBundle().get("productName") + " to the cart");

		Alert cartAlert = common.switchToAlert();
		String addedToCartText = cartAlert.getText();
		log.debug("alert opened with text: " + addedToCartText);
		// verify alert text
		Assert.assertEquals(addedToCartText, "Product added.", "the alert text does not match expected");
		log.debug("the alert text matches expected");
		// accept and close alert
		cartAlert.accept();
		log.debug("alert accepted");
		log.debug(MyConfig.getBundle().get("productName") + " was added to the cart");
		Listners.getReporter().log(Status.PASS, MyConfig.getBundle().get("productName") + "was added to the cart");

		log.info("Navigating to cart");
		product.getCartLink().click();
		wait.waituntillElementIsVisible(cart.getAddedProductTable(), 5);// wait till added product table button is
																		// visible
		log.debug("cart was loaded");

		Assert.assertEquals(MyConfig.getBundle().get("productName"), cart.getAddedProductText(),
				"The product in cart does not match actual product"); // assert if correct product is on cart
		log.debug("the product in cart is " + MyConfig.getBundle().get("productName"));
		Assert.assertTrue(price.replaceAll("\\D+", "").equals(cart.getAddedProductPriceText()),
				"the price in cart does not match product price");
		log.debug("the price in cart is " + price);
		Listners.getReporter().log(Status.PASS, "Product name and price of product in cart was verified");
		log.info("proceding to pay");
		cart.getPlaceOrderButton().click();

		wait.waituntillElementIsVisible(cart.getPlaceOrderText(), 5);
		log.debug("filling payment info");
		cart.fillPaymentInfo();
		cart.getPurchaseButton().click();
		log.debug("purchasing product");
		wait.waituntillElementIsVisible(cart.getThankText(), 5);
		log.debug("product purchase complete");
		cart.getokButton().click();
		Listners.getReporter().log(Status.PASS, "Product purchase was completed");
	}

	@AfterTest(alwaysRun=true)
	public void testTearDown() {
		driverlocal.close();
	}
}
