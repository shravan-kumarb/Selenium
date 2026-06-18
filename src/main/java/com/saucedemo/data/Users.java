package com.saucedemo.data;

import com.saucedemo.config.ConfigReader;

/**
 * SauceDemo user accounts. User names are  source from externalize JSON
 * ({@code data/testdata.json}); the shared password comes from configuration
 * ({@code password} /{@code -Dpassword}) so it is not hard coded.
 */

public final class Users {
	
	public static final String PASSWORD = ConfigReader.get("password", "secret_sauce");
	
	public static final String STANDARD = TestData.text("users", "standard");
	public static final String LOCKED = TestData.text("users", "locked");
	public static final String PROBLEM = TestData.text("users", "problem");
	public static final String PERFROMANCE = TestData.text("users", "performance");
	
	private Users() {
		
	}
			
			

}
