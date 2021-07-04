package TestCaseClasses;


import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;
import pageClasses.BookShelvesPage;
import pageClasses.GetStudyChairPage;
import pageClasses.GiftCardPage;
import pageClasses.HomePage;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;


public class GetStudyChairTest extends BaseTestClass {
	
	HomePage hg;
	GetStudyChairPage chair;
	
	@Test
	public void testChairCase() throws Exception
	{
		logger = report.createTest("Get Study Chair Test : ");
		invokeBrowser("chrome");
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		hg = pageBase.OpenApplication();
		chair=hg.studyChair();
		chair.topStudyChairs();
	}

}