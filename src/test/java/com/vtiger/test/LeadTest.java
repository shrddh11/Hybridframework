package com.vtiger.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vtiger.pages.HomePage;
import com.vtiger.pages.LoginPage;

public class LeadTest extends BaseTest {

	@BeforeClass
	public void StartApp()
	{
		launchApp();
	}
	
	@Test(priority=2)
	public void verifyValidLogin()
	{
		logger = extent.createTest("verifyValidLogin_TC02");
		LoginPage lp = new LoginPage(driver,logger);
		lp.login("admin", "admin");
		HomePage hp = new HomePage(driver,logger);
		hp.clickLogout();
		extent.flush();
	}
	
	@Test(priority=1)
	public void verifyInValidLogin()
	{
		logger = extent.createTest("verifyInValidLogin_TC01");
		LoginPage lp = new LoginPage(driver,logger);
		lp.login("admin1", "admin123");	
		lp.verifyErrorMsg();
		extent.flush();
	}
	
	@AfterClass
	public void closeApp()
	{
		driver.quit();
	}
	
	
	
	
	
	
	
	
}
