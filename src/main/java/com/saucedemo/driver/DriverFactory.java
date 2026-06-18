package com.saucedemo.driver;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.saucedemo.config.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Creates {@link WebDriver} instances based on configuration
 * ({@code browser}, {@code headless}). Browser driver binaries are resolved and
 * downloaded by WebDriverManager, Password manager and leak-detection pop-ups are 
 * disabled so the "Change your password" dialog (triggered by SauceDemo's well-
 * known demo credentials) cannot steal focus and break post-login interaction.
 */

public final class DriverFactory {
	
	private DriverFactory() {
		
	}
	
	public static WebDriver createDriver() {
		String browser = ConfigReader.get("browser", "chrome").toLowerCase();
		boolean headless = ConfigReader.getBoolean("headless",true);
		
		WebDriver driver;
		switch (browser) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if(headless) {
				firefoxOptions.addArguments ("-headless");
			}
			firefoxOptions.addArguments("--width=1920","--height=1080");
			firefoxOptions.addPreference("signon.rememberSignons",false);
			firefoxOptions.addPreference("signon.management.page.breach-alerts.enabled",false);
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			if(headless) {
				edgeOptions.addArguments("--headless=new");
			}
			edgeOptions.addArguments("--no-sandbox","--disable-dev-shm-usage","--window-size=1920,1080");
			edgeOptions.addArguments("--disable-features=PasswordLeakDetection,PasswordChange");
			edgeOptions.setExperimentalOption("prefs", passwordPrefs());
			driver = new EdgeDriver(edgeOptions);
			break;
		case "chrome":
			default:
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				if(headless) {
					chromeOptions.addArguments("--headless=new");
				}
				chromeOptions.addArguments(
						"--no-sandbox",
						"--disable-dev-shm-usage",
						"--disable-gpu",
						"--window-size = 1920,1080");
				chromeOptions.addArguments("--disable-features=PasswordLeakDetection,PasswordChange");
				chromeOptions.setExperimentalOption("prefs", passwordPrefs());
				driver = new ChromeDriver(chromeOptions);
				break;
		}
		
		if(!headless) {
			driver.manage().window().maximize();
		}
		return driver;
	}
	
	/** Chromium prefs that disable the password manager and breach/leak detection. */
	private static Map<String, Object> passwordPrefs(){
		Map<String,Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled",false );
		prefs.put("profile.password_manager_leak_detection", false);
		return prefs;
	}
		
}
