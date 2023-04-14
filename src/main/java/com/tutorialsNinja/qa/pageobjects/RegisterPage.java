package com.tutorialsNinja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;

	@FindBy(name="firstname")
	private WebElement firstNameField;
	
	@FindBy(name="lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id = "input-telephone")
	private WebElement telephoneField;
	
	@FindBy(xpath="//*[@id=\"input-password\"]")
	private WebElement passwordField;
	
	@FindBy(xpath="//*[@id=\"input-confirm\"]")
	private WebElement confirmField;
	
	@FindBy(xpath="//input[@name='newsletter']['@value=1']")
	private WebElement newsletterOption;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyOption;
	
	@FindBy(xpath="//*[@id=\"content\"]/form/div/div/input[2]")
	private WebElement continueOption;
	
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	private WebElement duplicateEmail;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement priacyPolicyWarning;
	
	@FindBy(xpath="//div[contains(@class,'text-danger')]")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}
	
	public void enterTelephone(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String confirmPasswordText) {
		confirmField.sendKeys(confirmPasswordText);
	}
	
	public void clickOnNewsletterOption() {
		newsletterOption.click();
	}
	
	public void clickOnPrivacyPolicy() {
		privacyPolicyOption.click();
	}
	
	public void clickOnContinue() {
		continueOption.click();
	
	}
	
	public String duplicateEmailWarningMessage() {
		String duplicateEmailWarning = duplicateEmail.getText();
		return duplicateEmailWarning;
	}
	public String privacyPolicyWarningMessage(){
		String privacyWarningMessage = priacyPolicyWarning.getText();
		return privacyWarningMessage;
	}
	public String firstNameWarningMessage() {
		String fnameWarningMessage = firstNameWarning.getText();
		return fnameWarningMessage;
	}
	
	public String lastNameWarningMessage() {
		String lnameWarningMessage = lastNameWarning.getText();
		return lnameWarningMessage;
	}
	
	public String emailWarningMessage() {
		String emailTextWarningMessage = emailWarning.getText();
		return emailTextWarningMessage;
	}
	public String telephoneWarningMessage() {
		String teleWarningMessage = telephoneWarning.getText();
		return teleWarningMessage;
	}
	
	public String passwordWarningMessage() {
		String passWarningMessage = passwordWarning.getText();
		return passWarningMessage;
	}
	
	
}
