
package com.amazon.pages;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jfree.util.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);

		PageFactory.initElements(driver, this);
	}

	// waitingForTheElementToLoad(emailTextField);

	@FindBy(xpath = "//*[@id=\"nav-link-yourAccount\"]/span[1]")
	public WebElement signIn;

	@FindBy(id = "ap_email")
	private WebElement amazonEmail;

	@FindBy(id = "continue")
	public WebElement continueBtn;

	@FindBy(id = "ap_password")
	private WebElement password;

	@FindBy(xpath = "//*[@id=\"nav-link-shopall\"]/span[2]")
	public WebElement categoryTab;

	@FindBy(xpath = "//*[@id=\"nav-flyout-shopAll\"]/div[2]/span[6]/span")
	public WebElement mobile;

	@FindBy(xpath = "//*[@id=\"nav-flyout-shopAll\"]/div[3]/div[6]/div[1]/div/a[1]/span")
	public WebElement allMobile;

	@FindBy(xpath = "//*[@alt='Honor Play']")
	public WebElement honor;

	protected HashMap<String, String> lp;

	/*
	 * This method is used to login as different user
	 * 
	 */
	public HomePage login(String loginVal) throws InterruptedException {
		// signIn.click();
		 lp = readExcelData("LOGIN", loginVal);
		click(signIn);
		waitingForTheElementToLoad(amazonEmail);
		amazonEmail.sendKeys(lp.get("USERNAME"));
		continueBtn.click();
		password.sendKeys(lp.get("PASSWORD"), Keys.ENTER);
		// continueBtn.click();

		return new HomePage(driver);

	}

}
