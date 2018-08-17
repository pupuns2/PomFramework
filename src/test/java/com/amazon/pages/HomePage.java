package com.amazon.pages;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;

import org.ini4j.InvalidFileFormatException;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);

		PageFactory.initElements(driver, this);
		this.driver = driver;

		// waitingForTheElementToLoad(welcomeText);
		// Assert.assertTrue(welcomeText.isDisplayed(), "Not in Home Page");

	}

	@FindBy(xpath = "//*[@id='wrap']/div/div/div/div/h1")
	public WebElement welcomeText;

	@FindBy(xpath = "//a[contains(text(),'Admin')]")
	public WebElement adminTabButton;

	// @FindBy(id="organization")
	// public WebElement adminOrganizationButton;
	@FindBy(linkText = "Admin")
	public WebElement adminOrganizationButton;

 
	
	
	
	@FindBy(xpath = "//form[@ng-submit='search()']/ul/li/a")
	public WebElement searchDropdown;
	
	@FindBy(xpath = "//form[@ng-submit='search()']/ul/li/ul/li/a[contains(text(),'Groups')]")
	public WebElement group;
	
	
	@FindBy(xpath = "//a[text()='System']")
	public WebElement adminSystemButton;

	@FindBy(linkText = "Products")
	public WebElement adminProductsButton;

	@FindBy(linkText = "Users")
	public WebElement adminUsersButton;

	@FindBy(xpath = "//*[@id='wrap']/div/div/div/section/div/div[1]/div[1]/a")
	public WebElement manageOffice;

	@FindBy(linkText = "Create Office")
	public WebElement createOffice;

	@FindBy(xpath = "//*[@id='wrap']/div/div/div/section/div/div/div/div/div[2]/table/tbody/tr/td[1]")
	public WebElement centerName;

	@FindBy(xpath = "//*[@id='wrap']/div/div/div/section/div/div/div/div/div[2]/div/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/a")
	public WebElement addButton;

	@FindBy(linkText = "Templates")
	public WebElement adminTemplatesButton;

	@FindBy(linkText = "Accounting")
	public WebElement accountingTabButton;

	@FindBy(linkText = "Reports")
	public WebElement reportsTabButton;

	@FindBy(linkText = "All")
	public WebElement reportsAllButton;

	@FindBy(linkText = "Clients")
	public WebElement reportsClientsButton;

	@FindBy(linkText = "Loans")
	public WebElement reportsLoansButton;

	@FindBy(linkText = "Accounting")
	public WebElement reportsAccountingButton;

	@FindBy(xpath = "//*[@id='bs-example-navbar-collapse-1']/ul[1]/li/a/span")
	public static WebElement logoutDropdown;

	@FindBy(id = "logout")
	public static WebElement logoutButton;

	@FindBy(id = "leftmenu-trigger")
	public WebElement toggleSideBar;

	@FindBy(linkText = "Dashboard")
	public WebElement dashboardButton;

	@FindBy(linkText = "Advance search")
	public WebElement advancedSearchButton;

	@FindBy(linkText = "Navigation")
	public WebElement navigationButton;

	@FindBy(linkText = "Checker Inbox & Tasks")
	public WebElement checkerInboxAndTasks;

	@FindBy(linkText = "Collection sheet")
	public WebElement collectionSheetButton;

	@FindBy(linkText = "Frequent Postings")
	public WebElement frequentPostingButton;

	@FindBy(linkText = "Add Journal Entries")
	public WebElement addjournalEntriesButton;

	@FindBy(linkText = "Closing Entries")
	public WebElement closingEntriesButton;

	@FindBy(linkText = "Chart of Accounts")
	public WebElement chartOfAccountsButton;

	@FindBy(linkText = "Client")
	public WebElement clientsButton;

	@FindBy(linkText = "Group")
	public WebElement groupButton;

	@FindBy(linkText = "Center")
	public WebElement centerButton;

	@FindBy(xpath = "//ul[@id='sidebar']/li[16]/a")
	public WebElement helpButton;

	// ********** Subsidiary JLG*****************

	@FindBy(xpath = "//*[@id='sidebar']/li[6]/a/span")
	public WebElement subsidiaryJLG;
	
	@FindBy(xpath = "//*[@id='sidebar']/li[5]/a/span")
	public WebElement subsidiaryJLG10;

	@FindBy(id = "appStatus")
	public WebElement StageDropDown;

	@FindBy(id = "centerName")
	public WebElement centerNameDropDown;

	@FindBy(id = "cbStatus")
	public WebElement cbStatusDropdown;

	@FindBy(id = "meetingDay")
	public WebElement centerMeetingDayDropdown;

	@FindBy(xpath = "//*[@id='wrap']/div/div/div/div/section/div/div/div/div/div[2]/form/div[5]/div/div/button")
	public WebElement searchBtn;

	@FindBy(xpath = "//form[@ng-submit='search()']")
	public static WebElement search_bt;

	@FindBy(id = "search")
	public static WebElement search_bar;

	// @FindBy(xpath ="//*[@id='search-icon']")
	// public WebElement searchBar;

	@FindBy(xpath = "//table[@id='melunsecuredapplications']/tbody/tr[1]/td[7]")
	public WebElement ddeBtn;

	@FindBy(xpath = "//*[@id='wrap']/div/div/div/div/h1")
	public WebElement welcome;

//	@FindBy(linkText = "Groups")
//	public WebElement groupTab;
	
	
	
	@FindBy(xpath = "//*[@id='wrap']/div/div/div/div/div[2]/div[2]/form/div/div/input")
	public WebElement centerSearchBar;
	
	@FindBy(xpath = "//*[@id='wrap']/div/div/div/section/div/div/div/div/div[2]/div/ul/li[2]/a")
	public WebElement groupTab;
	

	@FindBy(linkText = "Members")
	public WebElement memberTab;

	public HashMap<String, String> testExecutiondata;
	public static Boolean flag = false;

	public HomePage assertHomePage() {
		// Assert to check if we're on the home page
		Assert.assertEquals("Welcome!", "Welcome!");
		Reporter.log("Ooops! User is not on homepage..", false);
		return this;
	}

	// public HomePage assertHomePage() {
	//
	// // Assert to check if we're on the home page
	// Assert.assertEquals("Welcome!", "Welcome!");
	// return this;
	// }

	public CenterCreationPage navigateToCentersPage() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		waitForElementVisibility(centerButton);
		click(centerButton);

		return new CenterCreationPage(driver);
	}

	public void selectDDE(String clientName) throws InterruptedException {

		loop: for (int i = 1; i < 15; i++) {
			try {
				if (driver.findElement(By.xpath("//*[contains(text(),'" + clientName + "')]")).isDisplayed()) {
					driver.findElement(By.xpath("//*[contains(text(),'" + clientName + "')]")).click();
					System.out.println("Found " + clientName + " and clicked on it");
					break loop;
				}
			} catch (WebDriverException e) {
				System.out.println("unable to find the " + clientName + " and click on it");

			}
			Thread.sleep(1000);

		}

	}

	public SubsidiaryJLGPage clickSubsidiaryJLG() throws InterruptedException, IOException {
		waitForElementVisibility(subsidiaryJLG);
		Thread.sleep(500);
		if(getPropValue("Database").equals("GV.10")||getPropValue("Database").equals("GV.14")){
			try {
				click(subsidiaryJLG10);
			} catch (Exception e) {
				 mouseHoverAndClick(subsidiaryJLG10);
			}
		}else{
			try {
				click(subsidiaryJLG);
			} catch (Exception e) {
				 mouseHoverAndClick(subsidiaryJLG);
			}
		}
	
		
		return new SubsidiaryJLGPage(driver);
	}

	public Object searchCenter(String name, String groupName, String clientId) throws InterruptedException {

		// HashMap<String, String> existingCenter =
		// readExcelData("EXISTINGCENTER", existingCenterData);

		log.debug(" User is searching for center");
		HashMap<String, String> value = readExcelData("CENTERCREATION", name);
		Thread.sleep(2000);
		Reporter.log("User is attempting to click on search bar", true);
		search_bt.click();
		Reporter.log("User has clicked on search bar", true);
		search_bar.clear();
		Reporter.log("User has cleared on search bar", true);
		Thread.sleep(1500);
		Reporter.log("User is attempting to send the value in search bar", true);
		System.out.println("Value********" + value.get("NAME"));
		search_bar.sendKeys(value.get("NAME"));
		search_bar.sendKeys(Keys.ENTER);
		Reporter.log("User has send the value in search bar", true);
		Thread.sleep(1000);
		try {
			WebElement centerFound = driver.findElement(By.partialLinkText(value.get("NAME")));
			if (centerFound.isDisplayed()) {
				Reporter.log("Center is found!", true);
				centerFound.click();
				clickGroupTab();
				selectGroup(groupName);
				click(memberTab);
				Thread.sleep(200);
				selectClientAdd(clientId);
				Reporter.log("Client has been selected", true);
				// goToGroupAndcreateClient(groupName, clientId);
				log.info("User found the center" + value.get("NAME"));
				Reporter.log("User has clicked on existing center.", true);
				return new ClientCreationPage(driver);
			}

			flag = true;

		} catch (Exception e) {
			Reporter.log("Center is not found----> ", true);
			Reporter.log("User is attempting to clickon center button to create a new center", true);
			waitForElementVisibility(centerButton);
			click(centerButton);

			Reporter.log("User is able to clickon center button to create a new center", true);
			return new CenterCreationPage(driver);
		}

		return null;
	}

	public ClientCreationPage createClientForExistingCenter(String groupName) throws InterruptedException {
		HashMap<String, String> value = readExcelData("CENTERCREATION", groupName);
		selectGroup(value.get("GROUPNAME"));
		try {
			click(addButton);

		} catch (Exception e) {

		}
		return new ClientCreationPage(driver);

	}

	public void clickGroupTab() {

		try {
			waitForElementVisibility(groupTab);
			mouseHoverAndClick(groupTab);
		} catch (Exception e) {
			click(groupTab);
		}

	}

	public ClientCreationPage searchExistingCenter(String existingCenter) throws InterruptedException, InvalidFileFormatException, IOException, AWTException {

		HashMap<String, String> value = readExcelData("EXISTINGCENTER", existingCenter);

		log.debug(" User is searching for center");
		Thread.sleep(1200);
		scrollUp();
		waitForElementVisibility(searchDropdown);
		searchDropdown.click();
		group.click();
		Reporter.log("User is attempting to click on search bar", true);
		search_bt.click();
		Reporter.log("User has clicked on search bar", true);
		search_bar.clear();
		Reporter.log("User has cleared on search bar", true);
		Thread.sleep(1500);
		Reporter.log("User is attempting to send the value in search bar", true);
		System.out.println("Value********" + getCenterName());
		//search_bar.sendKeys(value.get("NAME"));
		search_bar.sendKeys(getCenterName());
		search_bar.sendKeys(Keys.ENTER);
		Reporter.log("User has send the value in search bar", true);
		Thread.sleep(1000);
		try {
			WebElement centerFound = driver.findElement(By.partialLinkText(getCenterName()));
			if (centerFound.isDisplayed()) {
				Reporter.log("Center is found!", true);
				centerFound.click();
				clickGroupTab();
				selectGroup(value.get("GROUPNUMBER"));
				click(memberTab);
				Thread.sleep(200);
				selectClientAdd(value.get("MEMBERNUMBER"));
				Reporter.log("Client has been selected", true);
				log.info("User found the center  -->" + getCenterName());
				Reporter.log("User has clicked on existing center.", true);

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			System.out.println(e.getMessage());
	
		}

		return new ClientCreationPage(driver);
	}

	public ClientCreationPage goToGroupAndcreateClient(String groupName, String clientId) throws InterruptedException {

		selectGroup(groupName);
		click(memberTab);
		Thread.sleep(200);
		selectClientAdd(clientId);
		Reporter.log("Client has been selected", true);

		return new ClientCreationPage(driver);
	}
	
	public void selectClientAdd(String clientId) throws InterruptedException {

		loop: for (int i = 1; i < 15; i++) {
			try {
				if (driver.findElement(By
						.xpath("//*[@id='wrap']/div/div/div/section/div/div/div/div/div[2]/div/div/div[2]/div[2]/table/tbody/tr["
								+ clientId + "]/td[5]/a"))
						.isDisplayed()) {

					Thread.sleep(300);

					driver.findElement(By
							.xpath("//*[@id='wrap']/div/div/div/section/div/div/div/div/div[2]/div/div/div[2]/div[2]/table/tbody/tr["
									+ clientId + "]/td[5]/a"))
							.click();

					System.out.println("Found " + clientId + " and clicked on it");
					log.info("Found " + clientId + " and clicked on it");
					break loop;
				}
			} catch (WebDriverException e) {
				e.printStackTrace();
				e.getMessage();

				log.info(e.getMessage());
				log.info("unable to find the " + clientId + " and click on it");
				System.err.println("unable to find the " + clientId + " and click on it");

			}
			Thread.sleep(1000);

		}

	}
 
	public void selectClientOptRSD(String clientName) throws InterruptedException {

		loop: for (int i = 1; i < 6; i++) {
			try {
				if (driver.findElement(By
						.xpath("//table/tbody/tr/td[contains(.,'"+clientName+"')]/following-sibling::td/a[@ng-click='optForRSDSubsidairy(member)']"))
						.isDisplayed()) {

					Thread.sleep(300);

					driver.findElement(By
							.xpath("//table/tbody/tr/td[contains(.,'"+clientName+"')]/following-sibling::td/a[@ng-click='optForRSDSubsidairy(member)']"))
							.click();

					System.out.println("Found client " + clientName + " and clicked on it");
					log.info("Found client " + clientName + " and clicked on it");
					System.err.println("Found client--> " + clientName + " <---and clicked on it");
					break loop;
				}
			} catch (WebDriverException e) {
				e.printStackTrace();
				e.getMessage();

				log.info(e.getMessage());
				log.info("unable to find the client " + clientName + " and click on it");
				System.err.println("unable to find the client " + clientName + " and click on it");

			}
			Thread.sleep(1000);

		}

	}
	
	public OptForRSDPage searchCenterAndClientOPTForRSD(String rsd) throws InterruptedException, InvalidFileFormatException, IOException {

		HashMap<String, String> value = readExcelData("OPTFORRSD", rsd);

		 
		
		log.debug(" User is searching for center");
		Thread.sleep(800);
		waitForElementVisibility(searchDropdown);
		if(searchDropdown.isDisplayed()){
			Thread.sleep(500);
			try {
				System.err.println("Search Dropdown is displayed " );
				searchDropdown.click();
				System.err.println("Clicked on group Dropdown " );
			} catch (Exception e) {
				 mouseHoverAndClick(searchDropdown);
			}
			
			
		} 
		group.click();

		Reporter.log("FM is attempting to click on search bar", true);
		search_bt.click();
		Reporter.log("FM has clicked on search bar", true);
		search_bar.clear();
		Reporter.log("FM has cleared on search bar", true);
		Thread.sleep(1500);
		Reporter.log("FM is attempting to send the value in search bar", true);
//		System.out.println("Value********" + value.get("CENTERNAME"));
//		search_bar.sendKeys(value.get("CENTERNAME"));
		
		System.err.println("Value********" + getCenterName());
		search_bar.sendKeys(getCenterName());
		
		search_bar.sendKeys(Keys.ENTER);
		Reporter.log("FM has send the value in search bar", true);
		Thread.sleep(1000);
		try {
			//WebElement centerFound = driver.findElement(By.partialLinkText(value.get("CENTERNAME")));
			WebElement centerFound = driver.findElement(By.partialLinkText(getCenterName()));
			if (centerFound.isDisplayed()) {
				Reporter.log("Center is found!", true);
				centerFound.click();
				clickGroupTab();
				selectGroup(value.get("GROUPNUMBER"));
				click(memberTab);
				Thread.sleep(200);
			//	selectClientOptRSD(value.get("CLIENTNAME"));
				selectClientOptRSD(getClientFullName());
				Reporter.log("Client has been selected", true);
				log.info("User found the center  -->" + value.get("CENTERNAME"));
				Reporter.log("User has clicked on existing center.", true);

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			System.out.println(e.getMessage());
	
		}

		return new OptForRSDPage(driver);
	}

	
}
