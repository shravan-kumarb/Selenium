package com.saucedemo.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saucedemo.driver.DriverFactory;
import com.saucedemo.driver.DriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

/**
 * Driver life cycle for UI scenarios. API scenarios are tagged {@code @api} and
 * are excluded here so they never launch a browser.
 */

public class Hooks {
	
	private static final Logger log = LoggerFactory.getLogger(Hooks.class);
	
	@Before(value = "not @api", order=0)
	public void startBrowser(Scenario scenario) {
		log.info("Starting browser for scenario: {}", scenario.getName());
		DriverManager.setDriver(DriverFactory.createDriver());
	}
	
	@After (value="not @api",order =0)
	public void stopBrowser(Scenario scenario) {
		if(DriverManager.hasDriver()) {
			if(scenario.isFailed()) {
				captureScreenshot(scenario);
			}
			DriverManager.quitDriver();
		}
		log.info("Finished scenario: {} -> {}", scenario.getName(), scenario.getStatus());
	}
	
	private void captureScreenshot(Scenario scenario) {
		try {
			org.openqa.selenium.WebDriver driver = DriverManager.getDriver();
			log.warn("Failure context -> url={} | title={}",driver.getCurrentUrl(),driver.getTitle());
			byte[] png = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(png, "image/png", scenario.getName());
			Allure.addAttachment("Failure screenshot", new ByteArrayInputStream(png));
			java.nio.file.Path dir = java.nio.file.Paths.get("target","screenshots");
			java.nio.file.Files.createDirectories(dir);
			String safe = scenario.getName().replaceAll("[^a-zA-Z0-9-_]","_");
			java.nio.file.Files.write(dir.resolve(safe+".png"), png);
			log.warn("Saved screenshot to target/screenshots/{}.png",safe);
		}catch(Exception e) {
			log.warn("Coult not capture screenshot: {}", e.getMessage());
		}
	}

}
