package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;


public class HomePage extends HeaderPage {
	
	
	public HomePage(WebDriver driver,ExtentTest logger)
	{
		super(driver,logger);		
		PageFactory.initElements(driver, this);		
	}

}
