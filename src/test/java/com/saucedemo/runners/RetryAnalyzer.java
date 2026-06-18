package com.saucedemo.runners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Re-runs a failed scenario up to {@code MAX_RETRIES} times before marking it
 * failed. This is the TestNG-idiomatic equivalent of Surefire's
 * {@code rerunFailingTestsCount} and helps absorb transient UI flakiness.
 */
public class RetryAnalyzer implements IRetryAnalyzer{
	
	private static final int MAX_RETRIES = 2;
	private int attempts =0;
	
	@Override
	public boolean retry(ITestResult result) {
		if(attempts < MAX_RETRIES) {
			attempts++;
			return true;
		}
		return false;
	}

}
