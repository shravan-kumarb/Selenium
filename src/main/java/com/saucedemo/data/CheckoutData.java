package com.saucedemo.data;

/** Checkout test data, source from externalize JSON ({@code data/testdata.json}).*/

public final class CheckoutData {
	
	public  static final String VALID_FIRST_NAME =TestData.text("checkout","validFirstName");
	public static final String VALID_LAST_NAME=TestData.text("checkout","validLastName");
	public static final String VALID_POSTAL_CODE= TestData.text("checkout","validPostalCode");
	public static final String SUCCESS_MESSAGE = TestData.text("checkout", "successMessage");

	private CheckoutData(){
		
	}
}
