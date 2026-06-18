package com.saucedemo.data;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saucedemo.config.ConfigReader;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Loads externalize test data from {@code data/testdata.json} on the class path
 * and exposes typed accessors. Keeping test data in JSON (rather than hard coded
 * Java constants) lets non-developers edit it and keeps a single source of truth.
 */

public final class TestData {
	
	private static final JsonNode ROOT = load();
	
	private TestData() {
		
	}
	
	private static JsonNode load()
	{
		try (InputStream in = ConfigReader.openResource("data/testdata.json")){
			if(in == null) {
				throw new IllegalStateException("data/testdata.json not found on the classpath or filesystem"
						+ "Ensure src/main/resources is built/on the classpath"
						+ "{rebuild / reimport the project).");
			}
			return new ObjectMapper().readTree(in);
		}catch (Exception e) {
			throw new ExceptionInInitializerError("Failed to load test data: "+e.getMessage());
		}
	}
	
	public static JsonNode node(String section) {
		return ROOT.path(section);
	}
	
	public  static String text(String section, String key) {
		return ROOT.path(section).path(key).asText();
	}
	public static int number(String section, String key) {
		return ROOT.path(section).path(key).asInt();
	}
	
	public  static List<String> stringList(String section, String key){
		List<String> values = new ArrayList<>();
		ROOT.path(section).path(key).forEach(node-> values.add(node.asText()));
		return List.copyOf(values);
	}
}
