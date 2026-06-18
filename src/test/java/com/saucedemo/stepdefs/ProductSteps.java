package com.saucedemo.stepdefs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.saucedemo.context.ScenarioContext;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductSteps {
	
	private final ScenarioContext context;
	
	public ProductSteps(ScenarioContext context) {
		this.context= context;
	}
	
	@When("the products are sorted by {string}")
	public void theProductsAreSortedBy(String option) {
		context.pages().products().sortBy(option);
	}
	
	@Then("the inventroy shows {int} products")
	public void theInventoryShowsProducts(int expected) {
		assertEquals(context.pages().products().getProductName().size(),expected);
	}
	
	@Then("the inventory contains {string}")
	public void theInventoryContains(String name) {
		assertTrue(context.pages().products().getProductName().contains(name));
	}
	
	@Then("the product prices are sorted ascending")
	public void theProductPricesAreSortedAscending() {
		List<Double> prices = context.pages().products().getProductPrice();
		List<Double> sorted = new ArrayList<>(prices);
		sorted.sort(Comparator.naturalOrder());
		assertEquals(prices, sorted);
	}

	@Then("the product names are sorted descending")
	public void theProductNamesAreSortedDescending() {
		List<String> names = context.pages().products().getProductName();
		List<String> sorted = new ArrayList<>(names);
		sorted.sort(Comparator.reverseOrder());
		assertEquals(names, sorted);
	}
	
	@When("the user adds {string} to the cart")
	public void theUserAddsToTheCart(String product) {
		context.pages().products().addToCart(product);
	}
	
	@When("the user removes {string} from the cart")
	public void theUserRemovesFromTheCart(String product) {
		context.pages().products().removeFromCart(product);
	}
	
	@Then("the cart badge shows {int}")
	public void theCartBadgeShows(int expected) {
		assertEquals(context.pages().products().getCartCount(),expected);
	}
}
