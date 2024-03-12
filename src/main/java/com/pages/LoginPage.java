package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.base.FrameworkBase;

public class LoginPage extends FrameworkBase
{
	
	public static WebElement username;
	public static WebElement password;

	
	public static void performLogin(String user_name, String pass_word)
	{
		username = driver.findElement(By.xpath("//input[@name='email']"));
		username.sendKeys(user_name);
		
		password = driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys(pass_word);
		
		driver.findElement(By.xpath("//*[@id='loginhoder']/input")).click();
	}
}
