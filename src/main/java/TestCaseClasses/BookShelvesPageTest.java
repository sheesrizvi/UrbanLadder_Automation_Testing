package TestCaseClasses;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;
import pageClasses.BookShelvesPage;
import pageClasses.GiftCardPage;
import pageClasses.HomePage;

public class BookShelvesPageTest extends BaseTestClass {

	HomePage hg;
	GiftCardPage card;
	BookShelvesPage shelves;

	@Test
	public void testBookshelveCase() throws Exception {
		logger = report.createTest("BookShelves Test : ");
		String runType = "local";

		/*
		 * To run Selenium Grid change runType to "Grid"
		 */

		if (runType.equalsIgnoreCase("local")) {
			invokeBrowser("chrome");
		} else {
			createRemoteDriver();
		}
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		hg = pageBase.OpenApplication();
		hg.handlePopUp();
		shelves = hg.bookshelveBtn();
		Thread.sleep(2000);
		shelves.adjustPriceRange();
		shelves.applyFilters();
		shelves.top3BookShelves();
	}
}
