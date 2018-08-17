package com.amazon.utils;

import java.io.IOException;
 
import org.openqa.selenium.WebDriver;
 

import com.relevantcodes.extentreports.ExtentTest;


public class TestUtils {
	public WebDriver driver;

	public ExtentTest test;
//	BasePage basepage;


	//@BeforeClass
	public void setUp() throws IOException {

//		basepage = new BasePage(driver);
//		driver = basepage.launchBrowser();
//		basepage.navigateTo();

	}

	// @AfterClass
	public void tearDown() {
		driver.quit();
	}
}
