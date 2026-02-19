package com.blazedemo.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;
import com.blazedemo.utils.BaseTest;
import com.blazedemo.utils.DataProviders;

public class TC04FlightBookingDataDrivenTest extends BaseTest {
	
	@Test(dataProvider="PassengerDetail",dataProviderClass=DataProviders.class,groups={"Data-driven"})
	public void testBookingBydataDriven(
			String name,
	        String address,
	        String city,
	        String state,
	        String zipCode,
	        String cardType,
	        String cardNumber,
	        String month,
	        String year,
	        String nameOnCard) {

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
			purchase.enterName(name);
			purchase.enterAddress(address);
			purchase.enterCity(city);
			purchase.enterState(state);
			purchase.enterZipCode(zipCode);
			purchase.selectCardType(cardType);
			purchase.enterCardNumber(cardNumber);
			purchase.enterCardMonth(month);
			purchase.enterCardYear(year);
			purchase.enterNameOnCard(nameOnCard);

			logger.info("Submitting purchase");
			purchase.clickOnPurchaseFlight();

			ConfirmationPage conf = new ConfirmationPage(driver);

			logger.info("Verifying booking success");
			Assert.assertTrue(conf.isBookingSuccessful(), "Booking was not successful");

			logger.info("Flight booking test PASSED");
			
			
			
		} catch (Exception e) {
			logger.error("Flight booking test FAILED", e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}
}
