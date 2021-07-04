package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

import baseClasses.PageBaseClass;

public class GiftCardPage extends PageBaseClass {
	
	public GiftCardPage(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}

	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[1]/ul/li[3]")
	public static WebElement giftCardType;
	
	@FindBy(xpath = "//*[@id=\"ip_2251506436\"]")
	public static WebElement amount;
	
	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[2]/div/section[2]/div[4]/select[1]")
	public static WebElement monthYear;
	
	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[2]/div/section[2]/div[4]/select[2]")
	public static WebElement day;
	
	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[2]/div/section[2]/button")
	public static WebElement nextBtn;
	
	@FindBy(xpath = "//*[@id=\"ip_4036288348\"]")
	public static WebElement recipientName;
	
	@FindBy(xpath = "//*[@id=\"ip_137656023\"]")
	public static WebElement recipientEmail;
	
	@FindBy(xpath = "//*[@id=\"ip_1082986083\"]")
	public static WebElement sendersName;
	
	@FindBy(xpath = "//*[@id=\"ip_4081352456\"]")
	public static WebElement sendersEmail;
	
	@FindBy(xpath = "//*[@id=\"ip_2121573464\"]")
	public static WebElement sendersPhone;
	
	@FindBy(xpath = "//*[@id=\"ip_582840596\"]")
	public static WebElement message;
	
	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[3]/form/button")
	public static WebElement confirmBtn;
	
	@FindBy(xpath = "//*[@id=\"app-container\"]/div/main/section/section[4]/div[2]/button/span[1]")
	public static WebElement paymentbtn;
	
	public void selectDropDown(WebElement element, String value)
	{
		selectDropDownValue(element,value);
	}
	
}

