package com.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DashboardPage extends LoginPage
{
	//Variables.......
	public static boolean Dashboard = driver.findElement(By.partialLinkText("Dashboard")).isDisplayed();
	public static String user = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div[1]")).getText();
	public static String title = driver.getTitle();
	public static List<WebElement> ele = driver.findElements(By.xpath("//div[@class='menudiv']"));
	public static List<WebElement> ele2 = driver.findElements(By.xpath("//div[@class='datacontenttab']/a"));
	public static List<WebElement> ele3 = driver.findElements(By.xpath("//div[@class='head linearbackground']"));
	//methods
	//------------------------------------------------------------------------------
	public static boolean isDashboardDisplayed()
	{
		return Dashboard;
	}
	//------------------------------------------------------------------------------
	public static boolean verifyUser()
	{
		return user.contains("Selenium Automation");
	}
	//------------------------------------------------------------------------------
	public static boolean getPageTitle(String ExpectedTitle)
	{
		return title.equalsIgnoreCase(ExpectedTitle);
	}
	//------------------------------------------------------------------------------
	public static boolean checkModules()
	{
		int count=0;
		for (int i = 0; i < ele.size(); i++) 
		{
			
			WebElement we = ele.get(i);
			String me = we.getText();
			//System.out.println(me);

			if (me.equalsIgnoreCase("SERIALIZATION") 	|| 
				me.equalsIgnoreCase("MASTER DATA") 		|| 
				me.equalsIgnoreCase("ADMINISTRATION")
				) 
			{
				count = count + 1;
			}
			//System.out.println(count);
		}

		if (count == 3)
		{
			return true;
		}
		else
			return false;
	}
	//------------------------------------------------------------------------------
	public static boolean checkTabs()
	{
		int count1 = 0;
		
		for (WebElement we2 : ele2) 
		{

			String me2 = we2.getText();
			//System.out.println(me2);
			if (me2.equalsIgnoreCase("Reporting") || 
				me2.equalsIgnoreCase("Production orders") || 
				me2.equalsIgnoreCase("Request SSCC Information") || 
				me2.equalsIgnoreCase("AS2") || 
				me2.equalsIgnoreCase("Stock") || 
				me2.equalsIgnoreCase("Shipping orders")	|| 
				me2.equalsIgnoreCase("Reconciliation") || 
				me2.equalsIgnoreCase("VRS")
				) 
			{
				count1++;
			}

		}
		if (count1 == 8)
		{
			return true;
		}
		else
			return false;
	}
	//------------------------------------------------------------------------------
	public static boolean checkFrames()
	{
		int count2 = 0;
		
		for (WebElement we3 : ele3) 
		{
			String me3 = we3.getText();
			//System.out.println(me3);
			if (me3.equalsIgnoreCase("Local serial numbers pool") || 
				me3.equalsIgnoreCase("Tasks")	|| 
				me3.equalsIgnoreCase("Latest events")	|| 
				me3.equalsIgnoreCase("Central serial numbers pool")
				) 
			{
				count2++;
				//System.out.println(count2);
			}

		}
		if (count2 == 4)
		{
			return true;
		}
		else
			return false;
	}
	//-------------------------------------------------------------------------------
	
	
	
}
