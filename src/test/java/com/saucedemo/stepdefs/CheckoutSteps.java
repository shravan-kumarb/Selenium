package com.saucedemo.stepdefs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.saucedemo.context.ScenarioContext;
import com.saucedemo.data.CheckoutData;
import com.saucedemo.pages.CheckoutPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutSteps {

	private final ScenarioContext context;

	public CheckoutSteps(ScenarioContext context) {
		this.context = context;
	}
	
	@When("the user enters valid checkout information")
	public void theUserEntersValidCheckoutInformation() {
		context.pages().checkout().fillInfo(
				CheckoutData.VALID_FIRST_NAME,
				CheckoutData.VALID_LAST_NAME,
				CheckoutData.VALID_POSTAL_CODE);
	}
	
	@When("the user enters checkout info {string} {string} {string}")
	public void theUserEntersCheckoutInfo(String first, String last, String postal) {
		context.pages().checkout().fillInfo(first, last, postal);
	}
	
	@Then("the order total equals subtotal plus tax")
	public void theOrderTotalEqualsSubtotalPlusTax() {
		CheckoutPage checkout = context.pages().checkout();
		double subtotal = checkout.getSubtotal();
		double tax = checkout.getTax();
		double total = checkout.getTotal();
		assertEquals(total,subtotal+tax, 0.01);
	}
	
	@When("the user finishes the order")
	public void theUserFinishesTheOrder() {
		context.pages().checkout().finish();
	}
	
	@Then("the order is successful")
	public void theOrderIsSuccessful() {
		assertEquals(context.pages().checkout().getSuccessMessage(),CheckoutData.SUCCESS_MESSAGE);
	}
	
	@Then("a checkout error containing {string} is shown")
	public void aCheckoutErrorContaining(String message) {
		assertTrue(context.pages().checkout().getErrorMessage().toLowerCase().contains(message.toLowerCase()));
	}
	

}
