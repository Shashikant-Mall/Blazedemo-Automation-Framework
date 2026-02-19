package com.blazedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.ReservePage;
import com.blazedemo.utils.BaseTest;

public class TC02SearchFlightWithValidCities extends BaseTest {
	
  @Test(groups= {"Functional"})
  public void searchFlightTest() {
	  
	  HomePage home = new HomePage(driver);
	  
	  logger.info("Selecting departure and destination cities");
	  home.selectCities("Portland", "Dublin");
	  
	  logger.info("Clicking Find Flights");
	  home.clickFindFlights();
	  
	  ReservePage rg=new ReservePage(driver);
	  Assert.assertTrue(rg.isFlightResultDisplayed());
	  
  }
  
  
}
