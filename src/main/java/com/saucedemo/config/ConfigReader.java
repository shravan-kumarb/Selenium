package com.saucedemo.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Reads configuration from {@code config/config.properties} on the class path,
 * allowing any value to be overridden at runtime with a {@code - Dkey=value}
 * system property (e.g. {@code - Dbrowser=firefox -Dheadless=false}
 */

public final class ConfigReader {
	
	private static final Properties PROPS = new Properties();
	
	static {
		try(InputStream in = openResource("config/config.properties")){
					if(in == null) {
						throw new IllegalStateException(
								"config/config.properties not found on the classpath or filesystem. "
								+ "Ensure src/main/resource is built/on the test classpath"
								+ "(rebuild/ reimport the project).");
					}
					PROPS.load(in);
				}	catch(IllegalStateException e) {
					throw new ExceptionInInitializerError(e.getMessage());
				}	catch(Exception e) {
					throw new ExceptionInInitializerError("Failed to load config.properties: "+e.getMessage());
				}
	}
	
	private ConfigReader() {
		
	}
	
	
	/**
	 * Open a class path resource, falling back to the file system under
	 * {@code src/main/resources} / {@code src/test/resources}. The fallback keeps
	 * IDE runs working even when resources are not yet on the runtime class path.
	 * Caller is responsible for closing the returned stream
	 */
	public static InputStream openResource(String resource) throws IOException {
		InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream(resource);
		if(in != null) {
			return in;
		}
		for(String base: new String[] {"src/main/resources/","src/test/resources/"}) {
			Path path = Paths.get(base+resource);
			if(Files.exists (path)) {
				return Files.newInputStream(path);
			}
		}
		return null;
	}
	
	public static String get(String key) {
		return System.getProperty(key,PROPS.getProperty(key));
	}
	
	/** Returns the value for {@code key}, failing fast with a clear message if it is missing. */
	public static String getRequired(String key) {
		String value = get(key);
		if(value == null || value.isBlank()) {
			throw new IllegalStateException("Missing required configuration: '"+key
					+"'. Set it in config/config.properties or pass -D"+key+ "=<value>.");
		}
		return value;
	}
	
	public static String get(String key, String defaultValue) {
		String value = System.getProperty(key, PROPS.getProperty(key));
		return value != null? value : defaultValue;
	}
	
	public static boolean getBoolean(String key, boolean defaultValue) {
		String value = get(key);
		return value != null ? Boolean.parseBoolean(value.trim()):defaultValue;
	}

	public static int getInt(String key, int defaultValue) {
		String value = get(key);
		try {
			return value != null? Integer.parseInt(value.trim()):defaultValue;
		}catch (NumberFormatException e) {
			return defaultValue;
		}
	}
}
