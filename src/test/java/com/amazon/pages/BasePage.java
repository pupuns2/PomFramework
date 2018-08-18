package com.amazon.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.amazon.utils.JavaUtils;
import com.amazon.utils.Path;
import com.relevantcodes.extentreports.ExtentTest;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


/**
 * 
 * @author aravindanathdm
 *
 */
public class BasePage extends JavaUtils {
	public static String subject;
	public WebDriver driver;
//	public ExtentTest test;
	Logger log = Logger.getLogger("devpinoyLogger");

	// public static String screenshotFile;

//	public BasePage(WebDriver driver) {
//		this.driver = driver;
//
//		// PropertyConfigurator.configure("Log4j.properties");
//	}
	
	public BasePage(WebDriver driver  ){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public LoginPage gotoLoginPage() throws IOException {
		log.info("User is attempting to launch  GV application.");
		driver.get(getPropValue("URL"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		Reporter.log("URL is launched and user is on login page ");
		log.info("Application is sucessfully launched. ");
		return new LoginPage(driver);
	}

	public void waitingForTheElementToDisappear() {
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='loading-widget-div']")));
	}

	public void Dropdown(WebElement element, String value) {
		Select selectList = new Select(element);
		selectList.selectByVisibleText(value);
		Assert.assertEquals(selectList.getFirstSelectedOption().getText(), selectList.equals(value));
		System.err.println("Dropdown Value-->" + value);
	}

	public void selectStageDropdown(WebElement element, String value) {
		log.info("selectDropdown method is attempting to select the value from the Dropdown ");
		waitingForTheElementToLoad(element);
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(value);
		// Print all the options for the selected drop down and select one
		// option of your choice
		// Get the size of the Select element
		List<WebElement> oSize = dropdown.getOptions();
		int iListSize = oSize.size();
		log.info("selectDropdown method has collected all the values in the dropdown. ");
		// Setting up the loop to print all the options
		for (int i = 0; i < iListSize; i++) {
			// Storing the value of the option
			String sValue = dropdown.getOptions().get(i).getText();
			// Printing the stored value
			System.err.println("Dropdown value ----> " + sValue);
			// Putting a check on each option that if any of the option is equal
			// to "VALUE" then select it
			if (sValue.equals(value)) {
				dropdown.selectByIndex(i);
				break;
			}
		}
		log.info("selectDropdown method has selected values in the dropdown. ");
	}

	public void selectDropdown(WebElement element, String value) {
		log.info("selectDropdown method is attempting to select the value from the Dropdown ");
		waitingForTheElementToLoad(element);
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(value);
		// Print all the options for the selected drop down and select one
		// option of your choice
		// Get the size of the Select element
		List<WebElement> oSize = dropdown.getOptions();
		int iListSize = oSize.size();
		log.info("selectDropdown method has collected all the values in the dropdown. ");
		// Setting up the loop to print all the options
		for (int i = 0; i < iListSize; i++) {
			// Storing the value of the option
			String sValue = dropdown.getOptions().get(i).getText();
			// Printing the stored value

			// System.out.println("Dropdown value " + sValue);
			// Putting a check on each option that if any of the option is equal
			// to "VALUE" then select it
			if (sValue.equals(value)) {
				dropdown.selectByIndex(i);
				break;
			}
		}
		log.info("selectDropdown method has selected values in the dropdown. ");
	}

	/**
	 * This method is used for selecting multiple values
	 */
	public static void selectMultipleValues(String multipleVals, WebElement element) {

		String multipleSel[] = multipleVals.split(",");

		for (String valueToBeSelected : multipleSel) {

			new Select(element).selectByVisibleText(valueToBeSelected);
			// driver.findElement(By.id(multipleVals)).sendKeys(Keys.CONTROL);

		}

	}

	public void selectAllValues(WebElement element) {
		Select sel = new Select(element);
		List<WebElement> list = sel.getOptions();
		for (int i = 0; i < list.size(); i++) {
			sel.selectByIndex(i);
			element.sendKeys(Keys.CONTROL);
		}
	}

	public void selectDropDown(WebElement ele, String value) {
		WebElement mySelectElement = ele;
		Select dropdown = new Select(mySelectElement);
		List<WebElement> options = dropdown.getOptions();
		for (WebElement option : options) {
			dropdown.selectByVisibleText(value);
			// System.err.println(option.getSize()); //output "option1",
			// "option2", "option3"
		}

	}

	public boolean isElementPresent1(String locator) {

		int s = driver.findElements(By.xpath(locator)).size();
		if (s == 0) {

			return false;
		} else {

			return true;
		}

	}

	public void waitForElementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		log.info("waitForElementVisibility method is in progress");
		wait.until(ExpectedConditions.visibilityOf(element));
		assert element.isDisplayed();

	}

	/**
	 * <h1>takeScreenshot!</h1> This method will capture a screenshot appending
	 * timestamp to it
	 */

	// public void takeScreenShot() throws IOException {
	// Date d = new Date();
	// // String screenshotFile = d.toString().replace(":", "_").replace(" ",
	// "_") + ".png";
	// String filePath = getPropValue(screenshotFile).REPORTS_PATH +
	// "screenshots//" + screenshotFile;
	// // store screenshot in that file
	// // File scrFile = ((TakesScreenshot)
	// driver).getScreenshotAs(OutputType.FILE);
	//
	// }

	/*
	 * public void waitForPageToLoad() { wait(1); JavascriptExecutor
	 * js=(JavascriptExecutor)driver; String state = (String)js.executeScript(
	 * "return document.readyState");
	 * 
	 * while(!state.equals("complete")){ wait(2); state =
	 * (String)js.executeScript("return document.readyState"); } }
	 * 
	 * public void wait(int timeToWaitInSec){ try { Thread.sleep(timeToWaitInSec *
	 * 1000); } catch (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */

	public void waitForElementToBeSelected(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeSelected(element));
		log.info("waitForElementToBeSelected is in progress");
	}

	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		assert element.isEnabled();
		log.info("waitForElementToBeClickable method is in progress");
	}

	public void isElementPresent(WebElement element) {
		try {
			element.isDisplayed();
		} catch (NoSuchElementException e) // NoSuchElementException
		{
			System.out.println("FAILURE: Element is not dispalyed in the page");
			throw (e);
		}
	}

	public void waitTillPageLoad(WebElement element) {

		while (!element.isEnabled()) {
			driver.navigate().refresh();
		}

	}

	public void fluentWait(WebElement element, int Maxtime, int pollTime) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Maxtime, TimeUnit.SECONDS)
				.pollingEvery(pollTime, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.withMessage("User Defined: Wait timed out after" + Maxtime + "Seconds");

		wait.until(ExpectedConditions.presenceOfElementLocated((By) element)).click();

		// WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
		// public WebElement apply(WebDriver driver) {
		// return driver.findElement(locator);
		// }
		// });

	};

	public WebDriver launchBrowser() throws IOException {
		log.debug("launchBrowser method is in progress");
		// String browser = getPropValue("browser");
		String browser = Path.browser;
		if (browser.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("Chrome")) {
			File chromedriver = new File(System.getProperty("user.dir")+"/drivers/chromedriver");
			// Create object of ChromeOptions class
			ChromeOptions options = new ChromeOptions();
			// add parameter which will disable the extension
			options.addArguments("--disable-extensions");
			options.addArguments("test-type");
			options.addArguments("start-maximized");
			options.addArguments("--js-flags=--expose-gc");
			options.addArguments("--enable-precise-memory-info");
			options.addArguments("--enable-popup-blocking");
			options.addArguments("--disable-default-apps");
			options.addArguments("test-type=browser");
			options.addArguments("disable-infobars");

			// Start the Chrome session
			log.info("webdriver.chrome.driver" + chromedriver.getAbsolutePath());
			System.setProperty("webdriver.chrome.driver", "/Users/aravindanathdm/Documents/Aravinda/chromedriver");
			driver = new ChromeDriver(options);
		}

		return driver;

	}

	public void takeScreenShot() throws AWTException, IOException {
		scrollUp();
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		// String filePath = "D:\\reports\\FailedScreenShots\\" +
		// "screenshots\\" + screenshotFile;
		String filePath = getPropValue(screenshotFile) + "screenshots\\" + screenshotFile;

		// store screenshot in that file
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getMessage());

		}

	}

	public void screenShot() throws IOException {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		String path = getPropertyOf("ScreenShotPath");
		// String filePath = path + "screenshots\\" + screenshotFile;
		String filePath = path + screenshotFile;
		ImageIO.write(screenshot.getImage(), "PNG", new File(filePath));
	}

	public void reportFailure(String failureMessage) throws AWTException, IOException {
		takeScreenShot();
		Assert.fail(failureMessage);
	}

	public boolean isElementPresent(WebDriver driver, By by) {
		try {
			return driver.findElements(by).size() > 0;
		} catch (Exception e) // NoSuchElementException
		{
			return false;
		}
	}

	public void verifyTitle1(String expeted) {

	}

	public boolean isElementPresent(String locator) {

		return false;
	}

	public void waitSeconds1(int seconds) {
		new WebDriverWait(driver, seconds);

	}

	public void waitingForTheElementToLoad(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void isElementPresent1(WebElement element) {
		try {
			element.isDisplayed();
		} catch (NoSuchElementException e) // NoSuchElementException
		{
			System.out.println("Element is not dispalyed in the page");
			throw (e);
		}
	}

	public boolean isElementPresent1(WebDriver driver, By by) {
		try {
			return driver.findElements(by).size() > 0;
		} catch (Exception e) // NoSuchElementException
		{
			return false;
		}
	}

	public void click(WebElement ele) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);

	}

	public boolean clickElement(WebElement element) {

		long startTime = System.currentTimeMillis();
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].disabled = false", element);
			((JavascriptExecutor) driver).executeScript("arguments[0].click;", element);
		} catch (Exception e) {
			long currentTime = System.currentTimeMillis();
			long totalTimeTaken = currentTime - startTime;
			int timeInSec = (int) totalTimeTaken / 1000;
			if (timeInSec < 40) {
				clickElement(element);
			}
		}
		return false;
	}

	public void scrollup(String xValue) {

		String parameter = "scroll(" + xValue + ",0)";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(parameter); // x value '500' can be altered

	}

	public void scrollDown(String yValue) throws InterruptedException {

		String parameter = "scroll(0," + yValue + ")";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(parameter); // y value '500' can be altered

	}

	public void scrollDown() throws AWTException {
		log.info("scrollDown method is in progress");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		log.info("scrollDown method has been executed..");
	}

	public void pressEscape() throws AWTException {
		log.info("pressEscape method is in progress");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		log.info("pressEscape method has been executed..");
	}

	public void pressTab() throws AWTException {
		log.info("pressTab method is in progress");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		log.info("pressTab method has been executed..");
	}

	public void pressEnter() throws AWTException {
		log.info("pressTab method is in progress");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		log.info("pressTab method has been executed..");
	}

	public void scrollUp() throws AWTException {
		log.info("scrollUp method is in progress");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_UP);
		robot.keyRelease(KeyEvent.VK_PAGE_UP);
		log.info("scrollUp method has been executed..");
	}

	public void scrollToElementViaJavascript(WebElement element) {
		assert element.isDisplayed();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollToElementViaCordinate(WebElement element) {
		Coordinates coordinate = ((Locatable) element).getCoordinates();
		coordinate.onPage();
		coordinate.inViewPort();
	}

	public List<WebElement> getDropdownItems(WebElement element) {
		Select select = new Select(element);
		return select.getOptions();
	}

	public String getSelectedDropdownItem(WebElement element) {
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public void deselectDropDown(WebElement element) {
		int size = new Select(element).getOptions().size();
		for (int i = 0; i < size; i++) {
			new Select(element).deselectByIndex(i);
		}
	}

	public void selectGroup(String groupName) throws InterruptedException {

		loop: for (int i = 1; i < 15; i++) {
			try {
				if (driver.findElement(By.xpath("//table[@class='table width100']/tbody/tr[" + groupName + "]/td[3]"))
						.isDisplayed()) {
					Thread.sleep(300);
					driver.findElement(By.xpath("//table[@class='table width100']/tbody/tr[" + groupName + "]/td[3]"))
							.click();

					log.info("Found Group name " + groupName + " and clicked on it");
					System.out.println("Found Group name " + groupName + " and clicked on it");
					break loop;
				}
			} catch (WebDriverException e) {
				log.info("unable to find the " + groupName + " and click on it");
				System.err.println("unable to find the " + groupName + " and click on it");
			}
			Thread.sleep(2000);
		}

	}

	public void selectClientOptRSD(String clientName) throws InterruptedException {

		loop: for (int i = 1; i < 6; i++) {
			try {

				if (driver
						.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + clientName
								+ "')]/following-sibling::td/a[@ng-click='optForRSDSubsidairy(member)']"))
						.isDisplayed()) {
					Thread.sleep(300);
					driver.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + clientName
							+ "')]/following-sibling::td/a[@ng-click='optForRSDSubsidairy(member)']")).click();

					log.info("Found client name " + clientName + " and clicked on it");
					System.out.println("Found client name " + clientName + " and clicked on it");
					break loop;
				}
			} catch (WebDriverException e) {
				log.info("unable to find the client " + clientName + " and click on it");
				System.err.println("unable to find the client " + clientName + " and click on it");
			}
			Thread.sleep(2000);
		}

	}

	public void clickAction(WebElement element) {
		Actions act = new Actions(driver);

		act.click(element).build().perform();
	}

	public void mouseHoverAndClick(WebElement element) {
		log.info("Mouse hover and click method is in progress.");
		System.out.println(element.isEnabled());
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].focus();", element);
		System.out.println(element.isEnabled());
		element.click();
		log.info("Mouse hover and click method has executed.");
	}

	public LoginPage logout() throws InterruptedException {
		Thread.sleep(2000);
		log.info("User is attempting to logout. ");
		scrollup("1000");
//		waitForElementVisibility(HomePage.logoutDropdown);
//		click(HomePage.logoutDropdown);
//		Thread.sleep(2000);
//		click(HomePage.logoutButton);
		log.info(" ");
		System.out.println("Logged Out sucessfuly");
		log.info("user has Logged Out sucessfuly");
		return new LoginPage(driver );

	}

	public WebDriver getDriver() {
		return driver;
	}

	public void closeAll() {
		// TODO Auto-generated method stub
		try {
			driver.close();
		} catch (Exception e) {
			driver.quit();
		}
	}

	/**
	 * <h1>open_FirefoxBrowser!</h1> This method will invoke a Firefox browser with
	 * maximized window
	 * 
	 */
	public WebDriver open_FirefoxBrowser() {
		try {

			driver = new FirefoxDriver();

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		} catch (Exception e) {
			e.getMessage();
		}
		return driver;
	}

	/**
	 * <h1>open_FirefoxBrowser_MobileView!</h1> This method will invoke a Firefox
	 * browser by setting Iphone useragent
	 */
	public WebDriver open_FirefoxBrowser_MobileView() throws Exception {
		try {

			FirefoxProfile ffprofile = new FirefoxProfile();
			ffprofile.setPreference("general.useragent.override", "iPhone");
			// iPhone,
			// WebDriver driver = new FirefoxDriver(ffprofile);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().setSize(new Dimension(400, 800));

		} catch (Exception e) {

		}
		return driver;
	}

	/**
	 * <h1>open_FirefoxBrowser_TabletView!</h1> This method will invoke a Firefox
	 * browser by setting Ipad useragent
	 */
	public WebDriver open_FirefoxBrowser_TabletView() throws Exception {
		try {

			FirefoxProfile ffprofile = new FirefoxProfile();
			ffprofile.setPreference("general.useragent.override", "iPad");

			// WebDriver driver = new FirefoxDriver(ffprofile);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().setSize(new Dimension(768, 1024));

		} catch (Exception e) {
			e.getMessage();
		}
		return driver;
	}

	/**
	 * <h1>open_ChromeBrowser!</h1> This method will invoke a chrome browser
	 */
	public WebDriver open_ChromeBrowser() throws Exception {
		try {
			// System.setProperty("webdriver.chrome.driver",
			// GVConstants.CHROME_DRIVER_EXE);
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new ChromeDriver(capability);

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		} catch (Exception e) {
			e.getMessage();
		}
		return driver;
	}

	/**
	 * <h1>switchToNewWindow!</h1> This method will helps us to switch to a New
	 * window
	 */
	public void switchToNewWindow() {
		log.info("switchToNewWindow method is in progress");
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		itr.next();
		String w2 = (String) itr.next();
		driver.switchTo().window(w2);
		log.info("switchToNewWindow executed sucessfully");
	}

	public void turnOffImplicitWaits() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	/**
	 * <h1>switchToOldWindow!</h1> This method will helps us to switch to a Old
	 * window
	 */
	public void switchToOldWindow() {
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		String w1 = (String) itr.next();
		itr.next();
		driver.switchTo().window(w1);
	}

	/**
	 * <h1>switchToDefaultContent!</h1> This method will helps us to switch to a
	 * default content
	 */
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	/**
	 * <h1>switchToFrame_byName!</h1> This method will helps us to switch to a
	 * Frame. Here you need to pass name of the frame
	 */
	public void switchToFrame_byName(String frameName) {
		driver.switchTo().frame(frameName);
	}

	/**
	 * <h1>switchToFrame_byIndex!</h1> This method will helps us to switch to a
	 * Frame. Here you need to pass number of the frame
	 */
	public void switchToFrame_byIndex(int frameValue) {
		driver.switchTo().frame(frameValue);
	}

	public void switchToFrame_byWebElement(String frameName) throws Exception {
		WebElement webelement = driver.findElement(By.tagName(frameName));
		try {
			driver.switchTo().frame(webelement);
		} catch (Exception e) {
			throw e;
		}
	}

	public WebDriver openIEBrowser() throws Exception {
		try {
			// System.setProperty("webdriver.ie.driver",
			// GVConstants.IEDriverPath);
			driver = new InternetExplorerDriver();

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			// driver.get(GVConstants.PROD_HOMEPAGE_URL);
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");

		} catch (Exception e) {
			e.getMessage();
		}
		return driver;
	}

	public String getTestCaseName(String sTestCase) throws Exception {
		String value = sTestCase;
		try {
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");
			value = value.substring(posi + 1);
			return value;
		} catch (Exception e) {
			e.getMessage();
			throw (e);
		}
	}

	public void waitForElement(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitMyTime(int i) {
		driver.manage().timeouts().implicitlyWait(i, TimeUnit.SECONDS);

	}

	public void setWindowSize(int Dimension1, int dimension2) {
		driver.manage().window().setSize(new Dimension(Dimension1, dimension2));

	}

	public void pressKeyDown(WebElement element) {
		element.sendKeys(Keys.DOWN);
	}

	public void pressKeyEnter(WebElement element) {
		element.sendKeys(Keys.ENTER);
	}

	public void pressKeyUp(WebElement element) {
		element.sendKeys(Keys.UP);
	}

	public void moveToTab(WebElement element) {
		element.sendKeys(Keys.chord(Keys.ALT, Keys.TAB));
	}

	public void clickWebelement(WebElement element) {
		try {
			boolean elementIsClickable = element.isEnabled();
			while (elementIsClickable) {
				element.click();
			}

		} catch (Exception e) {
			System.out.println("Element is not enabled");
			e.printStackTrace();
		}
	}

	public void clickMultipleElements(WebElement someElement, WebElement someOtherElement) {
		Actions builder = new Actions(driver);
		builder.keyDown(Keys.CONTROL).click(someElement).click(someOtherElement).keyUp(Keys.CONTROL).build().perform();
	}

	public void dragAndDrop(WebElement fromWebElement, WebElement toWebElement) {
		Actions builder = new Actions(driver);
		builder.dragAndDrop(fromWebElement, toWebElement);
	}

	public void dragAndDrop_Method2(WebElement fromWebElement, WebElement toWebElement) {
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(fromWebElement).moveToElement(toWebElement).release(toWebElement)
				.build();
		dragAndDrop.perform();
	}

	public void dragAndDrop_Method3(WebElement fromWebElement, WebElement toWebElement) throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.clickAndHold(fromWebElement).moveToElement(toWebElement).perform();
		Thread.sleep(2000);
		builder.release(toWebElement).build().perform();
	}

	public void hoverWebelement(WebElement HovertoWebElement) throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.moveToElement(HovertoWebElement).build().perform();
		Thread.sleep(2000);

	}

	public void doubleClickWebelement(WebElement doubleclickonWebElement) throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.doubleClick(doubleclickonWebElement).perform();
		Thread.sleep(2000);

	}

	public void selectElementByNameMethod(WebElement element, String Name) {
		Select selectitem = new Select(element);
		selectitem.selectByVisibleText(Name);
	}

	public void verifyExpectedAndActualOptionsInDropdown(WebElement element, List<String> listOfOptions) {

		Select ele = new Select(element);
		// need to give list of options like below. You can add values from
		// excel or csv
		/*
		 * List<String> ds = new ArrayList<String>(); ds.add("Asia"); ds.add("Europe");
		 * ds.add("Africa");
		 */

		List<String> expectedOptions = listOfOptions;
		List<String> actualOptions = new ArrayList<String>();
		for (WebElement option : ele.getOptions()) {
			System.out.println("Dropdown options are: " + option.getText());
			actualOptions.add(option.getText());

		}
		System.out.println("Numbers of options present in the dropdown: " + actualOptions.size());
		Assert.assertSame(expectedOptions.toArray(), actualOptions.toArray());
		// Assert.assertEquals(expectedOptions.toArray(),
		// actualOptions.toArray());
		System.out.println("test");

	}

	public void deselectElementByNameMethod(WebElement element, String Name) {
		Select selectitem = new Select(element);
		selectitem.deselectByVisibleText(Name);
	}

	public void verifyDropdownHaveNoMultipleSelection(WebElement element, String Name) {
		Select ss = new Select(element);
		Assert.assertFalse(ss.isMultiple());
	}

	public void verifyDropdownHaveMultipleSelection(WebElement element, String Name) {
		Select ss = new Select(element);
		Assert.assertTrue(ss.isMultiple());
	}

	public void verifyOptionsInDropdownInAphabeticalOrder(WebElement element) {

		Select ele = new Select(element);
		List<String> expectedOptions = new ArrayList<String>();
		List<String> actualOptions = new ArrayList<String>();

		for (WebElement option : ele.getOptions()) {
			System.out.println("Dropdown options are: " + option.getText());
			actualOptions.add(option.getText());
			expectedOptions.add(option.getText());
		}

		Collections.sort(actualOptions);
		System.out.println("Numbers of options present in the dropdown: " + actualOptions.size());
		Assert.assertSame(expectedOptions.toArray(), actualOptions.toArray());

	}

	public void verifyOptionsSizeOfDropdown(WebElement element, int numberOfOptions) {
		Select ssd = new Select(element);
		Assert.assertEquals(numberOfOptions, ssd.getOptions().size());
	}

	public void selectElementByValueMethod(WebElement element, String value) {
		Select selectitem = new Select(element);
		selectitem.selectByValue(value);
	}

	public void deselectElementByValueMethod(WebElement element, String value) {
		Select selectitem = new Select(element);
		selectitem.deselectByValue(value);
	}

	public void selectElementByIndexMethod(WebElement element, int index) {
		Select selectitem = new Select(element);
		selectitem.selectByIndex(index);
	}

	public void deselectElementByIndexMethod(WebElement element, int index) {
		Select selectitem = new Select(element);
		selectitem.deselectByIndex(index);
	}

	public void clickCheckboxFromList(String xpathOfElement, String valueToSelect) {

		List<WebElement> lst = driver.findElements(By.xpath(xpathOfElement));
		for (int i = 0; i < lst.size(); i++) {
			List<WebElement> dr = lst.get(i).findElements(By.tagName("label"));
			for (WebElement f : dr) {
				System.out.println("value in the list : " + f.getText());
				if (valueToSelect.equals(f.getText())) {
					f.click();
					break;
				}
			}
		}
	}

	public void downloadFile(String href, String fileName) throws Exception {
		URL url = null;
		URLConnection con = null;
		int i;
		url = new URL(href);
		con = url.openConnection();
		File file = new File(".//OutputData//" + fileName);
		BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
		@SuppressWarnings("resource")
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		while ((i = bis.read()) != -1) {
			bos.write(i);
		}
		bos.flush();
		bis.close();
	}

	public void navigate_back() {
		driver.navigate().back();
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public String getToolTip(WebElement toolTipofWebElement) throws InterruptedException {
		String tooltip = toolTipofWebElement.getAttribute("title");
		System.out.println("Tool text : " + tooltip);
		return tooltip;
	}

	public void datepicker_setDateAndTime(String TypeMonth, String TypeYear, String Date, String setHour,
			String setMinute, String setSeconds, String setHourShift) {
		List<WebElement> date = driver.findElements(By.xpath("//*[@id='ui-datepicker-div']"));
		System.out.println("number of datepickers present : " + date.size());
		for (int i = 0; i < date.size(); i++) {
			System.out.println("element present in the Date picker box " + date.get(i).getText());
			Select month = new Select(date.get(i).findElement(
					By.xpath("//select[@class='form-control datetime-ui-datepicker-month input-width-20']")));
			month.selectByVisibleText(TypeMonth);
			Select year = new Select(date.get(i).findElement(
					By.xpath("//select[@class='form-control datetime-ui-datepicker-year input-width-20']")));
			year.selectByVisibleText(TypeYear);

			driver.findElement(By.linkText(Date)).click();

			date.get(i).findElement(By.xpath("//input[contains(@id,'hour')]")).clear();
			date.get(i).findElement(By.xpath("//input[contains(@id,'minute')]")).clear();
			date.get(i).findElement(By.xpath("//input[contains(@id,'second')]")).clear();

			date.get(i).findElement(By.xpath("//input[contains(@id,'hour')]")).sendKeys(setHour);
			date.get(i).findElement(By.xpath("//input[contains(@id,'minute')]")).sendKeys(setMinute);
			date.get(i).findElement(By.xpath("//input[contains(@id,'second')]")).sendKeys(setSeconds);
			Select hourshift = new Select(date.get(i).findElement(By.xpath("//select[@class='hourShift']")));
			hourshift.selectByVisibleText(setHourShift);
			driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[4]/button")).click();
		}
	}

	/*
	 * 
	 * 
	 */
	public String nextmonth() {
		try {
			Date d = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
			String da = sdf.format(d);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(da));
			cal.add(Calendar.MONTH, 1);
			String fc = sdf.format(cal.getTime());
			return fc;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;

	}

	public String nextYear() {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 365);
		String reportDate = df.format(cal.getTime());
		return reportDate;
	}

	public String dayOfWeek() {
		Calendar cal = Calendar.getInstance();
		String reportDate = "";
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			reportDate = "Sunday";
			break;
		case 2:
			reportDate = "Monday";
			break;
		case 3:
			reportDate = "Tuesday";
			break;
		case 4:
			reportDate = "Wednesday";
			break;
		case 5:
			reportDate = "Thursday";
			break;
		case 6:
			reportDate = "Friday";
			break;
		case 7:
			reportDate = "Saturday";
			break;
		}
		return reportDate;
	}

	public String dateInFormat() {
		DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	public String dateInFormat1() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	public String dateInFormat3() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	public String dateInFormat2() {
		DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	public String dateInFormat4() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	public String yesterday() {

		DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String reportDate = df.format(cal.getTime());
		return reportDate;
	}

	public String lastWeek() {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		String reportDate = df.format(cal.getTime());
		return reportDate;
	}

	public String nextWeekSameDay() {

		DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7);
		String reportDate = df.format(cal.getTime());
		return reportDate;
	}

	public static String nextWeekSameday() {

		DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7);
		String reportDate = df.format(cal.getTime());
		return reportDate;
	}

	// public static DayOfWeek GetDayFromDate() {
	// LocalDate localDate = LocalDate.now().plusDays(7);
	// java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
	// // System.out.println(localDate + " is / was a " + dayOfWeek);
	// switch (dayOfWeek) {
	// case MONDAY:
	// System.err.println("MONDAY");
	// System.err.println("Selected MONDAY");
	// WebElement dayRadiobutton1 =
	// driver.findElement(By.xpath("//td[contains(.,'MON')]//input"));
	// dayRadiobutton1.click();
	// break;
	// case TUESDAY:
	// System.err.println("TUESDAY");
	// System.err.println("Selected TUESDAY");
	// WebElement dayRadiobutton2 =
	// driver.findElement(By.xpath("//td[contains(.,'TUE')]//input"));
	// dayRadiobutton2.click();
	// break;
	// case WEDNESDAY:
	// System.err.println("WEDNESDAY");
	// System.err.println("Selected WEDNESDAY");
	// WebElement dayRadiobutton3 =
	// driver.findElement(By.xpath("//td[contains(.,'WED')]//input"));
	// dayRadiobutton3.click();
	// break;
	// case THURSDAY:
	// System.err.println("THURSDAY");
	// System.err.println("Selected THURSDAY");
	// WebElement dayRadiobutton4 =
	// driver.findElement(By.xpath("//td[contains(.,'THU')]//input"));
	// dayRadiobutton4.click();
	// break;
	// case FRIDAY:
	// System.err.println("FRIDAY");
	// System.err.println("Selected FRIDAY");
	// WebElement dayRadiobutton5 =
	// driver.findElement(By.xpath("//td[contains(.,'FRI')]//input"));
	// dayRadiobutton5.click();
	// break;
	// case SATURDAY:
	// System.err.println("SATURDAY");
	// System.err.println("Selected SATURDAY");
	// WebElement dayRadiobutton6 =
	// driver.findElement(By.xpath("//td[contains(.,'SAT')]//input"));
	// dayRadiobutton6.click();
	// break;
	//
	// }
	//
	// return dayOfWeek;
	// }

	public String nextSecondWeekSameDay() {
		DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 14);
		String reportDate = df.format(cal.getTime());
		return reportDate;
	}

	public String nextMonthSameDay() {

		DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		String reportDate = df.format(cal.getTime());
		return reportDate;
	}

	public String tomorrow() {
		DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		String reportDate = df.format(cal.getTime());
		return reportDate;
	}

	public String todaysDate() {
		DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		Calendar cal = Calendar.getInstance();
		String reportDate = df.format(cal.getTime());
		return reportDate;
	}

	public String datePastYears(int years) {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -years);
		String reportDate = df.format(cal.getTime());
		return reportDate;
	}

	public void clickAllLinksInPage(String NameOfScreenshot) throws Exception {

		List<WebElement> Links = driver.findElements(By.tagName("a"));
		System.out.println("Total number of links  :" + Links.size());

		for (int p = 0; p < Links.size(); p++) {
			System.out.println("Elements present the body :" + Links.get(p).getText());
			Links.get(p).click();
			Thread.sleep(3000);
			System.out.println("Url of the page  " + p + ")" + driver.getCurrentUrl());
			// takeScreenshot(NameOfScreenshot + "_" + p);
			navigate_back();
			Thread.sleep(2000);
		}
	}

	public void navigate_forward() {
		driver.navigate().forward();
	}

	public void clearTextField(WebElement element) {
		element.clear();

	}

	public void highlightelement(WebElement element) {
		for (int i = 0; i < 3; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: solid red; border: 5px solid blue;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");

		}

	}

	public void closeExtraTabs() throws InterruptedException {

		List<String> windows = new ArrayList<String>(driver.getWindowHandles());
		for (int i = 1; i < windows.size(); i++) {
			driver.switchTo().window(windows.get(i));
			driver.close();
		}
		// driver.switchTo().window(windows.get(1));
		// driver.close();
		driver.switchTo().window(windows.get(0));
	}

	public void switchToMainWindow() throws InterruptedException {

		List<String> windows = new ArrayList<String>(driver.getWindowHandles());
		for (int i = 1; i < windows.size(); i++) {
			driver.switchTo().window(windows.get(i));
			driver.close();
		}
		// driver.switchTo().window(windows.get(1));
		// driver.close();
		driver.switchTo().window(windows.get(0));
	}

	public boolean checkAlert() {
		try {
			Alert a = driver.switchTo().alert();
			String str = a.getText();
			System.out.println(str);

			return true;

		} catch (Exception e) {

			System.out.println("no alert ");
			return false;

		}
	}

	public boolean acceptAlert() {
		try {
			Alert a = driver.switchTo().alert();
			String str = a.getText();
			System.out.println(str);

			a.accept();
			return true;

		} catch (Exception e) {

			System.out.println("no alert ");
			return false;

		}
	}

	public boolean dismissAlert() {
		try {
			Alert a = driver.switchTo().alert();
			String str = a.getText();
			System.out.println(str);

			a.dismiss();
			return true;

		} catch (Exception e) {

			System.out.println("no alert ");
			return false;

		}
	}

	public void scrolltoElement(WebElement ScrolltoThisElement) {
		Coordinates coordinate = ((Locatable) ScrolltoThisElement).getCoordinates();
		coordinate.onPage();
		coordinate.inViewPort();
	}

	public void checkbox_Checking(WebElement checkbox) {
		boolean checkstatus;
		checkstatus = checkbox.isSelected();
		if (checkstatus == true) {
			System.out.println("Checkbox is already checked");
		} else {
			checkbox.click();
			System.out.println("Checked the checkbox");
		}
	}

	public void radiobutton_Select(WebElement Radio) {
		boolean checkstatus;
		checkstatus = Radio.isSelected();
		if (checkstatus == true) {
			System.out.println("RadioButton is already checked");
		} else {
			Radio.click();
			System.out.println("Selected the Radiobutton");
		}
	}

	public void setApplicationParameter(String sectionName, String parameterName, String parameterValue)
			throws IOException {
		log.info("writing the Loan details to a .ini file");
		Ini ini = new Ini(new File("./test-data/test-data.ini"));
		ini.put(sectionName, parameterName, parameterValue);
		ini.store();
	}

	public String getApplicationParameter(String sectionName, String parameterName)
			throws InvalidFileFormatException, IOException {
		log.info("reading the Loan details from a .ini file");
		Ini ini = new Ini(new File("./test-data/test-data.ini"));
		String parameterValue = ini.get(sectionName, parameterName);
		System.err.println(parameterName + "-->" + parameterValue);
		return parameterValue;
	}

	public void setCenterName(String parameterName) throws IOException {
		log.info("writing the center name to a .ini file");
		Ini ini = new Ini(new File("./test-data/test-data.ini"));
		ini.put("CenterName", "Center", parameterName);
		ini.store();
	}

	public String getCenterName() throws InvalidFileFormatException, IOException {
		log.info("reading the center name details from a .ini file");
		Ini ini = new Ini(new File("./test-data/test-data.ini"));
		String centerName = ini.get("CenterName", "Center");
		System.err.println("CenterName-->" + centerName);
		return centerName;
	}

	public void setClientFirstName(String parameterName) throws IOException {
		log.info("writing the Client First name to a .ini file");
		Ini ini = new Ini(new File("./test-data/test-data.ini"));
		ini.put("ClientFirstName", "ClientFN", parameterName);
		ini.store();
	}

	public String getClientFirstName() throws InvalidFileFormatException, IOException {
		log.info("reading the  Client First name details from a .ini file");
		Ini ini = new Ini(new File("./test-data/test-data.ini"));
		String clientFN = ini.get("ClientFirstName", "ClientFN");
		System.err.println("ClientFirstName-->" + clientFN);
		return clientFN;
	}

	public void setClientMiddleName(String parameterName) throws IOException {
		log.info("writing the Client Middle name to a .ini file");
		Ini ini = new Ini(new File("./test-data/test-data.ini"));
		ini.put("ClientMiddleName", "ClientMN", parameterName);
		ini.store();
	}

	public String getClientMiddleName() throws InvalidFileFormatException, IOException {
		log.info("reading the  Client Middle name details from a .ini file");
		Ini ini = new Ini(new File("./test-data/test-data.ini"));
		String clientMN = ini.get("ClientMiddleName", "ClientMN");
		System.err.println("ClientMiddleName-->" + clientMN);
		return clientMN;
	}

	public void setClientLastName(String parameterName) throws IOException {
		log.info("writing the Client Middle name to a .ini file");
		Ini ini = new Ini(new File("./test-data/test-data.ini"));
		ini.put("ClientLastName", "ClientLN", parameterName);
		ini.store();
	}

	public String getClientLastName() throws InvalidFileFormatException, IOException {
		log.info("reading the  Client Middle name details from a .ini file");
		Ini ini = new Ini(new File("./test-data/test-data.ini"));
		String clientLN = ini.get("ClientLastName", "ClientLN");
		System.err.println("ClientLastName-->" + clientLN);
		return clientLN;
	}

	public void setClientFullName(String parameterName) throws IOException {
		log.info("writing the Client Full name to a .ini file");
		Ini ini = new Ini(new File("./test-data/test-data.ini"));
		ini.put("ClientFullName", "ClientFullname", parameterName);
		ini.store();
	}

	public String getClientFullName() throws InvalidFileFormatException, IOException {
		log.info("reading the  Client full name details from a .ini file");
		Ini ini = new Ini(new File("./test-data/test-data.ini"));
		String clientFullname = ini.get("ClientFullName", "ClientFullname");
		System.err.println("ClientFirstName-->" + clientFullname);
		return clientFullname;
	}

	public void setLeadId(String applicantLeadID) throws IOException {
		log.info("writing the Loan details to a .ini file");
		Ini ini = new Ini(new File("./test-data/test-data.ini"));
		ini.put("LoanApplication", "LEADID", applicantLeadID);
		ini.store();
	}

	public String getLeadId() throws InvalidFileFormatException, IOException {
		log.info("reading the Loan details from a .ini file");
		Ini ini = new Ini(new File("./test-data/test-data.ini"));
		String leadId = ini.get("LoanApplication", "LEADID");
		System.err.println("LeadID-->" + leadId);
		return leadId;
	}

	// Unchecking
	public void checkbox_Unchecking(WebElement checkbox) {
		boolean checkstatus;
		checkstatus = checkbox.isSelected();
		if (checkstatus == true) {
			checkbox.click();
			System.out.println("Checkbox is unchecked");
		} else {
			System.out.println("Checkbox is already unchecked");
		}
	}

	public void radioButton_Deselect(WebElement Radio) {
		boolean checkstatus;
		checkstatus = Radio.isSelected();
		if (checkstatus == true) {
			Radio.click();
			System.out.println("Radio Button is deselected");
		} else {
			System.out.println("Radio Button is already Deselected");
		}
	}

	public void failThisStep(String reasonForFailing) {
		Assert.fail(reasonForFailing);
	}

	public boolean isElementPresent_locator(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isElementPresent_webelement(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void verifyCSSvalue(WebElement element, String cssValueOf, String expectedCssValue) {
		try {
			element.isDisplayed();
			System.out.println("CSS Value of " + cssValueOf + " is :" + element.getCssValue(cssValueOf));
			Assert.assertEquals(expectedCssValue, element.getCssValue(cssValueOf));
		} catch (NoSuchElementException e) {
			throw e;
		}

	}

	/**
	 * Verifying the flags in cookie such as httponly, issecure
	 * 
	 * @param Expectedcookie
	 * @throws Exception
	 */
	public void verifyFlagsOfCookie(String Expectedcookie) throws Exception {
		try {
			Cookie saveMyCookie = driver.manage().getCookieNamed(Expectedcookie);
			if (saveMyCookie.isHttpOnly() && saveMyCookie.isSecure()) {
				Assert.assertTrue(true);
			} else {
				System.out.println(saveMyCookie.getName() + " doesnt have flags IsHttpOnly & IsSecure");
				Assert.fail("doesnt have flags IsHttpOnly & IsSecure");
			}

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Adding a cookie with name, value, path
	 * 
	 * @param cookieName
	 * @param cookieValue
	 * @param Domain
	 * @param path
	 * @throws Exception
	 */
	public void addCookie(String cookieName, String cookieValue, String Domain, String path) throws Exception {
		try {
			Cookie name = new Cookie(cookieName, cookieValue, Domain, path, new Date());
			driver.manage().addCookie(name);
			refresh();
			System.out.println("Added Cookie " + cookieName);

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Delete all cookies present in the application
	 */
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();

	}

	/**
	 * getAllCookies, this will get you all the cookies present in the
	 * page/application
	 */
	public Set<Cookie> getAllCookies() {
		Set<Cookie> cookiesList = driver.manage().getCookies();
		for (Cookie getcookies : cookiesList) {
			System.out.println(getcookies);
		}
		return cookiesList;
	}

	public void verifyPageSourceContains(String stringInPagesource) {
		String pageSource = driver.getPageSource();
		Assert.assertTrue(pageSource.contains(stringInPagesource));
	}

	/**
	 * <h1>getPageLoadTime
	 * <h1>This method can be used to get page load time. This returns Long.
	 */
	public long getPageLoadTime() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
		long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
		long loadtime = (loadEventEnd - navigationStart) / 1000;
		System.out.println("Page Load Time is " + loadtime + " seconds.");
		return loadtime;
	}

	/**
	 * In order to zoom out you need to pass an integer, to zoomout n number of
	 * times
	 */
	public void zoomOut(int toExtent) {
		for (int i = 0; i < toExtent; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		}
	}

	/**
	 * In order to zoom In you need to pass an integer, to zoom In n number of times
	 */
	public void zoomIn(int toExtent) {
		for (int i = 0; i < toExtent; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
		}
	}

	public void zoomToDefault() {
		driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));

	}

	/**
	 * <h1>verifyVideoUsingVideoElement
	 * <h1>This method can be used to verify a video present in the page with tag
	 * video.
	 * 
	 * @param element
	 * @param srcOfVideo
	 * @param durationOfVideo
	 */
	public void verifyVideoUsingVideoElement(WebElement element, String srcOfVideo, long durationOfVideo) {
		// Get the HTML5 Video Element
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String source = (String) jsExecutor.executeScript("return arguments[0].currentSrc;", element);
		long duration = (Long) jsExecutor.executeScript("return arguments[0].duration", element);

		Assert.assertEquals(source, srcOfVideo);
		Assert.assertEquals(duration, durationOfVideo);

	}

	// public void verifyTextPresent(String value) {
	// driver.getPageSource().contains(value);
	//
	// Verify.verify(true, value, value + " ---- is Present");
	//
	// }

	public int getRandomNumberBetween(int min, int max) {

		Random foo = new Random();
		int randomNumber = foo.nextInt(max - min) + min;
		if (randomNumber == min) {
			return min + 1;
		} else {
			return randomNumber;
		}

	}

	/**
	 * 
	 * RandomDataMethods.generateRandomDate("dd MMM yyyy", "18 Jul 2016", "20 Sep
	 * 2017")
	 * 
	 * @param Format
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws java.text.ParseException
	 */
	public String generateRandomDate(String Format, String startDate, String endDate) throws java.text.ParseException {
		DateFormat formatter = new SimpleDateFormat(Format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(formatter.parse(startDate));
		Long value1 = cal.getTimeInMillis();

		cal.setTime(formatter.parse(endDate));
		Long value2 = cal.getTimeInMillis();

		long value3 = (long) (value1 + Math.random() * (value2 - value1));
		cal.setTimeInMillis(value3);
		return formatter.format(cal.getTime());
	}

	/**
	 * Set proxy Address(IP) and port number as a String (ex:localhost:8080)
	 * 
	 * @param ProxyHostWithPort
	 */
	public void openFirefoxWithProxySettings(String ProxyHostWithPort) {

		Proxy proxy = new Proxy();
		proxy.setHttpProxy(ProxyHostWithPort).setFtpProxy(ProxyHostWithPort).setSslProxy(ProxyHostWithPort)
				.setSocksProxy(ProxyHostWithPort);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, proxy);
		driver = new FirefoxDriver(cap);
		driver.manage().window().maximize();

	}

	public int[] getX_Y_cordinates(WebElement element) {
		int[] xy = null;
		Point p = element.getLocation();
		System.out.println("X Position: " + p.getX());
		System.out.println("Y Position: " + p.getY());
		int x = p.getX();
		int y = p.getY();
		xy = new int[x * y];
		return xy;

	}

	public int[] getWidth_HeightOfElement(WebElement element) {
		int[] xy = null;
		Dimension dimensions = element.getSize();
		System.out.println("Width : " + dimensions.width);
		System.out.println("Height : " + dimensions.height);
		int x = dimensions.getWidth();
		int y = dimensions.getHeight();
		xy = new int[x * y];
		return xy;

	}

	public void multipleFieldFill(List<WebElement> listOfElements, int number, String value) {
		listOfElements.get(number).sendKeys(value);
	}

	public void multipleFieldFillDateBox(List<WebElement> listOfElements, int number,
			String[] eXPECTEDDISBURSEMENTDATE) {
		listOfElements.get(number).sendKeys(eXPECTEDDISBURSEMENTDATE);
	}

	public void multipleFieldFillDate(List<WebElement> listOfElements, int number, String[] eXPECTEDDISBURSEMENTDATE) {
		listOfElements.get(number).sendKeys(eXPECTEDDISBURSEMENTDATE);
	}

	public void multipleFieldClear(List<WebElement> listOfElements, int number) {
		listOfElements.get(number).clear();
	}

	public void multipleElementClick(List<WebElement> listOfElements, int number) {
		// listOfElements.get(number).click();
		click(listOfElements.get(number));
	}

	public void multipleDropdownSelect(List<WebElement> listOfElements, int number, String value) {
		WebElement element = listOfElements.get(number);
		Select selectitem = new Select(element);
		selectitem.selectByVisibleText(value);
	}

	/**
	 * This method is used for passing CSV values
	 * 
	 * @param excelCellData
	 * @return
	 */

	public String[] splitStringValue(String excelCellData) {
		String[] individualValues = excelCellData.split(",");
		return individualValues;
	}

	public String[] splitStringValueifan(String excelCellData) {
		String[] individualValues = excelCellData.split("-");
		return individualValues;
	}

	public String[] splitStringValueTwice(String excelCellData, int number) {
		String[] set = excelCellData.split("-");
		int numberOfDataSet = set.length;
		System.out.println(numberOfDataSet);
		String[] individualValues = set[number].split(",");
		return individualValues;
	}

	public void navigateTo() {
		// TODO Auto-generated method stub

	}

	public void jsClick() {
		((JavascriptExecutor) driver).executeScript("document.getElementById('gs_htif0').disabled = false");
	}

	public static Boolean isElementEnabled(WebElement element) {
		String className = element.getAttribute("class");
		return className.matches(".+(noduplicate-.+-disabled)+");
	}

	// public static void main(String[] args) throws EncryptedDocumentException,
	// InvalidFormatException, IOException {
	// BasePage bp = new BasePage(driver);
	// bp.GetDayFromDate();
	// // System.err.print(bp.GetDayFromDate());
	//
	// }

	/**
	 * This method will set any parameter string to the system's clipboard.
	 */
	public void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste
		// operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public void uploadFile(String fileLocation) throws AWTException {

		StringSelection ss = new StringSelection(fileLocation);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		Robot r = new Robot();

		r.keyPress(KeyEvent.VK_ENTER);

		r.keyRelease(KeyEvent.VK_ENTER);

		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);

		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

	}

	public void fileUpload(String file) {
		StringSelection selection = new StringSelection(file);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	public void waitingForTheElementToDisappear(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, 80);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locator)));
	}

	@FindBy(xpath = "//*[@class='loading-widget-div']")
	public WebElement Block;

	public void updateExternalIdAndMDMCustomerUpdate(String householdID)
			throws ClassNotFoundException, InterruptedException {
		log.info("going to update External Id and UCIC in database");
		Class.forName("com.mysql.jdbc.Driver");
		int min = 111111111;
		int max = 999999998;
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		String externalIdUpdate = "update m_client set external_id='" + randomNum + "' where house_hold_id="
				+ householdID + ";";
		String mdmCustomerUpdate = "update m_client set is_mdm_customer_created = 1 where house_hold_id=" + householdID
				+ ";";
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://192.168.150.13/novobank_319_v1", "avik", "avik@123");
			Statement stmt = con.createStatement();
			stmt.executeUpdate(externalIdUpdate);
			Thread.sleep(5000);
			stmt.executeUpdate(mdmCustomerUpdate);
			System.out.println("Successfully updated queries in Client table");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public String randomAlphaNumericString() {
		final String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz12345674890";
		final java.util.Random rand = new java.util.Random();
		// consider using a Map<String,Boolean> to say whether the identifier is
		// being used or not
		final Set<String> identifiers = new HashSet<String>();

		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			int length = rand.nextInt(5) + 5;
			for (int i = 0; i < length; i++) {
				builder.append(randomString.charAt(rand.nextInt(randomString.length())));
			}
			if (identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		return builder.toString();
	}

	public String randomPAN() {
		final String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final java.util.Random rand = new java.util.Random();
		// consider using a Map<String,Boolean> to say whether the identifier is
		// being used or not
		final Set<String> identifiers = new HashSet<String>();
		int min = 1001;
		int max = 9998;
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			for (int i = 0; i < 5; i++) {
				builder.append(randomString.charAt(rand.nextInt(randomString.length())));
			}
			builder.append(randomNum);
			for (int i = 0; i < 1; i++) {
				builder.append(randomString.charAt(rand.nextInt(randomString.length())));
			}
			if (identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		return builder.toString();
	}

	public String randomString() {
		final String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		final java.util.Random rand = new java.util.Random();
		// consider using a Map<String,Boolean> to say whether the identifier is
		// being used or not
		final Set<String> identifiers = new HashSet<String>();

		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			int length = rand.nextInt(5) + 5;
			for (int i = 0; i < length; i++) {
				builder.append(randomString.charAt(rand.nextInt(randomString.length())));
			}
			if (identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		return builder.toString();
	}

	public String centerName() {
		final String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		final java.util.Random rand = new java.util.Random();
		// consider using a Map<String,Boolean> to say whether the identifier is
		// being used or not
		final Set<String> identifiers = new HashSet<String>();

		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			int length = rand.nextInt(5) + 5;
			for (int i = 0; i < length; i++) {
				builder.append(randomString.charAt(rand.nextInt(randomString.length())));
			}
			if (identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		return builder.toString();
	}

	public String randomMobileNumber() {
		long min = 9000000000L;
		long max = 9999999997L;
		long number = ThreadLocalRandom.current().nextLong(min, max + 1);
		String mobileNumber = "" + number + "";
		return mobileNumber;
	}

	public String randomContactNumber() {
		long min = 1000000001L;
		long max = 9999999997L;
		long number = ThreadLocalRandom.current().nextLong(min, max + 1);
		String contactNumber = "" + number + "";
		return contactNumber;
	}

	public String randomNumber() {
		int min = 48040;
		int max = 49999;
		int number = ThreadLocalRandom.current().nextInt(min, max + 1);
		String randomNumber = "" + number + "";
		return randomNumber;
	}

	public String randomInsurancePolicyNumber() {
		long min = 1000000001L;
		long max = 9999999997L;
		long number = ThreadLocalRandom.current().nextLong(min, max + 1);
		String contactNumber = "" + number + "";
		return contactNumber;
	}

	public String randomEmailAddress() {
		String emailAddress = "test." + randomAlphaNumericString() + "@gmail.com";
		return emailAddress;
	}

	public static String Handeler() {
		try {
			InetAddress ownIP = null;
			ownIP = InetAddress.getLocalHost();
			subject = ownIP.getHostAddress();

		} catch (Throwable t) {
			t.printStackTrace();
		}
		return subject;

	}

	public String randomAadharNumber() {
		long min = 100000000000L;
		long max = 999999999997L;
		long number = ThreadLocalRandom.current().nextLong(min, max + 1);
		String aadharNumber = "" + number + "";
		return aadharNumber;
	}

	public static LocalDate randomDate() {
		Random random = new Random();
		int minDay = (int) LocalDate.of(1960, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(1990, 1, 1).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);

		LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);

		return randomBirthDate;
		// System.out.println(randomBirthDate);

	}

	// public static void main(String[] args) {
	//
	// System.err.println(randomDate());
	//
	// }

}
