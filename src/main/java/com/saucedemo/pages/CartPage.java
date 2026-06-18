package com.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saucedemo.data.PageData;



public class CartPage extends BasePage{
	
	private static final Logger log = LoggerFactory.getLogger(CartPage.class);
	
	private final By title = By.cssSelector(".title");
	private final By cartItem = By.cssSelector(".cart_item");
	private final By itemName = By.cssSelector(".inventory_item_name");
	private final By checkoutButton = By.id("checkout");
	private final By continueShoppingButton = By.id("continue-shopping");
	
	public CartPage(WebDriver driver) {super(driver);}
	
	public boolean isLoaded() {return PageData.CART_TITLE.equals(text(title));}
	
	public List<String> getItemNames() {return texts(itemName);}
	
	public int getItemCount() {return count(cartItem);}
	
	public void removeItem(String productName) {
		log.info("Removing item from cart: {}",productName);
		By removeButton = By.xpath("//div[contains(@class,'cart_item')][.//div[contains(@class,'inventory_item_name')"
				+ "and normalize-space()='"+productName+"']]//button[normalize-space()='Remove']");
		clickable(removeButton).click();	
	}
	
	public void checkout() {
		log.info("Proceeding to checkout");
		click(checkoutButton);
	}
	
	public void continueShopping() {
		log.info("Continuing Shopping");
		click(continueShoppingButton);
	}

}
