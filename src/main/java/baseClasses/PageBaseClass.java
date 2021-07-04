package baseClasses;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import pageClasses.HomePage;
import utilities.DateUtil;

public class PageBaseClass extends BaseTestClass {
	
	public ExtentTest logger;

	public PageBaseClass(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	/****************** OpenApplication ***********************/
	public HomePage OpenApplication() {
		logger.log(Status.INFO, "Opening the WebSite");
		driver.get("https://www.urbanladder.com/");
		logger.log(Status.PASS, "Successfully Opened the https://www.urbanladder.com/");
		HomePage hg = new HomePage(driver, logger);
		PageFactory.initElements(driver, hg);
		return hg;
	}
	
	/****************** Select value From DropDown ***********************/
	public void selectDropDownValue(WebElement webElement, String value){
		try {
			Select select = new Select(webElement);
			select.selectByVisibleText(value);
			logger.log(Status.PASS, "Element selected successfully from dropdown : " + value);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public void veriyElementIsDisplayed(WebElement webElement){
		try {
			if(webElement.isDisplayed()){
				reportPass("Gift has been created successfully ");
			}else {
				reportFail("Wrong information Fed-Up : Verify The Details in attached ScreenShot");
			}
			
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
	}
	
	/****************** Reporting Functions ***********************/
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	/****************** Capture Screen Shot ***********************/
	public void takeScreenShotOnFailure() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
