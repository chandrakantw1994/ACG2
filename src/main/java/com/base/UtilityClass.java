package com.base;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityClass extends FrameworkBase
{
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception
	{
		//Convert web driver object to TakeScreenshot...
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File DestFile=new File(fileWithPath);
		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
		//..
		
	}
	
	public static void waitfor(int t, By s)
	{
		WebDriverWait wait = new WebDriverWait(driver, t);
		wait.until(ExpectedConditions.visibilityOfElementLocated(s));
	}
	
	
}
