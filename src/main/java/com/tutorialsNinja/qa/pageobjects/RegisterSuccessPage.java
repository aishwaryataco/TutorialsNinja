package com.tutorialsNinja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterSuccessPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement registerSuccessMessage;
	
	public RegisterSuccessPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String retrieveRegisterSuccessMessage() {
		String registerSuccessPageHeading = registerSuccessMessage.getText();
		return registerSuccessPageHeading;
	}

}
