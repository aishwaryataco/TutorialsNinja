package com.tutorialsNinja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
	
	WebDriver driver;
	
	//objects
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
 @FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
 
 @FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
 private WebElement emailPasswordNotMatchingWarning;
 
 
 public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
 
 //Actions
 
 public void enterEmail(String emailText) {
	 
	 emailField.sendKeys(emailText);
	 
 }
 
public void enterpassword(String passwordText) {
	 
	passwordField.sendKeys(passwordText);
	 
 }

public AccountPage clickOnLogin() {
	 
	loginButton.click();
	return new AccountPage(driver);
	 
}
public String retrieveEmailAndPasswordNotMatchingWarning() {
	String warningText = emailPasswordNotMatchingWarning.getText();
	return warningText;
}
}


