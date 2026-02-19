package com.blazedemo.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//a[normalize-space()='home']")
	WebElement homeIcon;
	
	@FindBy(xpath="//select[@name='fromPort']")
	WebElement departCity;
	
	@FindBy(xpath="//select[@name='toPort']")
	WebElement destCity;
	
	@FindBy(xpath="//input[@value='Find Flights']")
	WebElement findFlightButton;
	
    public boolean isHomePageDisplayed() {
		return wait.until(ExpectedConditions.visibilityOf(homeIcon)).isDisplayed();
	}
    
    public boolean isDropdownVisible() {
    	 
    	  boolean isDepartVisible= wait.until(ExpectedConditions.visibilityOf(departCity)).isDisplayed();
    	  boolean isDestVisible= wait.until(ExpectedConditions.visibilityOf(destCity)).isDisplayed();
    	  
    	  return isDepartVisible && isDestVisible;
    	
    }
	
	public void selectCities(String from,String to) {
		
		Select departCities = new Select(departCity);
		departCities.selectByContainsVisibleText(from);
		
		Select destCities = new Select(destCity);
		destCities.selectByContainsVisibleText(to);
		
	}
	public void clickFindFlights() {
		
		wait.until(ExpectedConditions.elementToBeClickable(findFlightButton)).click();
		
	}
	public List<String> listOfDestinationCity() {
		
		
		Select destCities = new Select(destCity);
	    List<WebElement> options = destCities.getOptions();
	    List<String> cityNames = new ArrayList<>();

	    for (WebElement op : options) {
	        cityNames.add(op.getText());
	    }
	    return cityNames;
	}
	
	
	

}
