package com.saucedemo.driver;

import org.openqa.selenium.WebDriver;

/**
 * Thread-safe holder for the active {@link WebDriver}, enabling parallel
 * scenario execution where each thread owns its own browser instance.
 */

public final class DriverManager {
	
	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
	
	private DriverManager() {
		
	}
	
	public  static WebDriver getDriver() {
		WebDriver driver = DRIVER.get();
		if(driver == null) {
			throw new IllegalStateException(
					"WebDriver has not been initialized for this thread. Did the @Before hook run?");
		}
		return driver;
	}
	
	public static void setDriver(WebDriver driver) {DRIVER.set(driver);}
	
	public static boolean hasDriver() {
		return DRIVER.get() != null;
	}
	
	public static void quitDriver() {
		WebDriver driver = DRIVER.get();
		if(driver != null) {
			driver.quit();
			DRIVER.remove();
		}
	}
	

}
