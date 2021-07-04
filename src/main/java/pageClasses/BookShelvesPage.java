package pageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;

public class BookShelvesPage extends PageBaseClass {

	public BookShelvesPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	@FindBy(xpath = "//*[@id='filters-form']/div[1]/div/div/ul/li[1]/div[1]")
	public static WebElement priceBtn;

	@FindBy(xpath = "//div[@class='noUi-handle noUi-handle-upper']")
	public static WebElement priceSlider;

	@FindBy(xpath = "//*[@id=\"filters-form\"]/div[1]/div/div/ul/li[2]/div[1]")
	public static WebElement storageType;

	@FindBy(xpath = "//*[@id=\"filters_storage_type_Open\"]")
	public static WebElement openType;

	@FindBy(xpath = "//*[@id=\"filters_availability_In_Stock_Only\"]")
	public static WebElement outOfStock;

	public void adjustPriceRange() {
		priceBtn.click();
		Actions action = new Actions(driver);
		action.dragAndDropBy(priceSlider, -200, 0).build().perform();
		logger.log(Status.PASS, "Filtered the price between 0 to 15000");
	}

	public void applyFilters() throws Exception {
		Thread.sleep(2000);
		storageType.click();
		logger.log(Status.PASS, "Storage Type is Clicked");
		openType.click();
		logger.log(Status.PASS, "Storage Type Open is Successfully Selected");
		outOfStock.click();
		logger.log(Status.PASS, "Exclude out of stock is selected successfully");
	}

	public void top3BookShelves() throws Exception {
		Thread.sleep(2000);
		List<WebElement> name = driver.findElements(By.xpath("//*[@class=\"name\" and @itemprop=\"name\"]"));
		List<WebElement> price = driver.findElements(By.xpath("//div[@class='price-number']/span"));
		List<WebElement> details = driver.findElements(By.xpath("//*[@class=\"small\"]"));
		logger.log(Status.PASS, "Top 3 recommended BookShelves is displayed");
		for (int i = 0; i < Math.min(price.size(), (int) 3); i++) {
			String tempPrice = price.get(i).getText();
			tempPrice = tempPrice.substring(1);
			System.out.println(name.get(i).getText() + "---------------------- Rs." + tempPrice
					+ "----------------------" + details.get(i).getText());
		}
	}
}
