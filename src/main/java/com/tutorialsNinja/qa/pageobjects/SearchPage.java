package com.tutorialsNinja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
 public WebDriver driver;
	
 
 @FindBy(linkText="HP LP3065")
	private WebElement productSearchMessage;
 
 @FindBy(xpath="//*[@id=\"content\"]/p[2]")
 private WebElement noProductSearchWarning;
 
 
 
 public SearchPage(WebDriver driver) {
	 this.driver = driver;
	 PageFactory.initElements(driver, this);
 }
 
 public boolean retrieveProductSearchMessage() {
	boolean productSearchRetrieve = productSearchMessage.isDisplayed();
	return productSearchRetrieve;
 }
 
 public String retrieveNoProductSearch() {
	 String noProductSearchMessage = noProductSearchWarning.getText();
	 return noProductSearchMessage;
 }
}
