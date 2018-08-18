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

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.driver = driver;

	}

	@FindBy(xpath = "//*[@title='Apple iPhone 7 (Black, 32GB)']")
	public WebElement iphone7;

	@FindBy(xpath = "//*[@id=\"nav-flyout-shopAll\"]/div[2]/span[6]/span")
	public WebElement mobile;

	@FindBy(xpath = "//*[@id=\"nav-flyout-shopAll\"]/div[3]/div[6]/div[1]/div/a[1]/span")
	public WebElement allMobile;

	@FindBy(linkText = "Honor Play")
	public WebElement honorPlay;

	protected HashMap<String, String> pdp;

	public ProductPage productDiscription(String loginVal) throws IOException, InterruptedException {
		pdp = readExcelData("PRODUCTS", loginVal);
		iphone7.click();
	//	search.sendKeys(pdp.get("PRODUCTNAME"), Keys.ENTER);
		return new ProductPage(driver);
	}

	
	
	
	

}



