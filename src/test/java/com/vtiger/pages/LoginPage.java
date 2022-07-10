package com.vtiger.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.lib.CommonFunctions;

public class LoginPage {
	private WebDriver driver;
	private CommonFunctions cf;
	private ExtentTest logger;
	
	public LoginPage(WebDriver driver,ExtentTest logger)
	{
		this.driver = driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);
		cf = new CommonFunctions(driver,logger);
	}
	
	/*
	By tb_username = By.name("user_name");
	By tb_userpwd = By.name("user_password");
	By btn_login = By.name("Login");
	*/
	@FindBy(name="user_name")
	WebElement tb_username;
	
	@FindBy(name="user_password")
	WebElement tb_userpwd;
	
	@FindBy(name="Login")
	WebElement btn_login;
	
	@FindBy(xpath="//*[contains(text(),'You must specify a valid username and password.')]")
	WebElement txt_ErrMsg;
	
	public void login(String userid, String pwd)
	{
		enterusername(userid);
		enterpassword(pwd);
		clicklogin();
	}
	
	public void enterusername(String userid)
	{
		cf.entervalue(tb_username, userid);		
	}
	
	public void enterpassword(String pwd)
	{
		cf.entervalue(tb_userpwd, pwd);
	}
	
	public void clicklogin()
	{
		cf.clickElement(btn_login,"Login button");
	}
	
	public void verifyErrorMsg()
	{
		cf.DisplayElement(txt_ErrMsg, " Error Message: You must specify a valid username and password.");
	}

}
