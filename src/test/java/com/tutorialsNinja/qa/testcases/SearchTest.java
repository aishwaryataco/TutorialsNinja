package com.tutorialsNinja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsNinja.qa.pageobjects.HomePage;
import com.tutorialsNinja.qa.pageobjects.SearchPage;

public class SearchTest extends Base{
public WebDriver driver;
public SearchPage searchpage;
@BeforeMethod
public void setUp() {
	driver = InitialiseBrowserAndOpenApplication();
}
@AfterMethod
public void tearDown() {
	driver.quit();
}
@Test(priority=1)
 public void verifySearchWithValidProduct() {
	HomePage homepage = new HomePage(driver);
	homepage.enterProductDetails(dataProp.getProperty("validproduct"));
	 homepage.clickOnSearch();
	 SearchPage searchpage = new SearchPage(driver);
	
	 Assert.assertTrue(searchpage.retrieveProductSearchMessage(),"valid product hp is not displayed");
	 
 }
@Test(priority=2)
public void verifySearchWithInvalidProduct() {
	HomePage homepage = new HomePage(driver);
homepage.enterProductDetails(dataProp.getProperty("invalidproduct"));
homepage.clickOnSearch();

SearchPage searchpage = new SearchPage(driver);
	String actualSearchMessage = searchpage.retrieveNoProductSearch();
	Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noproductsearch"),"There is no product that matches the search criteria. message not displayed");
}
@Test(priority=3)
 public void verifySearchWithoutAnyProduct() {
	HomePage homepage = new HomePage(driver);
	homepage.clickOnSearch();
	SearchPage searchpage = new SearchPage(driver);
	String actualSearchMessage = searchpage.retrieveNoProductSearch();
	Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noproductsearch"),"There is no product that matches the search criteria. message not displayed");
 }
}
