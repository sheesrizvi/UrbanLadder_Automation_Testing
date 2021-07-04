package pageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;

public class GetStudyChairPage extends PageBaseClass {

	public GetStudyChairPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	@FindBy(xpath = "//*[@class=\"name\" and @itemprop=\"name\"]")
	public static List<WebElement> chairName;

	@FindBy(xpath = "//*[@class=\"price-number\"]/span")
	public static List<WebElement> chairPrice;

	@FindBy(xpath = "//*[@class=\"small\"]")
	public static List<WebElement> chairDetails;

	public void topStudyChairs() {
		logger.log(Status.PASS, "TOP Recommended Study Chairs is displayed");
		for (int i = 0; i < 3; i++) {
			String tempPrice = chairPrice.get(i).getText();
			tempPrice = tempPrice.substring(1);
			System.out.println(chairName.get(i).getText() + "---------------------- Rs." + tempPrice
					+ "----------------------" + chairDetails.get(i).getText());
		}
	}

}
