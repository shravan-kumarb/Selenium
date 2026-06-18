package com.saucedemo.data;

import java.util.List;

/**
 * Product test data, source from externalize JSON({@code data/testdata.json}).
 */
public final class Products {

	public static final String BACKPACK = TestData.text("products","backpack");
	public static final String BIKE_LIGHT = TestData.text("products","bikelight");
	
	public static final int EXPECTED_COUNT = TestData.number("products","expectedCount");
	
	public static final List<String> DEFAULT_CART_ITEMS = TestData.stringList("products","defaultCartItems");
	
	private Products() {
		
	}
}
