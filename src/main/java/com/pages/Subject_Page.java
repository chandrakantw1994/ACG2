package com.pages;

import com.base.UtilityClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.FrameworkBase;

public class Subject_Page extends FrameworkBase
{	
	static WebElement v_Subject_page;
	static WebElement v_new_subject_icon;
	static WebElement v_subject_name;
	static Select v_subject_group;
	static Select v_subject_country;
	static WebElement v_subject_gcp;
	static WebElement v_subject_gln;
	static WebElement v_save_subject;
	static WebElement v_activate_subject;
	static FileInputStream file;
	static Workbook workbook;
	static Sheet sheet;
	static Row row;
	static Cell cell;
	
	static String newsubject;
	static int count=0;
	static int subject_loop;

	
	
	
	public static boolean addNewSubject()
	{
		try 
		{
			
		
			setInputExcel("C:/Users/Chandrakant.W/eclipse-workspace/ACG/BookforAutomation.xlsx");
			sheet=workbook.getSheetAt(0);
			
			subject_loop = (int) sheet.getRow(1).getCell(1).getNumericCellValue();
			subject_loop = subject_loop*2;
			
			for (int i = 2; i <= subject_loop; i=i+2)
			{
			
				v_new_subject_icon 	= driver.findElement(By.xpath("//*[@title='New subject']"));
				v_new_subject_icon.click();
				
				v_subject_name 		= driver.findElement(By.xpath("//input[@id='subjectname']"));
				UtilityClass.waitfor(10, By.xpath("//input[@id='subjectname']"));
				
				
				
			
				v_subject_name.sendKeys(sheet.getRow(3).getCell(i).getStringCellValue());
				
				
				v_subject_group = new Select(driver.findElement(By.xpath("//select[@id='subjectgroup_id']")));
				v_subject_group.selectByVisibleText(sheet.getRow(4).getCell(i).getStringCellValue());
				
				
				//v_subject_country = new Select(driver.findElement(By.xpath("")));
				
				v_subject_gcp = driver.findElement(By.xpath("//input[@class='certtitle subjectpropertyvalue  numeric']"));
				v_subject_gcp.sendKeys(String.valueOf(sheet.getRow(10).getCell(i).getNumericCellValue()));
				
				v_save_subject = driver.findElement(By.xpath("//*[@id='savesubjectbutton']"));
				v_save_subject.click();
				
				Thread.sleep(3000);
				
				driver.findElement(By.xpath("/html/body/div[11]/div[3]/div[1]/button[2]")).click();
				
				new WebDriverWait(driver, 10).until(
			            ExpectedConditions.visibilityOfElementLocated(
			                By.xpath("/html/body/div[11]/div[3]/div[1]/button[2]/span"))).click();
				Thread.sleep(10000);
				driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']/button")).click();
				Thread.sleep(10000);							
				WebElement table = driver.findElement(By.xpath("//table[@id='subjects_list_table']"));
				
				newsubject = table.findElements(By.xpath(".//tr")).get(1).findElements(By.xpath(".//td")).get(1).getText();
				System.out.println(newsubject);
				WebDriverWait subjectwait = new WebDriverWait(driver, 10);
				subjectwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='subjects_list_table']/tbody/tr[1]/td[8]/a[@title='Activate subject']"))).click();
				subjectwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[11]/div[3]/div[1]/button[2]/span"))).click();
				Thread.sleep(4000);
				
			}
				
			
			
		}
		catch(Exception e)
				
		{
				e.printStackTrace();
		}
				
		if (newsubject.equalsIgnoreCase(sheet.getRow(3).getCell(1).getStringCellValue()))
		{
				System.out.println("Subject added successfully: "+ newsubject);
				count++;
		}
		else
		{
				System.out.println("Failed to add subject: "+sheet.getRow(3).getCell(1).getStringCellValue());
		}
			
			
			
		return true;
		
		
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
