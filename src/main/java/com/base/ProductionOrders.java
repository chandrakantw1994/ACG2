package com.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductionOrders extends LoginPage
{
	//Variables...
	public static WebElement POpage= driver.findElement(By.xpath("//a[contains(text(),'Production orders')]"));
	
	//
	
	
	//methods
	//-------------------------------------------------------------------------------------------
	public static void openProductionOrdersTab()
	{
		POpage.click();
	}
	//-------------------------------------------------------------------------------------------
	public static boolean checkSearchFilter()
	{
		if (driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]")).isDisplayed())
		{
			return true;
		}
		else
			return false;
	}
	//-------------------------------------------------------------------------------------------
	public static boolean checkPageSoter()
	{
		if (driver.findElement(By.xpath("//div[@id='productionorders_list_pages']")).isDisplayed())
		{
			return true;
		}
		else
			return false;
	}
	//--------------------------------------------------------------------------------------------
	public static boolean checkExcelIcon()
	{
		if (driver.findElement(By.xpath("//i[@title='Import from Excel']")).isDisplayed())
		{
			return true;
		}
		else
			return false;	
	}
	//---------------------------------------------------------------------------------------------
	public static boolean checkNewProductionOrderIcon()
	{
		if (driver.findElement(By.xpath("//i[@title='New production order']")).isDisplayed())
		{
			return true;
		}
		else
			return false;
	}
	//---------------------------------------------------------------------------------------------
	public static boolean checkTableHeader()
	{
		List<WebElement> poheaderlist = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div[2]/div[4]/table[2]/thead/tr/th"));
		int phlcount=0;
		for (WebElement phl : poheaderlist)
		{	
			String sphl = phl.getAttribute("aria-label");
			//System.out.println(sphl);
			if (sphl.contains("Id") ||
				sphl.contains("PO order name / number") ||
				sphl.contains("Product") ||
				sphl.contains("Location") ||
				sphl.contains("Regulation") ||
				sphl.contains("Production date") ||
				sphl.contains("Requested") ||
				sphl.contains("Produced") ||
				sphl.contains("Created") ||
				sphl.contains("Status") ||
				sphl.contains("Actions") 
				)
			{
				phlcount++;
				//System.out.println(phlcount);
			}
		}
		if (phlcount==11)
		{
			return true;
		}
		else
			return false;
	}	
	
	
	
}
