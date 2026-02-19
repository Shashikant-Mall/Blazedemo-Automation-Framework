package com.blazedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.ReservePage;
import com.blazedemo.utils.BaseTest;

public class TC07SameDepartAndDestinCity extends BaseTest {

	@Test(groups={"Negative"})
	public void testSameDepartAndDestination() {

		logger.info("Test started: Same Departure and Destination City");
		HomePage home = new HomePage(driver);

		logger.info("Checking if Paris exists in destination dropdown");
		Assert.assertTrue(home.listOfDestinationCity().contains("Paris"),
				"Paris not available in destination dropdown");

		logger.info("Selecting Paris as departure and destination");
		home.selectCities("Paris", "Paris");
		
		logger.info("Clicking Find Flights button");
		home.clickFindFlights();

		ReservePage reserve = new ReservePage(driver);

		logger.info("Validating flight results page");
		Assert.assertTrue(reserve.isFlightResultDisplayed(), 
				"Reserve page not displayed for same city selection");
		
		logger.info("Test completed successfully");
	}
	
}
