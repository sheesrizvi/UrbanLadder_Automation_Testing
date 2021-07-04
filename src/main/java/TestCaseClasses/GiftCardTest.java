package TestCaseClasses;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;
import pageClasses.GiftCardPage;
import pageClasses.HomePage;
import utilities.TestDataProvider;

public class GiftCardTest extends BaseTestClass {

	HomePage hg;
	GiftCardPage card;

	@Test(dataProvider = "addGiftCardDetails")
	public void testGiftCardCase(Hashtable<String, String> testData) throws Exception {
		logger = report.createTest("Gift Card Details Test : " + testData.get("comments"));
		invokeBrowser("chrome");
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		hg = pageBase.OpenApplication();
	    hg.handlePopUp();
		card = hg.clickGiftCardBtn();
		GiftCardPage.giftCardType.click();
		GiftCardPage.amount.sendKeys("2000");
		GiftCardPage.monthYear.click();
		try {
			card.selectDropDown(GiftCardPage.monthYear, testData.get("monthYear"));
			card.selectDropDown(GiftCardPage.day, "20");
			GiftCardPage.nextBtn.click();
		}catch(Exception e) {
			logger.log(Status.FAIL,e.getMessage());
			Assert.fail();
		}
		GiftCardPage.recipientName.sendKeys(testData.get("recipientName"));
		logger.log(Status.INFO, "Successfully Entered the recipient name");
		GiftCardPage.recipientEmail.sendKeys(testData.get("recipientEmail"));
		logger.log(Status.INFO, "Successfully Entered the recipient email");
		GiftCardPage.sendersName.sendKeys(testData.get("senderName"));
		logger.log(Status.INFO, "Successfully Entered the sender's name");
		GiftCardPage.sendersEmail.sendKeys(testData.get("senderEmail"));
		logger.log(Status.INFO, "Successfully Entered the sender's email");
		GiftCardPage.sendersPhone.sendKeys(testData.get("senderPhone"));
		logger.log(Status.INFO, "Successfully Entered the sender's phone");
		GiftCardPage.message.sendKeys(testData.get("message"));
		logger.log(Status.INFO, "Successfully Entered the message on GiftCard");
		GiftCardPage.confirmBtn.click();
		logger.log(Status.INFO, "CLicked on Confirm button");
		card.veriyElementIsDisplayed(GiftCardPage.paymentbtn);
	}

	@DataProvider
	public Object[][] addGiftCardDetails() {
		return TestDataProvider.getTestData("TestDataFile.xlsx", "TestCase1", "GiftCard");
	}

}
