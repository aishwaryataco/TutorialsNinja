package com.tutorialsNinja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsNinja.qa.pageobjects.HomePage;
import com.tutorialsNinja.qa.pageobjects.RegisterPage;
import com.tutorialsNinja.qa.pageobjects.RegisterSuccessPage;
import com.tutorialsNinja.qa.utils.Utilities;

public class RegisterTest extends Base  {
	public RegisterTest() {
		super();
	}
	WebDriver driver;
	
	
	
	@BeforeMethod
	public void setUp() {
		 driver = InitialiseBrowserAndOpenApplication();
		 HomePage homepage = new HomePage(driver);
		 homepage.clickOnMyAccount();
		  homepage.selectRegisterOption();
			
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority=1)
	
	public void verifyRegisterWithMandatoryFields() {
		
		
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstname"));
		registerpage.enterLastName(dataProp.getProperty("lastname"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(dataProp.getProperty("telephone"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnPrivacyPolicy();
		 registerpage.clickOnContinue();
		
		RegisterSuccessPage registersuccesspage = new RegisterSuccessPage(driver);
		String actualSuccessMessage = registersuccesspage.retrieveRegisterSuccessMessage();
		Assert.assertEquals(actualSuccessMessage, "Your Account Has Been Created!", "Your Account Has Been Created! messgae not displayed");
		
	
		
	}
	@Test(priority=2)
	public void verifyRegisterWithAllFields() {
		
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstname"));
		registerpage.enterLastName(dataProp.getProperty("lastname"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(dataProp.getProperty("telephone"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnNewsletterOption();
		registerpage.clickOnPrivacyPolicy();
		registerpage.clickOnContinue();
		
		RegisterSuccessPage registersuccesspage = new RegisterSuccessPage(driver);
		String actualSuccessMessage = registersuccesspage.retrieveRegisterSuccessMessage();
		
		
		Assert.assertEquals(actualSuccessMessage, dataProp.getProperty("accountSuccessMessage"), "Your Account Has Been Created! messgae not displayed");
		
	

	}
	@Test(priority=3)
	public void verifyRegisteringWithExistingEmail() {
		
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstname"));
		registerpage.enterLastName(dataProp.getProperty("lastname"));
		registerpage.enterEmail(prop.getProperty("validEmail"));
		registerpage.enterTelephone(dataProp.getProperty("telephone"));		
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnNewsletterOption();
		registerpage.clickOnPrivacyPolicy();
		registerpage.clickOnContinue();
		
		String actualSuccessMessage = registerpage.duplicateEmailWarningMessage();
		Assert.assertEquals(actualSuccessMessage, dataProp.getProperty("duplicateEmailWarning"), "Warning: E-Mail Address is already registered! messgae not displayed");
	}
	@Test(priority=4)
	public void verifyRegisteringWithNoCredentials() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.clickOnContinue();
		
		String actualPrivacyPolicyMessage = registerpage.privacyPolicyWarningMessage();
		Assert.assertEquals(actualPrivacyPolicyMessage, dataProp.getProperty("privacyPlocyWarning"), "Warning: You must agree to the Privacy Policy! messgae not displayed");
		
		String actualFirstNameMessage = registerpage.firstNameWarningMessage();
		Assert.assertEquals(actualFirstNameMessage, dataProp.getProperty("firstnameWarning"), "First Name must be between 1 and 32 characters! messgae not displayed");
		
		String actualLastNameMessage = registerpage.lastNameWarningMessage();
		Assert.assertEquals(actualLastNameMessage, dataProp.getProperty("lastnameWarning"), "Last Name must be between 1 and 32 characters! messgae not displayed");
		
		String actualEmailWarning = registerpage.emailWarningMessage();
		Assert.assertEquals(actualEmailWarning, dataProp.getProperty("emailWarning"), "E-Mail Address does not appear to be valid! messgae not displayed");
		
		String actualTelephoneWarning = registerpage.telephoneWarningMessage();
		Assert.assertEquals(actualTelephoneWarning,dataProp.getProperty("telephoneWarning"), "Telephone must be between 3 and 32 characters! message not displayed");
		
		String actualPasswordWarning = registerpage.passwordWarningMessage();
		Assert.assertEquals(actualPasswordWarning, dataProp.getProperty("passwordWarning"), "Password must be between 4 and 20 characters! message not displayed");
		
		
		

		
	}

}
