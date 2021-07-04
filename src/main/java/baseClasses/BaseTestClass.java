package baseClasses;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Platform;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import org.testng.annotations.AfterMethod;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.ExtendReportManager;

public class BaseTestClass {

	public WebDriver driver;
	public ExtentReports report = ExtendReportManager.getReportInstance();
	public ExtentTest logger;

	/****************** Invoke Browser ***********************/
	public void invokeBrowser(String browserName) {

		try {
			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("Mozila")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
		} catch (Exception e) {
		    //reportFail(e.getMessage());
			System.out.println(e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public WebDriver createRemoteDriver() throws MalformedURLException {
		
		String browserName = "chrome";
		/*
		 * to run for firefox rename browserName to "firefox"
		 */
				
		
        DesiredCapabilities capability;
        switch (browserName){

            case "chrome":

                //capability.setPlatform(Platform.WIN10);
                ChromeOptions chromeOptions = new ChromeOptions();
                capability = DesiredCapabilities.chrome();

                URL uri= new URL("http://localhost:5555/wd/hub");
                driver = new RemoteWebDriver(uri, capability);
                //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
                
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        		driver.manage().window().maximize();
               
                break;
            case "firefox":

                capability = DesiredCapabilities.firefox();
                capability.setBrowserName("firefox");
                capability.setPlatform(Platform.WIN10);
                driver = new RemoteWebDriver(new URL("http://localhost:5454/wd/hub"), capability);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        		driver.manage().window().maximize();
                break;
            default: throw new RuntimeException(" browser not implemented yet")   ;



        }
        return driver;
    }
	
	

	@AfterMethod
	public void flushReports() {
		report.flush();
		driver.close();
	}

}
