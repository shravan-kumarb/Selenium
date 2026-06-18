package com.saucedemo.stepdefs;

import static org.testng.Assert.assertTrue;

import com.saucedemo.config.ConfigReader;
import com.saucedemo.context.ScenarioContext;
import com.saucedemo.data.Users;
import com.saucedemo.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	
	private final ScenarioContext context;
	
	public LoginSteps(ScenarioContext context) {this.context=context;}
	
	@Given("the user is on the login page")
	public void theUserIsOnTheLoginPage() {
		context.pages().login().openLoginPage(ConfigReader.getRequired("base.url"));
	}
	
	@Given("the user is logged in as {string}")
	public void theUserIsOnTheLoginPage(String username) {
		LoginPage login = context.pages().login();
		login.openLoginPage(ConfigReader.getRequired("base.url"));
		login.login(username, Users.PASSWORD);
		assertTrue(context.pages().products().isLoaded(),"products page should be loaded after login");
	}
	
	@When("the user logs in as {string}")
	public void theUserLogInAs(String username) {
		context.pages().login().login(username, Users.PASSWORD);
	}
	
	@When("the user logs in with username {string} and password {string}")
	public void theUserLogsInWith(String username, String password) {
		context.pages().login().login(username, password);
	}
	
	@Then("the products page is displayed")
	public void theProductsPageIsDispalyed() {
		assertTrue(context.pages().products().isLoaded());
	}
	
	@Then("a login error containing {string} is shown")
	public void aLoginErrorContaining(String message) {
		assertTrue(context.pages().login().getErrorMessage().toLowerCase()
				.contains(message.toLowerCase()));
	}
	

}
