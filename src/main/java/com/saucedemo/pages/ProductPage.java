package com.saucedemo.pages;

import java.util.List;

import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saucedemo.data.PageData;


public class ProductPage extends BasePage{
	
	private static final Logger log = LoggerFactory.getLogger(ProductPage.class);
	
	
	private final By title = By.cssSelector(".title");
	private final By inventoryItem = By.cssSelector(".inventory_item");
	private final By itemName = By.cssSelector(".inventory_item_name");
	private final By itemPrice = By.cssSelector(".inventory_item_price");
	private final By cartLink = By.cssSelector(".shopping_cart_link");
	private final By cartBadge = By.cssSelector(".shopping_cart_badge");
	private final By sortDropdown = By.cssSelector(".product_sort_container");
	
	public ProductPage(WebDriver driver) {super(driver);}
	
	public boolean isLoaded() {return PageData.PRODUCTS_TITLE.equals(text(title)) 
			&& count(inventoryItem) > 0;}
	
	public List<String> getProductName() {return texts(itemName);}
	
	public List<Double> getProductPrice() { 
		return texts(itemPrice).stream()
				.map(p->Double.parseDouble(p.replace("$","").trim()))
				.collect(Collectors.toList());
	}
	
	public void sortBy(String option) {
		log.info("Sorting products by {}", option);
		selectByValue(sortDropdown,option);
	}
	
	public void addToCart(String productName) {
		log.info("Adding product to cart: {}", productName);
		cardButton(productName, "Add to cart").click();
	}
	
	public void removeFromCart(String productName) {
		log.info("Removing product from cart: {}",productName);
		cardButton(productName, "Remove").click();
	}
	
	public int getCartCount() {
		if(count(cartBadge)==0) {
			return 0;
		}
		return Integer.parseInt(text(cartBadge));
	}
	
	public void openCart() {
		log.info("Opening cart");
		click(cartLink);
	}
	
	private WebElement cardButton(String productName, String buttonLabel) {
		By cardLocator = By.xpath(
					"//div[contains(@class,'inventory_item')]"
				+"[.//div[contains(@class,'inventory_item_name') and normalize-space()"
				+ "="+xpathLiteral(productName)+"]]//button[normalize-space()="
						+ xpathLiteral(buttonLabel)+"]");
		return clickable(cardLocator);
		}
	
	/** Safely quote a value for use inside an XPath expression. */
	private String xpathLiteral(String value) {
		if(!value.contains("'")) {
			return "'" + value+ "'";}
		
		return "concat('"+value.replace("'", "',\"'\",'")+"')";
		}
	

}
