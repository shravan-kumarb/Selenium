package com.saucedemo.pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutPage  extends BasePage{
	
	private static final Logger log = LoggerFactory.getLogger(CheckoutPage.class);
	private static final Pattern NUMBER = Pattern.compile("[\\d.]+");
	
	private final By firstName = By.id("first-name");
	private final By lastName = By.id("last-name");
	private final By postalCode = By.id("postal-code");
	private final By continueButton = By.id("continue");
	private final By finishButton = By.id("finish");
	private final By subtotalLabel = By.cssSelector(".summary_subtotal_label");
	private final By taxLabel = By.cssSelector(".summary_tax_label");
	private final By totalLabel = By.cssSelector(".summary_total_label");
	private final By successHeader = By.cssSelector(".complete-header");
	private final By errorMessage = By.cssSelector("[data-test='error'], .error-message-container h3");
	
	public CheckoutPage(WebDriver driver) {super(driver);}
	
	public boolean isLoaded() { return isDisplayed(firstName);}
	
	public void fillInfo(String first, String last, String postal) {
		log.info("Filling checkout info: {} {} {}", first, last, postal);
		
		if(first != null && !first.isEmpty()) {
			type(firstName, first);
		}
		if(last != null && !last.isEmpty()) {
			type(lastName, last);
		}
		if(postal != null && !postal.isEmpty()) {
			type(postalCode, postal);
		}
		click(continueButton);
	}
	
	public double getSubtotal() {return parseAmount(text(subtotalLabel));}
	
	public double getTax() {return parseAmount(text(taxLabel));}
	
	public double getTotal() {return parseAmount(text(totalLabel));}
	
	public void finish() {
		log.info("Finishing order");
		click(finishButton);
	}
	
	public String getSuccessMessage() {return text(successHeader);}
	
	public String getErrorMessage() {return text(errorMessage);}
	
	private double parseAmount(String label) {
		Matcher matcher = NUMBER.matcher(label);
		return matcher.find()? Double.parseDouble(matcher.group()) :0.0;
	}
	
	
	
}
