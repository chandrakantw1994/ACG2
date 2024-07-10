package com.tests;
import org.openqa.selenium.By;

import com.base.FrameworkBase;
import com.base.LoginPage;
import com.base.UtilityClass;
import com.pages.Product_Page;
import com.pages.Subject_Page;

public class testClass extends FrameworkBase 
{
	public static void main(String[] args) 
	{
		
		try {
		
		launchBrowser();
		
		openURL("https://172.16.70.116/");
		
		LoginPage.performLogin("Chandrakant", "tnt1234");
		
		driver.manage().window().maximize();
		
		
		Thread.sleep(3000);
		
		
		driver.findElement(By.linkText("MASTER DATA")).click();
		
		/*
		UtilityClass.waitfor(30, By.linkText("Subjects"));
		
		driver.findElement(By.linkText("Subjects")).click();
		
		UtilityClass.waitfor(30, By.xpath("//*[@title='New subject']"));
		
		Subject_Page.addNewSubject();
		*/
		
		Thread.sleep(3000);
		driver.findElement(By.linkText("Products")).click();
		Thread.sleep(3000);
		
		Product_Page.addNewProduct();
		
	
		
		
		
		
		
		
		
		
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
