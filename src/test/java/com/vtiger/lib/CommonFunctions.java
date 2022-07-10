package com.vtiger.lib;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class CommonFunctions {
	private WebDriver driver;
	private WebDriverWait wait;
	private ExtentTest logger;
	public CommonFunctions(WebDriver driver,ExtentTest logger)
	{
		this.logger=logger;
		this.driver=driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	}
	
	public void entervalue(WebElement elm,String val)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(elm));
			elm.clear();
			elm.sendKeys(val);
			if(elm.getAttribute("value").equals(val))
			{
				
				logger.pass("Value "+val+" has been entered into "+elm.getAttribute("name")+" fields");
			}
			else
			{
				
				logger.fail("<a href='"+getscreenshot()+"' target='_blank'><span class='label end-time'>Screenshot</span></a>"+" Value "+val+" did not enter into "+elm.getAttribute("name")+" fields ");
			}
		}
		catch(Exception e)
		{
			logger.fail("<a href='"+getscreenshot()+"' target='_blank'><span class='label end-time'>Screenshot</span></a>"+" Value "+val+" did not enter into "+elm.getAttribute("name")+" fields due to exception "+e.getMessage());
			
		}
	}
	
	public void clickElement(WebElement elm,String elmName)
	{
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(elm));			
			elm.click();			
			logger.pass(elmName+ " clicked successfully");
		}
		catch(Exception e)
		{
			logger.fail("<a href='"+getscreenshot()+"' target='_blank'><span class='label end-time'>Screenshot</span></a>"+elmName+" did not click due to error "+e.getMessage());
		}
	}
	
	
	public void DisplayElement(WebElement elm,String elmName)
	{
		
		try
		{
			wait.until(ExpectedConditions.visibilityOf(elm));			
			elm.isDisplayed();		
			logger.pass(elmName+ " displayed successfully");
		}
		catch(Exception e)
		{
			logger.fail("<a href='"+getscreenshot()+"' target='_blank'><span class='label end-time'>Screenshot</span></a>"+elmName+" did not display due to error "+e.getMessage());
			
		}
	}
	
	public String getscreenshot() 
	{
		DateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		Date d = new Date();
		String str = f.format(d);
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/src/test/java/com/vtiger/reports/screenshots/image"+str+".png";
		//Move image file to new destination
		File DestFile=new File(path);
		//Copy file at destination
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
		
	}

}
