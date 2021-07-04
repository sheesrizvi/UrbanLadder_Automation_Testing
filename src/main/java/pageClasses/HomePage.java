package pageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;

public class HomePage extends PageBaseClass {

	public HomePage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	@FindBy(xpath = "//*[@id=\"authentication_popup\"]/div[1]/div/div[2]/a[1]")
	public static WebElement popupCloseBtn;

	@FindBy(xpath = "//*[@id=\"topnav_wrapper\"]/ul/li[5]/span")
	public static WebElement storageBtn;

	@FindBy(xpath = "//*[@id=\"topnav_wrapper\"]/ul/li[5]/div/div/ul/li[1]/ul/li[2]/a/span")
	public static WebElement shelvesBtn;
	
	@FindBy(xpath = "//*[@id=\"topnav_wrapper\"]/ul/li[6]/span")
	public static WebElement studyBtn;
	
	@FindBy(xpath = "//*[@id=\"topnav_wrapper\"]/ul/li[6]/div/div/ul/li[2]/ul/li[1]/a/span")
	public static WebElement studychairBtn;

	@FindBy(xpath = "//*[@id='header']/section/div/ul[2]/li[3]/a")
	public static WebElement giftCardBtn;
	
	@FindBy(xpath = "//*[@id=\"topnav_wrapper\"]/ul/li[10]/span")
	public static WebElement collectonTab;
	

	public void handlePopUp() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(popupCloseBtn));
		logger.log(Status.INFO, "PopUp Showed up");
		popupCloseBtn.click();
		logger.log(Status.PASS, "Successfully Closed the popup");
		Thread.sleep(2000);
	}
	
	public void beingAtHome() throws Exception
	{
		collectonTab.click();
		logger.log(Status.PASS, "Successfully clicked the Collections option");
		List<WebElement> submenu = driver.findElements(By.xpath("//*[@id='topnav_wrapper']/ul/li[10]//div[@class='taxontype']"));
		Thread.sleep(2000);
		for(WebElement ele:submenu)
		{
			String menu = ele.getText();
			if(menu.equalsIgnoreCase("Being-At-Home"))
			{
				List <WebElement> athome= driver.findElements(By.xpath("//*[@id=\"topnav_wrapper\"]/ul/li[11]/div/div/ul/li[1]/ul/li/a/span"));
				int len=athome.size();
				for(int i=0;i<len;i++) {
					String lol=athome.get(i).getText();
					System.out.println(lol);
				}
				logger.log(Status.PASS, "Successfully displayed the all items of Being-At-Home menu");
				return;
			}
		}
		reportFail("Being-At-Home sub-menu is Missing in Collection Menu");
	}

	public BookShelvesPage bookshelveBtn() {
		storageBtn.click();
		logger.log(Status.PASS, "Successfully clicked the Storage menu");
		shelvesBtn.click();
		logger.log(Status.PASS, "Successfully clicked the Bookshelves menu");
		BookShelvesPage shelve = new BookShelvesPage(driver, logger);
		PageFactory.initElements(driver, shelve);
		return shelve;
	}
	
	public GetStudyChairPage studyChair()
	{
		studyBtn.click();
		logger.log(Status.PASS, "Successfully clicked the Study menu");
		studychairBtn.click();
		logger.log(Status.PASS, "Successfully clicked the Study Chair menu");
		GetStudyChairPage chair = new GetStudyChairPage(driver,logger);
		PageFactory.initElements(driver, chair);
		return chair;
	}

	public GiftCardPage clickGiftCardBtn() {
		giftCardBtn.click();
		logger.log(Status.PASS, "Successfully clicked the GiftCard option");
		GiftCardPage gf = new GiftCardPage(driver, logger);
		PageFactory.initElements(driver, gf);
		return gf;
	}

}
