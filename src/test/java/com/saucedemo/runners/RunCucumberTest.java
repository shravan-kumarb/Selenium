package com.saucedemo.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * TestNG runner for the Cucumber suite. Scenarios are discovered from 
 * {@code classparth:features} and run via TestNG; the parallel data providers
 * enables scenaio-level parallel execution (thread count is set in testng.xml).
 */

@CucumberOptions(
		features ="classpath:features",
		glue= "com.saucedemo",
		plugin = {
				"pretty",
				"html:target/cucumber-report.html",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
		})
public class RunCucumberTest extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel =true)
	public Object[][] scenarios(){
		return super.scenarios();
	}

}
