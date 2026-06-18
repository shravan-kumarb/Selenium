package com.saucedemo.runners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Reconciles flaky scenarios that failed an initial attempt but passed on retry
 * (via {@link RetryAnalyzer}). Without this, TestNG/Surefire still counts the
 * failed attempts and fails the build. Here we drop any failed result that also
 * has a corresponding passed result, so a recovered flake does not fail the run. 
 */

public class RerunFilterListener implements ITestListener{
	
	@Override
	public void onFinish(ITestContext context) {
		List<ITestResult> toRemove = new ArrayList<>();
		for(ITestResult failed : context.getFailedTests().getAllResults()) {
			String id = identify(failed);
			boolean passedLater = context.getPassedTests().getAllResults().stream()
					.anyMatch(passed->identify(passed).equals(id));
			if(passedLater) {
				toRemove.add(failed);
			}
			
		}
		toRemove.forEach(result->context.getFailedTests().removeResult(result));
		
	}

	private String identify(ITestResult result) {
		return result.getMethod().getQualifiedName() + Arrays.toString(result.getParameters());
	}
}
