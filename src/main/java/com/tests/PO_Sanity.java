package com.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.opencsv.CSVWriter;

public class PO_Sanity 
{
	static String Name_for_PO_n_Lot= "JenkinsPOChan10";
	
	//.
	static WebElement table;
	static WebDriver driver;
	static String ANSI_RESET;
	static String ANSI_RED_BACKGROUND;
	
	
	static WebElement poname;
	static Select snp;
	static Select Product;
	static WebElement lotname;
	static WebElement productiondate;
	static WebElement mfgdate;
	static WebElement expirydate;
	static Select regulation;
	static Select line;
	
	static WebDriverWait wait;
	
	
	
	
	
	
	
	
	
	
	@Test(priority=1)	
	public static void initBrowser()
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		//ChromeDriver driver = new ChromeDriver(options);
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Chandrakant.W\\eclipse-workspace\\omen\\src\\Driver Servers\\chromedriver.exe");
		driver = new ChromeDriver(options);
		
		
		
		ANSI_RESET = "\u001B[0m";
		ANSI_RED_BACKGROUND = "\u001B[41m";
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@Test(priority=2)
	public static void openBrowser()
	{
		driver.get("https://172.16.70.125/login/?redir=index");
		
		driver.manage().window().maximize();
	
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
	}	
		//PrintStream o = new PrintStream(new File("C:\\Users\\Chandrakant.W\\Downloads\\Log.txt"));
		//System.setOut(o);
	
	
	
		
		//Test 1: Verify Login functionality
	@Test(priority=3)
	public static void verifyLogin()
	{
		System.out.println();
		System.out.println("Test 1: Verify Login functionality------------------------------------------------------------");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("AutoUser");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("tnt1234");
		driver.findElement(By.xpath("//*[@id=\"loginhoder\"]/input")).click();
		String user = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div[1]")).getText();
		if (user.contains("Selenium Automation"))
			System.out.println("Test passed:"+user+"logged in");
		else
			System.out.println(ANSI_RED_BACKGROUND+"Test failed"+ANSI_RESET);
		//Thread.sleep(6000);
	
		//Test 2: To verify Application name and version is displayed properly
		System.out.println();
		System.out.println("Test 2: To verify Application name and version is displayed properly--------------------------");
		String title = driver.getTitle();
		String expectedTitle = "VeriShield SM300 v.1.2.10.45";
		if(title.equalsIgnoreCase(expectedTitle))
		{
			System.out.println("Test passed: Application Name & version verified: "+ title+"\t Application name & version validated");
		}
		else 
		{
			System.out.println(ANSI_RED_BACKGROUND+"Test failed: Application name version not as expected: wrong name-"+title+ANSI_RESET);
		}
		
	}
	
	
	@Test(priority=4)
	public static void CreatePO() throws InterruptedException
	{
		
		System.out.println("Test 3: To Create a 3 Stn PO-----------------------------------------------------------");

		
		driver.findElement(By.xpath("//a[contains(text(),'Production orders')]")).click();
		
		driver.findElement(By.xpath("//i[@title='New production order']")).click();
		
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='po_name']")));
		
		poname = driver.findElement(By.xpath("//input[@id='po_name']"));
		poname.sendKeys(Name_for_PO_n_Lot);
		
		snp = new Select (driver.findElement(By.xpath("//select[@id='production_provider_id']")));
		snp.selectByVisibleText("VeriShield");
		
		driver.findElement(By.xpath("//*[@id='select2-packaging_product_id-container']/parent::span")).click();
		driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys("Jenkins_Product"+Keys.ENTER);
		
		Select packagingversion = new Select (driver.findElement(By.xpath("//select[@id='packaging_product_version_id']")));
		packagingversion.selectByValue("1");
		
		lotname = driver.findElement(By.xpath("//input[@id='production_lot']"));
		lotname.sendKeys(Name_for_PO_n_Lot);
		
		productiondate = driver.findElement(By.xpath("//input[@id='productiondate']"));
		productiondate.sendKeys("01.07.2024");
		
		mfgdate = driver.findElement(By.xpath("//input[@id='manufacturedate']"));
		mfgdate.sendKeys("07.04.2024");
		
		expirydate = driver.findElement(By.xpath("//input[@id='expirationdate']"));
		expirydate.sendKeys("31.12.2028");
		
		
		regulation = new Select (driver.findElement(By.xpath("//select[@id='po_standard_id']")));
		regulation.selectByVisibleText("US HDA 2017");
		
		driver.findElement(By.xpath("//span[@id='select2-location_id-container']/parent::span")).click();
		driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys("Jenkins_Location"+Keys.ENTER);
		
		line = new Select (driver.findElement(By.xpath("//select[@id='location_line_id']")));
		line.selectByValue("601");
		
		WebElement Source1 = driver.findElement(By.xpath("//div[@id='quantityform_packages_container']/div[1]"));
		WebElement Source2 = driver.findElement(By.xpath("//div[@id='quantityform_packages_container']/div[2]"));
		WebElement Source3 = driver.findElement(By.xpath("//div[@id='quantityform_packages_container']/div[3]"));
		
		WebElement destination1 = driver.findElement(By.xpath("//*[@id='prodorder_form']/div/div[3]/div[1]/div[2]"));
		WebElement destination2 = driver.findElement(By.xpath("//*[@id='prodorder_form']/div/div[3]/div[2]/div[2]"));
		WebElement destination3 = driver.findElement(By.xpath("//*[@id='prodorder_form']/div/div[3]/div[3]/div[2]"));
		
		
		Actions a = new Actions(driver);
		Actions b = new Actions(driver); 
		Actions c = new Actions(driver);
		
		//Thread.sleep(5000);
        
      //to perform Scroll on application using Selenium
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        
        //Thread.sleep(2000);

        a.dragAndDrop(Source1, destination1).build().perform();
        b.dragAndDrop(Source2, destination2).build().perform();
        c.dragAndDrop(Source3, destination3).build().perform();
        a.dragAndDrop(Source1, destination1).build().perform();
        
        driver.findElement(By.xpath("//*[@id='prodorder_form']/div/div[3]/div[1]/table/tbody/tr[2]/td[2]/input")).sendKeys(String.valueOf(200));
	
		Select prn2 = new Select (driver.findElement(By.xpath("//*[@id='prodorder_form']/div/div[3]/div[2]/table/tbody/tr[1]/td[2]/select")));
		prn2.selectByIndex(1);
		
		Select loosepackprn2 = new Select (driver.findElement(By.xpath("//*[@id='prodorder_form']/div/div[3]/div[2]/table/tbody/tr[3]/td[2]/select")));
		loosepackprn2.selectByIndex(1);
		
		Select internalloosepackprn2 = new Select (driver.findElement(By.xpath("//*[@id='prodorder_form']/div/div[3]/div[2]/table/tbody/tr[4]/td[2]/select")));
		internalloosepackprn2.selectByIndex(1);
		
		Select subject2 = new Select (driver.findElement(By.xpath("//*[@id='prodorder_form']/div/div[3]/div[2]/table/tbody/tr[6]/td[2]/select")));
		subject2.selectByVisibleText("VITA");
		
		Select prn3 = new Select (driver.findElement(By.xpath("//*[@id='prodorder_form']/div/div[3]/div[3]/table/tbody/tr[1]/td[2]/select")));
		prn3.selectByIndex(1);
		
		Select subject3 = new Select (driver.findElement(By.xpath("//*[@id='prodorder_form']/div/div[3]/div[3]/table/tbody/tr[4]/td[2]/select")));
		subject3.selectByVisibleText("VITA");
		

		driver.findElement(By.xpath("//*[@id='saveproductionbutton']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html/body/div[11]/div[3]/div[1]/button[2]/span")).click();
		
		System.out.println("============Po created succesfully=============");

		
	}
	
	
	@Test(priority=5)
	public static void runBatch() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='productionorders_list_table']")));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//table[@id='productionorders_list_table']/tbody/tr[1]/td[11]/a[@title='Production order overview']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='sendtoproductionbutton']"))).click();
		
		driver.findElement(By.xpath("/html/body/div[11]/div[3]/div[1]/button[2]/span")).click();
		
		Thread.sleep(5000);
		
		String poid = driver.findElement(By.xpath("//table[@id='productionorders_list_table']/tbody/tr[1]/td[1]")).getAttribute("innerHTML");;
		System.out.println("Po id is: "+poid);
		
		updatePoidInCSV(poid);
		
		clearUploadsFolder();
		
			
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void runJMXFile()
	{
		try {
		    Process process = Runtime.getRuntime().exec("cmd /c  \"D:\\apache-jmeter-5.5 (1)\\apache-jmeter-5.5\\bin\\jenkinsJmeter.bat\".bat");
		    // Note: With the "start" command, a separate command window will open.
		    // If you want to read the output from the batch file, remove "start" and just use "cmd /c yourbatchfile.bat"
		    int exitStatus = process.waitFor();
		    // Handle the exit status as needed
		} catch (IOException | InterruptedException e) {
		    e.printStackTrace();
		}

	}
	
	
	
	
	
	public static void updatePoidInCSV(String poooid)
	{
		
		System.out.println("======================= updating POID in IP.csv file ==========================");
		String pooid = poooid;
		// first create file object for file placed at location 
	    // specified by filepath 
	    File file = new File("D:\\apache-jmeter-5.5 (1)\\Projects\\Client-server-single-station\\csv files\\IP.csv"); 
	  
	    try { 
	        // create FileWriter object with file as parameter 
	        FileWriter outputfile = new FileWriter(file); 
	  
	        // create CSVWriter object filewriter object as parameter 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        String arr1[]=new String[] { "ip","poid","teach","accepted","inprocess","rejected","damaged"
	        		,"specimen","sample","child","xml","ip2","regulatory","ip3","child2","xml2" };
	        String arr2[] = new String[] { "192.168.10.1",pooid,"2","160","2","2","2","2","2","4","250",
	        		"192.168.10.2","HDA","192.168.10.3","2","100" };
	        writer.writeNext(arr1, false);
	        writer.writeNext(arr2, false);
	  
	        // closing writer connection 
	        writer.close(); 
	        
	        System.out.println("IP.csv file updated successfully");
	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block
	    	System.out.println("Failed to update IP.csv");
	        e.printStackTrace(); 
	    } 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void clearUploadsFolder() 
	{
		try 
		{
			System.out.println("================= Clearing the uploads folder ================================");
		
			String folderPath = "D:\\apache-jmeter-5.5 (1)\\Projects\\Client-server-single-station\\uploads"; // Replace with the folder you want
			File folder = new File(folderPath);
	
			if (folder.exists() && folder.isDirectory()) 
			{
				File[] files = folder.listFiles();
				if (files != null) 
				{
					for (File file : files) 
					{
						if (file.isFile()) 
						{
							file.delete();
						}
					}
				}
			}
			
			System.out.println("upload folder is cleared successfully");
		}
		catch(Exception e)
		{
			System.out.println("Failed to clear Uploads folder");
			e.printStackTrace();
		}
	}

}
