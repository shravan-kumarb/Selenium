package com.saucedemo.stepdefs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.saucedemo.context.ScenarioContext;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartSteps {
	
	private final ScenarioContext context;
	
	public CartSteps(ScenarioContext context) {this.context=context;}

	@When("the user opens the cart")
	public void theUserOpensTheCart() {
		context.pages().products().openCart();
		assertTrue(context.pages().cart().isLoaded());
	}
	
	@Then("the cart shows {int} items")
	public void theCartShowsItems(int expected) {
		assertEquals(context.pages().cart().getItemCount(),expected);
	}

	@Then("the cart contains {string}")
	public void theCartContains(String name) {
		assertTrue(context.pages().cart().getItemNames().contains(name));
	}
	
	@When("the user removes {string} from the cart page")
	public void theUserRemovesFromTheCartPage(String name) {
		context.pages().cart().removeItem(name);
	}
	
	@When("the user continues shopping")
	public void theUserContinuesShopping() {
		context.pages().cart().continueShopping();
	}
	
	@When("the user proceeds to checkout")
	public void theUserProceedsToCheckout(){
		context.pages().cart().checkout();
	}
	
	@Then("the checkout information page is displayed")
	public void theCheckoutInformationPageIsDisplayed() {
		assertTrue(context.pages().checkout().isLoaded());
	}
}
