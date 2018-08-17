



package com.amazon.pages;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jfree.util.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends BasePage {

	@FindBy(id = "uid1")
	public WebElement emailTextField;

	@FindBy(id = "pwd1")
	public WebElement passwordTextField;

	@FindBy(id = "login-button")
	public WebElement signInBtn;

	@FindBy(xpath = "//h1[@class='page-title' and contains(text(),'Welcome!')]")
	public WebElement welcomeTitle;

	@FindBy(xpath = "//*[@id='main-block']/div[1]/div/div[2]/p/small[1]")
	public WebElement buildVersion;

	@FindBy(xpath = "//*[@id='login-button']")
	public WebElement submitButton;
	
	
	
	public LoginPage(WebDriver driver) {
		super(driver);

		PageFactory.initElements(driver, this);
		waitingForTheElementToLoad(emailTextField);
		Assert.assertTrue(emailTextField.isDisplayed(), "Not in Login Screen");

	}

	protected HashMap<String, String> login;

	public LoginPage assertLoginPage(String loginVal) {

		// Assert to check if we're on the login page
		Assert.assertEquals(loginVal, "Novobank ");
		return this;
	}

	/*
	 * This method is used to login as different user
	 * 
	 */
	public HomePage login(String loginVal) throws InterruptedException {
		try {
			Log.info("User is attempting to login!");
			String build = buildVersion.getText();
			setBuildVersion(build);

			System.out.println("*****" + loginVal);
			checkExecutionStatusForSkip(loginVal);
			login = readExcelData("LOGIN", loginVal);
			waitForElementVisibility(emailTextField);
			emailTextField.clear();
			emailTextField.sendKeys(login.get("USERNAME") + "");
			passwordTextField.clear();
			passwordTextField.sendKeys(login.get("PASSWORD"));
			Thread.sleep(1000);
			passwordTextField.sendKeys(Keys.ENTER);
			//	submitButton.click();
		
			return new HomePage(driver);
		} catch (Exception e) {
			Log.error("User failed to login!", e);
		}
		Log.info("User is logged in succesfully!");
		return null;
	}

}
