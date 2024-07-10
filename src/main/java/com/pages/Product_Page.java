package com.pages;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.base.FrameworkBase;
import com.base.UtilityClass;

public class Product_Page extends FrameworkBase
{
	
		static WebElement v_product_page; 			
		static WebElement v_new_product_icon; 		
		static WebElement v_product_name;			
		static Select v_manufacturer; 				
		static WebElement v_product_packaging_tab;	
		static WebElement v_switch_to_advance_mode;	
		//static WebElement 
		//static WebElement 
		//static WebElement 
		//static WebElement 
		//static WebElement 
		//static WebElement 
		
		static FileInputStream file;
		static Workbook workbook;
		static Sheet sheet;
		static int subject_loop;
		
		
		public static void addNewProduct()
		{
			try	{
				
				setInputExcel("C:/Users/Chandrakant.W/eclipse-workspace/ACG/BookforAutomation.xlsx");
				sheet = workbook.getSheetAt(1);
				
				subject_loop = (int) sheet.getRow(1).getCell(1).getNumericCellValue();
				subject_loop = subject_loop*2;
				
				
				for(int i = 2; i <= subject_loop; i=i+2)
				{
				
				
					v_new_product_icon 		= driver.findElement(By.xpath("//i[@title='New product']"));
					UtilityClass.waitfor(30, By.xpath("//i[@title='New product']"));
					v_new_product_icon.click();
				
					
					v_product_name 			= driver.findElement(By.xpath("//input[@id='product_name']"));
					v_product_name.sendKeys(sheet.getRow(3).getCell(2).getStringCellValue());
					
					v_manufacturer 			= new Select(driver.findElement(By.xpath("//select[@id='manufacturer_id']")));
					v_manufacturer.selectByVisibleText(sheet.getRow(4).getCell(2).getStringCellValue());
					
					
					driver.findElement(By.xpath("//a[contains(text(),'Product packaging')]")).click();
					driver.findElement(By.xpath("//a[contains(text(),'Switch to advanced mode')]")).click();
					driver.findElement(By.xpath("//img[@src='/img/add.png']")).click();
					driver.findElement(By.xpath("//img[@src='/img/add.png']")).click();
					
					Select packaging_unit1 = new Select(driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[3]/td[3]/select")));
					packaging_unit1.selectByVisibleText("Item");
					
					Select packaging_unit2 = new Select(driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[5]/td[3]/select")));
					packaging_unit2.selectByVisibleText("Shipper");
					
					Select packaging_unit3 = new Select(driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[7]/td[3]/select")));
					packaging_unit3.selectByVisibleText("Palette");
					
					Select unit_code_type1 = new Select(driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[3]/td[4]/select")));
					unit_code_type1.selectByVisibleText("GTIN");
					
					Select unit_code_type2 = new Select(driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[5]/td[4]/select")));
					unit_code_type2.selectByVisibleText("GTIN");
					
					Select unit_code_type3 = new Select(driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[7]/td[4]/select")));
					unit_code_type3.selectByVisibleText("SSCC");
					
					Select indicator1 = new Select(driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[3]/td[5]/select")));
					indicator1.selectByVisibleText("0");
					
					Select indicator2 = new Select(driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[5]/td[5]/select")));
					indicator2.selectByVisibleText("1");
					
					Select indicator3 = new Select(driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[7]/td[5]/select")));
					indicator3.selectByVisibleText("2");
					
					driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[3]/td[6]/input")).sendKeys(String.valueOf(sheet.getRow(8).getCell(2).getNumericCellValue()));
					driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[5]/td[6]/input")).sendKeys(String.valueOf(sheet.getRow(8).getCell(2).getNumericCellValue()));
					driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[7]/td[6]/input")).sendKeys(String.valueOf(sheet.getRow(8).getCell(2).getNumericCellValue()));
					
					
					driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[3]/td[7]/input")).sendKeys(String.valueOf(1));
					
					driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[3]/td[8]/input")).sendKeys(String.valueOf(1));
					driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[5]/td[8]/input")).sendKeys(String.valueOf(4));
					driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[7]/td[8]/input")).sendKeys(String.valueOf(2));
					
					driver.findElement(By.xpath("//i[@id='saveproductbutton']")).click();
					
					driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']/button[2]")).click();
				
				
				
				}		
			
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			
			
		}
		
		
		
		public static void setInputExcel(String s)
		{
			try 
			{
				file = new FileInputStream(s);
				workbook = new XSSFWorkbook(file);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	
}
