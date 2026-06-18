package com.saucedemo.utils;

import java.util.concurrent.ThreadLocalRandom;

/** 
 * Lightweight, zero-dependency random data helpers used to support dynamic
 * test data (e.g. unique checkout customers).
 */

public final class DataGenerator {
	
	private static final String[] FIRST_NAMES = 
		{"Alex","Sam","Riya","Venice","Kiran","Mia","Noah","Aria"};
	private static final String[] LAST_NAMES =
		{"Stone","Reddy","Khan","Lopez","Singh","Brown","Walsh","Iyer"};
	private static final String DIGITS = "0123456789";
	
	public static String randomFirstName() {
		return FIRST_NAMES[ThreadLocalRandom.current().nextInt(FIRST_NAMES.length)];
	}
	
	public static String randomLastName() {
		return LAST_NAMES[ThreadLocalRandom.current().nextInt(LAST_NAMES.length)];
	}
	
	public static String randomPostalCode(int length) {
		StringBuilder code = new StringBuilder();
		for(int i=0;i<length; i++) {
			code.append(DIGITS.charAt(ThreadLocalRandom.current().nextInt(DIGITS.length())));
		}
		return code.toString();
	}
	public static String randomPostalCode() {
		return randomPostalCode(6);
	}
}
