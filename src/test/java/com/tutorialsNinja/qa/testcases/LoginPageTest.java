package com.tutorialsNinja.qa.testcases;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsNinja.qa.pageobjects.AccountPage;
import com.tutorialsNinja.qa.pageobjects.HomePage;
import com.tutorialsNinja.qa.pageobjects.Login;
import com.tutorialsNinja.qa.utils.Utilities;


public class LoginPageTest extends Base {
	public LoginPageTest() {
		super();
	}
	public WebDriver driver;
	public Login login;
	
	@BeforeMethod
	public void setUp() {
		driver = InitialiseBrowserAndOpenApplication();
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		 login = homepage.clickOnLogin();
			
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email,String password) {
		
		
		login.enterEmail(email);
		login.enterpassword(password);
		AccountPage accountpage = login.clickOnLogin();
		
		 
		accountpage.getDisplayStatusAsEditYourAccountInformation();
	
		Assert.assertTrue(accountpage.getDisplayStatusAsEditYourAccountInformation(),"Expected warning message is not displayed");
		driver.quit();
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	
	@Test(priority=2)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		login.enterEmail(prop.getProperty("validEmail"));
		login.enterpassword(dataProp.getProperty("invalidpassword"));
		login.clickOnLogin();
		String actualWarningMessage = login.retrieveEmailAndPasswordNotMatchingWarning();
		
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning message is not displayed");
		
	}
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		
		login.enterEmail(Utilities.generateEmailWithTimeStamp());
		login.enterpassword(prop.getProperty("validPassword"));
		login.clickOnLogin();
		String actualWarningMessage = login.retrieveEmailAndPasswordNotMatchingWarning();
		
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning message is not displayed");
		
	}
	@Test(priority=4)
	public void verifyLoginWithInvalidCredentials() {
		
		
		login.enterEmail(Utilities.generateEmailWithTimeStamp());
		login.enterpassword(dataProp.getProperty("invalidpassword"));
		login.clickOnLogin();
		String actualWarningMessage = login.retrieveEmailAndPasswordNotMatchingWarning();
		String expectedWarningMessage = dataProp.getProperty("emailAndPasswordNomatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning message is not displayed");
	
	}
	@Test(priority=5)
	public void verifyLoginWithoutProvidingCredentials() {
		
		
		login.clickOnLogin();
		String actualWarningMessage = login.retrieveEmailAndPasswordNotMatchingWarning();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning message is not displayed");
		
	}
	public String generateTimeStamp() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}
}
