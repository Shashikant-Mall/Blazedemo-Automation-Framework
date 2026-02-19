package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
	
	private WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//h1[normalize-space()='Thank you for your purchase today!']")
	WebElement confMessage;
	
	public boolean isBookingSuccessful() {
		
		String confirmationMessage=confMessage.getText();
		return confirmationMessage.contains("Thank you for your purchase today!");
	}
}
