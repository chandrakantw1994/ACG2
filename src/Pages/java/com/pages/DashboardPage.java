package com.pages;

import org.openqa.selenium.By;

public class DashboardPage extends LoginPage
{
	public static boolean isDashboardDisplayed()
	{
		boolean Dashboard = driver.findElement(By.partialLinkText("Dashboard")).isDisplayed();
		
		return Dashboard;
	}

}
