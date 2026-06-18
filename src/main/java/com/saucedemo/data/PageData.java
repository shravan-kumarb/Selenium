package com.saucedemo.data;

/** Expected page-state text, sourced from externalized JSON ({@code data/testdata.json}). */

public final class PageData {

	public static final String PRODUCTS_TITLE = TestData.text("pages", "productsTitle");
	public static final String CART_TITLE = TestData.text("pages","cartTitle");
	
	private PageData() {
		
	}
	

}
