package com.saucedemo.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucedemo.config.ConfigReader;

/**
 * Base for all page objects: holds the {@link WebDriver} and a shared
 * {@link WebDriverWait}, and provides small explicit-wait helpers so page
 * classes stay declarative
 */
public class BasePage {

	protected final WebDriver driver;
	protected final WebDriverWait wait;
	
	protected BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,
				Duration.ofSeconds(ConfigReader.getInt("explicit.timeout",10)));
			
	}
	
	protected WebElement visible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	protected WebElement clickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	protected void click(By locator) {clickable(locator).click();}
	
	protected void type(By locator, String text) {
		WebElement element = clickable(locator);
		element.click();
		element.clear();
		element.sendKeys(text);
		//Some browser/driver combinations occasionally drop characters when
		//typing into consecutive fields; verify and retry once if needed.
		
		if(!text.equals(element.getAttribute("value"))) {
			element.clear();
			element.sendKeys(text);
		}
		
	}
	
	protected String text(By locator) {return visible(locator).getText().trim();}
	
	protected boolean isDisplayed(By locator) {
		try {
			return visible(locator).isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
	
	protected List<WebElement> all(By locator) {return driver.findElements(locator);}
	
	protected List<String> texts (By locator){
		return all(locator).stream()
				.map(WebElement::getText)
				.map(String::trim)
				.collect(Collectors.toList());
	}
	
	protected void selectByValue(By locator,String value) {
		new Select(visible(locator)).selectByValue(value);
	}
	
	protected int count(By locator) {return all(locator).size();}
	
	public void open(String url) {driver.get(url);}
	
}
