package com.amazon.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.amazon.pages.BasePage;
import com.amazon.pages.HomePage;
import com.amazon.pages.LoginPage;
import com.amazon.utils.JavaUtils;

public class AmazonEndToEndFlow {

	WebDriver driver;
	public BasePage bp;
	public LoginPage lp = new LoginPage(driver);
	public HomePage hp = new HomePage(driver);
	public HashMap<String, String> testExecutiondata;
	JavaUtils utils = new JavaUtils();
	public List<String> workflowId;
	public static String methodName;
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
	Date date = new Date();
	String testCaseID;

	final static Logger log = Logger.getLogger("AmazonEndToEndFlow");

	@BeforeClass()
	public void setup() throws IOException {
		bp = new BasePage(driver);
		driver = bp.launchBrowser();
		lp = bp.gotoLoginPage();

		Reporter.log("Browser launched", true);
	}

	@Test
	public void AmazonFlow() throws AWTException, InvalidFormatException, IOException {

		try {

			workflowId = bp.returnAllWorkFlows("TESTEXECUTION");
			for (String id : workflowId) {

				testExecutiondata = utils.readExcelData("TESTEXECUTION", id);
//				String[] ExecutionDetails = { testExecutiondata.get("WORKFLOW").toUpperCase(), methodName,testExecutiondata.get("TESTDESCRIPTION").toUpperCase(), dateFormat.format(date), "PASS" };
				loginAndNavigateToHome(id);
//				ExecutionDetails[1] = methodName;
//				if (ExecutionDetails[1] != null) {
//					utils.writeExecutionStatusToExcel("TestResult", ExecutionDetails);
//				}
				product(id);
//				ExecutionDetails[1] = methodName;
//				if (ExecutionDetails[1] != null) {
//					utils.writeExecutionStatusToExcel("TestResult", ExecutionDetails);
//				}

			}

		} catch (Exception e) {

			// basePage.takeScreenShot();
			bp.screenShot();
			e.printStackTrace();
			methodName = e.getStackTrace()[0].getMethodName();

			System.err.println(e.getMessage());

		}

	}

	public void loginAndNavigateToHome(String WORKFLOW) throws Exception {
		log.info("User is attempting to login ");
		testExecutiondata = lp.readExcelData("TESTEXECUTION", WORKFLOW);
		testCaseID = WORKFLOW;
		String currentLoginUser = testExecutiondata.get("LOGIN");
		if (!currentLoginUser.equalsIgnoreCase("SKIP")) {

			System.out.println("User Logged in : " + currentLoginUser);
			Reporter.log("User login is in progress", true);

			hp = lp.login(currentLoginUser);
			methodName = "loginAndNavigateToHome";
			if (!(hp instanceof HomePage)) {
				hp = new HomePage(driver);
			}
			String product = testExecutiondata.get("SEARCH");
			 hp.searchProduct(product);

		}
	}
	
	
	public void product(String WORKFLOW) throws Exception {
		log.info("User is on Product Page");
		testExecutiondata = hp.readExcelData("TESTEXECUTION", WORKFLOW);
		testCaseID = WORKFLOW;
		String currentLoginUser = testExecutiondata.get("PRODUCTS");
		if (!currentLoginUser.equalsIgnoreCase("SKIP")) {

			System.out.println("User Logged in : " + currentLoginUser);
			Reporter.log("User login is in progress", true);

			//hp = lp.login(currentLoginUser);
			if (!(hp instanceof HomePage)) {
				hp = new HomePage(driver);
			}
			// hp.categoryTab();

		}
	}

//	@Test
//	public void login(String WORKFLOW) throws InterruptedException, IOException {
//		testExecutiondata = lp.readExcelData("TESTEXECUTION", WORKFLOW);
//		HomePage hp = lp.login(JavaUtils.getPropValue("username"), JavaUtils.getPropValue("password"));
//		hp.categoryTab();
//
//	}

	@AfterTest
	public void close() {
		driver.quit();
	}
}
