package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage{
	
	private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
	
	private final By username = By.id("user-name");
	private final By password = By.id("password");
	private final By loginButton = By.id("login-button");
	private final By errorMessage = By.cssSelector(".error-message-container h3");
	
	public LoginPage(WebDriver driver) {super(driver);}
	
	public void openLoginPage(String baseUrl) {
		log.info("Opening login page: {}",baseUrl);
		open(baseUrl);
		visible(loginButton);
	}
	
	public void login(String user, String pass) {
		log.info("Logging in as {}",user);
		type(username, user);
		type(password,pass);
		click(loginButton);
	}
	
	public String getErrorMessage() { return text(errorMessage);}

}
