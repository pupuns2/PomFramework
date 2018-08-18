
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

public class LoginPage extends BasePage {
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);

		PageFactory.initElements(driver, this);
		// waitingForTheElementToLoad(emailTextField);

	}

	@FindBy(xpath ="//*[@id=\"nav-link-yourAccount\"]/span[1]")
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

	public ElectronicPage categoryTab() throws IOException {
		click(categoryTab);
		click(mobile);
		click(allMobile);
		click(honor);
		screenShot();
		return new ElectronicPage(driver);
	}
	
	protected HashMap<String, String> login;

	/*
	 * This method is used to login as different user
	 * 
	 */
	public HomePage login(String email, String pass) throws InterruptedException {
//		signIn.click();
		click(signIn);
		waitingForTheElementToLoad(amazonEmail);
		amazonEmail.sendKeys(email);
		continueBtn.click();
		password.sendKeys(pass,Keys.ENTER);
		//continueBtn.click();

		return new HomePage(driver);

	}

}
