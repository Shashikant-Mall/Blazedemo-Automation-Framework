package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PurchasePage {
	
	private WebDriver driver;
	
	public PurchasePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@id='inputName']")
	WebElement name;
	
	@FindBy(xpath="//input[@id='address']")
	WebElement address;
	
	@FindBy(xpath="//input[@id='city']")
	WebElement city;
	
	@FindBy(xpath="//input[@id='state']")
	WebElement state;
	
	@FindBy(xpath="//input[@id='zipCode']")
	WebElement zipCode;
	
	@FindBy(xpath="//select[@id='cardType']")
	WebElement cardType;
	
	@FindBy(xpath="//input[@id='creditCardNumber']")
	WebElement cardNumber;
	
	@FindBy(xpath="//input[@id='creditCardMonth']")
	WebElement month;
	
	@FindBy(xpath="//input[@id='creditCardYear']")
	WebElement year;
	
	@FindBy(xpath="//input[@id='nameOnCard']")
	WebElement nameOnCard;
	
	@FindBy(xpath="//input[@value='Purchase Flight']")
	WebElement puchaseButton;
	
	public void enterName(String pname) {
		name.clear();
		name.sendKeys(pname);
	}
	public void enterAddress(String paddress) {
		address.clear();
		address.sendKeys(paddress);
	}
	public void enterCity(String pcity) {
		city.clear();
		city.sendKeys(pcity);
	}
	public void enterState(String pstate) {
		state.clear();
		state.sendKeys(pstate);
	}
	public void enterZipCode(String pZip) {
		zipCode.clear();
		zipCode.sendKeys(pZip);
	}
	public void selectCardType(String cType) {
		Select type = new Select(cardType);
		type.selectByContainsVisibleText(cType);
	}
	public void enterCardNumber(String Cnum) {
		cardNumber.clear();
		cardNumber.sendKeys(Cnum);
	}
	public void enterCardMonth(String Cmonth) {
		month.clear();
		month.sendKeys(Cmonth);
	}
	public void enterCardYear(String Cyear) {
		year.clear();
		year.sendKeys(Cyear);
	}
	public void enterNameOnCard(String Cname) {
		nameOnCard.clear();
		nameOnCard.sendKeys(Cname);
	}
	public void clickOnPurchaseFlight() {
		puchaseButton.click();
	}
	
	
	
	
}
