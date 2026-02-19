package com.blazedemo.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;
import com.blazedemo.utils.BaseTest;

public class FlightBookingTest extends BaseTest {

	@Test(groups = {"Smoke"})
	public void bookFlight() {

		logger.info("Starting flight booking test");

		try {
			HomePage home = new HomePage(driver);

			logger.info("Selecting cities");
			home.selectCities("Boston", "New York");

			logger.info("Searching flights");
			home.clickFindFlights();

			ReservePage reserve = new ReservePage(driver);
			logger.info("Choosing flight");
			reserve.chooseFlight();

			PurchasePage purchase = new PurchasePage(driver);

			logger.info("Entering passenger details");
			purchase.enterName("Mack");
			purchase.enterAddress("Dwarka");
			purchase.enterCity("Delhi");
			purchase.enterState("NewDelhi");
			purchase.enterZipCode("453426");
			purchase.selectCardType("American Express");
			purchase.enterCardNumber("4278953792586");
			purchase.enterCardMonth("10");
			purchase.enterCardYear("2029");
			purchase.enterNameOnCard("Mack");

			logger.info("Submitting purchase");
			purchase.clickOnPurchaseFlight();

			ConfirmationPage conf = new ConfirmationPage(driver);

			logger.info("Verifying booking success");
			Assert.assertTrue(conf.isBookingSuccessful(), "Booking was not successful");

			logger.info("Flight booking test PASSED");
			
		}
		catch (Exception e) {
			logger.error("Flight booking test FAILED", e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}

	}

}
