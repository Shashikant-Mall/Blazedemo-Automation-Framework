package com.blazedemo.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReservePage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public ReservePage(WebDriver driver) {
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h3[contains(text(),'Flights from')]")
	WebElement searchConfirm;
	
	@FindBy(xpath="//tbody/tr[2]/td[1]/input[1]")
	WebElement chooseFlightBtn;
	
	public boolean isFlightResultDisplayed() {
		
		return wait.until(ExpectedConditions.visibilityOf(searchConfirm)).isDisplayed();
	}
	
	public void chooseFlight() {
		
		chooseFlightBtn.click();
		
	}

}
