package com.amazon.tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.*;

import com.amazon.pages.*;
import com.amazon.utils.JavaUtils;

public class AmazonEndToEndFlow {
	public BasePage bp;
	public LoginPage lp;
	
	WebDriver driver;
	final static Logger log = Logger.getLogger("AmazonEndToEndFlow");

	@BeforeClass()
	public void setup() throws IOException {
		bp = new BasePage(driver);
		driver = bp.launchBrowser();
		lp = bp.gotoLoginPage();
		Reporter.log("Browser launched", true);
	}
	
	@Test
	public void login() throws InterruptedException, IOException {
		lp.login(JavaUtils.getPropValue("username"), JavaUtils.getPropValue("password"));
		 
	
	}
}
