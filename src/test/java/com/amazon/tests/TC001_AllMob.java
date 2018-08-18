package com.amazon.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.amazon.pages.BasePage;
import com.amazon.pages.HomePage;
import com.amazon.pages.LoginPage;
import com.amazon.utils.JavaUtils;

public class TC001_AllMob {
	public BasePage bp;
	public LoginPage lp;
	public HomePage hp;
	
	WebDriver driver;

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
		  lp.categoryTab();
		 
	
	}
	
	
	
	@AfterTest
	public void close() {
		driver.quit();
	}
}
