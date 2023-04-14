package com.tutorialsNinja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	 WebDriver driver;
	//objects
	@FindBy(xpath="//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")
 private WebElement myAccountDropDown;
	
	@FindBy(linkText="Login")
	private WebElement LoginButton;

	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(name="search")
	private WebElement searchOption;
	
	@FindBy(xpath="//*[@id=\"search\"]/span/button")
	private WebElement clicksearchoption;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	//Actions=methods
	
	public void clickOnMyAccount() {
		myAccountDropDown.click();
	}
	
	public Login clickOnLogin() {
		LoginButton.click();
		return new Login(driver);
		
	}
	public void selectRegisterOption() {
		registerOption.click();
		
	}
	
	public void enterProductDetails(String searchText) {
		searchOption.sendKeys(searchText);
	}
	
	public SearchPage clickOnSearch() {
		clicksearchoption.click();
		return new SearchPage(driver);
	}
}

