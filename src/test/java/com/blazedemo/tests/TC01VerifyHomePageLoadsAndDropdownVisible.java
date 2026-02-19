package com.blazedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.blazedemo.pages.HomePage;
import com.blazedemo.utils.BaseTest;

public class TC01VerifyHomePageLoadsAndDropdownVisible extends BaseTest {

	@Test(groups= {"Smoke"})
	public void testHomePageLoadsAndDropdownVisible() {

		logger.info("Starting HomePage and Dropdown load test");
		HomePage home = new HomePage(driver);

		logger.info("Home page Loaded");
		Assert.assertTrue(home.isHomePageDisplayed(), "Home Page not Loaded");

		logger.info("Cities Dropdowns visible");
		Assert.assertTrue(home.isDropdownVisible(), "Cities Dropdowns not visible");

		logger.info("Completed HomePage and Dropdown load test");
	}
}
