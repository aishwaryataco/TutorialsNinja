package com.tutorialsNinja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
 WebDriver driver;
 
	@FindBy(linkText="Modify your wish list")
	WebElement editYourAccountInformation;
	
	
	 public AccountPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
		 
	 }
	 
	 public boolean getDisplayStatusAsEditYourAccountInformation() {
		boolean displayStatus = editYourAccountInformation.isDisplayed();
		return displayStatus;
	 }
}
