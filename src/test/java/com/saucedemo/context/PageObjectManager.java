package com.saucedemo.context;

import com.saucedemo.driver.DriverManager;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductPage;

/** 
 * Lazily creates and caches page objects for the current scenario, wiring them
 * to the thread's active {@link com.saucedemo.driver.DriverManager WebDriver}
 * Pages are created on first access (after the (@code @Before) hook starts the
 * browser), never in the constructor.
 */

public class PageObjectManager {
	
	private LoginPage loginPage;
	private ProductPage productPage;
	private CartPage cartPage;
	private CheckoutPage checkoutPage;
	
	public LoginPage login() {
		if(loginPage == null) {
			loginPage = new LoginPage(DriverManager.getDriver());
		}
		return loginPage;
	}
	
	public ProductPage products() {
		if(productPage == null) {
			productPage = new ProductPage(DriverManager.getDriver());
		}
		return productPage;
	}
	
	public CartPage cart() {
		if(cartPage == null) {
			cartPage = new CartPage(DriverManager.getDriver());
		}
		return cartPage;
	}
	
	public CheckoutPage checkout() {
		if(checkoutPage == null) {
			checkoutPage = new CheckoutPage(DriverManager.getDriver());
		}
		return checkoutPage;
	}

}
