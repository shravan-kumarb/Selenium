package com.saucedemo.runners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * Applies {@link RetryAnalyzer} to every test method so flaky scenarios are
 * retried automatically. Registered as a TestNG listeners in testng.xml.
 */

public class RetryListener implements IAnnotationTransformer {
	
	
	@Override
	public void transform(ITestAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

}
