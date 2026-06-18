package com.saucedemo.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Pre-scenario state shared across step definitions via PicoContainer DI.
 * Holds the {@Link PageObjectManager} and a generic key/value store so steps  in
 * different classes can pass data within a single scenario.
 */

public class ScenarioContext {
	
	private final PageObjectManager pages = new PageObjectManager();
	private final Map<String, Object> store = new HashMap<>();
	
	public PageObjectManager pages() {return pages;}
	
	public void set(String key, Object value) {store.put(key, value);}
	
	public Object get(String key) {return store.get(key);}
	
	public <T> T get(String key, Class<T> type) {
		return (T) store.get(key);}
}
