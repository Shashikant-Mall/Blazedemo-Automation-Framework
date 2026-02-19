package com.blazedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;
import com.blazedemo.utils.BaseTest;

public class TC05BlankCreditCard extends BaseTest {

	@Test(groups= {"Negative"})
	public void testBlankCreditcard() {

		logger.info("===== Starting TC05BlankCreditCard =====");

		try {
			HomePage home = new HomePage(driver);

			logger.info("Selecting cities");
			home.selectCities("Paris", "New York");

			logger.info("Searching flights");
			home.clickFindFlights();

			ReservePage reserve = new ReservePage(driver);
			logger.info("Choosing flight");
			reserve.chooseFlight();

			PurchasePage purchase = new PurchasePage(driver);

			logger.info("Entering passenger details");
			purchase.enterName("Ajju");
			purchase.enterAddress("Kakrola");
			purchase.enterCity("Delhi");
			purchase.enterState("NewDelhi");
			purchase.enterZipCode("453426");
			
			logger.info("Leaving credit card details blank intentionally");
			purchase.selectCardType("");
			purchase.enterCardNumber("");
			purchase.enterCardMonth("");
			purchase.enterCardYear("");
			purchase.enterNameOnCard("");

			logger.info("Submitting purchase");
			purchase.clickOnPurchaseFlight();

			ConfirmationPage conf = new ConfirmationPage(driver);

			logger.info("Checking booking status");
            boolean bookingSuccess = conf.isBookingSuccessful();

            logger.info("Booking success status: " + bookingSuccess);
            Assert.assertFalse(
                    bookingSuccess,
                    "BUG: Booking succeeded even with blank credit card details");

            logger.info("===== TC05BlankCreditCard PASSED =====");
			
		} catch (Exception e) {
			logger.error("Flight booking test FAILED", e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}

}
