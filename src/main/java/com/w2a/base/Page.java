package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.w2a.listeners.CustomListeners;
import com.w2a.utitilities.ExcelReader;
import com.w2a.utitilities.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Page {
	
	

		public static WebDriver driver;
		public static Properties config = new Properties();
		public static Properties OR = new Properties();
		public static FileInputStream fis;
		public static Logger log = Logger.getLogger("devpinoyLogger");
		public static ExcelReader excel = new ExcelReader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\excel\\testdata.xlsx");
		public static WebDriverWait wait;
	//	public ExtentReports rep = ExtentManager.getInstance();
	
		public static ExtentTest test;
		public static String browser;
		public static WebElement dropdown;

		public static TopMenu menu;
		/*
		 * Logs,
		 * Properties - OR, Config
		 * Excel
		 * Implicit and ExplicitWait
		 * Extent Reports
		 * 
		 * 
		 * 
		 * 
		 */

		public Page() {

			if (driver == null) {

				try {
					fis = new FileInputStream(System.getProperty("user.dir")
							+ "\\src\\test\\resources\\properties\\properties\\config.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					config.load(fis);
					log.info("Config properties loaded !!!");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					fis = new FileInputStream(
							System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\properties\\OR.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					OR.load(fis);
					log.info("OR properties loaded !!!");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//Jenkins Browser filter configuration
				if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

					browser = System.getenv("browser");
				} else {

					browser = config.getProperty("browser");

				}

				config.setProperty("browser", browser);

				
				
				if (config.getProperty("browser").equals("firefox")) {

					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					log.info("Launching Firefox !!!");

				} else if (config.getProperty("browser").equals("chrome")) {
				
					WebDriverManager.chromedriver().setup();

				

				driver = new ChromeDriver();
				log.info("Launching Chrome !!!");
				}else if (config.getProperty("browser").equals("ie")) {

					WebDriverManager.iedriver().setup();
					driver = new InternetExplorerDriver();
					log.info("Launching InternetExplorer !!!");

				}

				
			
			driver.get(config.getProperty("testsiteurl"));
			log.info("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
		//	wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("explicit.wait"))));
			
		/*	try {
				DbManager.setMysqlDbConnection();
				log.info("DB Connection established !!!");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
			wait = new WebDriverWait(driver, 5);
			
			
		}

		}
		
		public static void quit() {
			driver.quit();
			log.info("Test Execution completed !!!");
		}
		
		
		//Common Keywords
		public static void click(String locatorKey) {
			try {
				if (locatorKey.endsWith("_XPATH")) {
					driver.findElement(By.xpath(OR.getProperty(locatorKey))).click();
				} else if (locatorKey.endsWith("_CSS")) {
					driver.findElement(By.cssSelector(OR.getProperty(locatorKey))).click();
				} else if (locatorKey.endsWith("_ID")) {
					driver.findElement(By.id(OR.getProperty(locatorKey))).click();
				}

				log.info("Clicking on an Element : " + locatorKey);
				CustomListeners.test.log(Status.INFO, "Clicking on an Element : " + locatorKey);
			} catch (Throwable t) {

				log.error("Error while Clicking on an Element : " + locatorKey + " error message : " + t.getMessage());
				CustomListeners.test.log(Status.FAIL,
						"Error while Clicking on an Element : " + locatorKey + " error message : " + t.getMessage());
				Assert.fail(t.getMessage());

			}

		}

		public static boolean isElementPresent(String locatorKey) {

			try {
				if (locatorKey.endsWith("_XPATH")) {
					driver.findElement(By.xpath(OR.getProperty(locatorKey)));
				} else if (locatorKey.endsWith("_CSS")) {
					driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
				} else if (locatorKey.endsWith("_ID")) {
					driver.findElement(By.id(OR.getProperty(locatorKey)));
				}
			} catch (Throwable t) {

				log.info("Element not found : " + locatorKey);
				CustomListeners.test.log(Status.INFO, "Element not found : " + locatorKey);
				return false;

			}

			log.info("Finding an Element : " + locatorKey);
			CustomListeners.test.log(Status.INFO, "Finding an Element : " + locatorKey);
			return true;
		}

		public static void type(String locatorKey, String value) {
			try {
				if (locatorKey.endsWith("_XPATH")) {
					driver.findElement(By.xpath(OR.getProperty(locatorKey))).sendKeys(value);
				} else if (locatorKey.endsWith("_CSS")) {
					driver.findElement(By.cssSelector(OR.getProperty(locatorKey))).sendKeys(value);
				} else if (locatorKey.endsWith("_ID")) {
					driver.findElement(By.id(OR.getProperty(locatorKey))).sendKeys(value);
				}
				log.info("typing in an Element : " + locatorKey + " entered the value as : " + value);
				CustomListeners.test.log(Status.INFO,
						"typing in an Element : " + locatorKey + " entered the value as : " + value);
			} catch (Throwable t) {

				log.error("Error while typing in an Element : " + locatorKey + " error message : " + t.getMessage());
				CustomListeners.test.log(Status.FAIL,
						"Error while typing in an Element : " + locatorKey + " error message : " + t.getMessage());
				Assert.fail(t.getMessage());

			}

		}
		
		
		
		public static void select(String locatorKey, String value) {
			try {
				
				
				if (locatorKey.endsWith("_XPATH")) {
					dropdown = driver.findElement(By.xpath(OR.getProperty(locatorKey)));
				} else if (locatorKey.endsWith("_CSS")) {
					dropdown = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
				} else if (locatorKey.endsWith("_ID")) {
					dropdown = driver.findElement(By.id(OR.getProperty(locatorKey)));
				}
				
				Select select = new Select(dropdown);
				select.selectByVisibleText(value);
				log.info("Selecting an Element : " + locatorKey + " selected the value as : " + value);
				CustomListeners.test.log(Status.INFO,
						"Selecting an Element : " + locatorKey + " selected the value as : " + value);
			} catch (Throwable t) {

				log.error("Error while selecting an Element : " + locatorKey + " error message : " + t.getMessage());
				CustomListeners.test.log(Status.FAIL,
						"Error while selecting an Element : " + locatorKey + " error message : " + t.getMessage());
				Assert.fail(t.getMessage());

			}

		}
		public boolean isElementPresent(By by) {

			try {

				driver.findElement(by);
				return true;

			} catch (NoSuchElementException e) {

				return false;

			}

		}

		public static void verifyEquals(String expected, String actual) throws IOException {

			try {

				Assert.assertEquals(actual, expected);

			} catch (Throwable t) {

				Utilities.captureScreenshot();
				// ReportNG
				Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
				Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + "><img src=" + Utilities.screenshotName
						+ " height=200 width=200></img></a>");
				Reporter.log("<br>");
				Reporter.log("<br>");
				// Extent Reports
				test.log(Status.FAIL, " Verification failed with exception : " + t.getMessage());
				
		
		//	test.log(Status.FAIL, test.addScreenCapture(Utilities.screenshotName));
			
		    test.log(Status.FAIL,"Failed Screenshot");
            try {
            test.addScreenCaptureFromPath(Utilities.screenshotName);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
	}

		}
}

