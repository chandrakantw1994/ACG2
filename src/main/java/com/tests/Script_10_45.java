package com.tests;





import java.time.Duration;
import java.util.List;
import java.util.Vector;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Script_10_45
{ 
	
	
		//.
		static WebElement table;
		static WebDriver driver;
		static String ANSI_RESET;
		static String ANSI_RED_BACKGROUND;
		static int random;
		static WebDriverWait subjectwait;
		static String gcp;
		static String newsubject;
		static String GLN_for_subject;
		
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
			driver.get("https://172.16.70.116/login/?redir=index");
			
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
		
			/*
			List<WebElement> Alllinks = driver.findElements(By.xpath("//a"));
			for (WebElement Alink : Alllinks) 
			{
				String urlz = Alink.getAttribute("href");
				Links.verifyLink(urlz);
			}
			*/
			
			//Test 3: To verify Dashboard page is displayed 
		@Test(priority = 4)
		public static void verifyDashboard()
		{
			System.out.println();
		
			System.out.println("Test 3: To verify Dashboard page is displayed ------------------------------------------");
			boolean Dashboard = driver.findElement(By.partialLinkText("Dashboard")).isDisplayed();
			if (Dashboard == true)
				System.out.println("Dashboard present");
			String ser = driver.findElement(By.xpath("//a[@href='/?link=serialization']")).getText();
			String mas = driver.findElement(By.xpath("//a[@href='/?link=masterdata']")).getText();
			String adm = driver.findElement(By.xpath("//a[@href='/?link=admins']")).getText();
		
			//System.out.println(ser+"\t"+mas+"\t"+adm);
			if(ser.equalsIgnoreCase("Serialization") && mas.equalsIgnoreCase("Master data") && adm.equalsIgnoreCase("Administration"))
			{
				System.out.println("Test passed: Dashboard page visible");
			}
			else
			{
				System.out.println(ANSI_RED_BACKGROUND+"Test Failed"+ANSI_RESET);
			}
		}
		
		@Test(priority=5)
		public static void verifyDashboardContent()
		{
			// Test 4: To validate the dashboard contents
			System.out.println();
			System.out.println("Test 4: To validate the dashboard contents----------------------------------------------");
			int count = 0, ccount=0;
			System.out.println("Sub-Test 4A: To verify All three module names are displayed or not =====================================");
			List<WebElement> ele = driver.findElements(By.xpath("//div[@class='menudiv']"));
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
				System.out.println("Sub-Test 4A: passed - Dashboard menu is proper"); 
				ccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"Sub-Test 4A: failed"+ANSI_RESET);

			
			System.out.println("Sub-Test 4B: To verify all required sub-tabs are available or not==================================");
			int count1 = 0;
			List<WebElement> ele2 = driver.findElements(By.xpath("//div[@class='datacontenttab']/a"));
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
				System.out.println("Sub-Test 4B: passed - Dashboard sub-tabs are proper");
				ccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"Sub-Test 4B: failed - Dashboard sub-tabs are not proper"+ANSI_RESET);

			
			
			System.out.println("Sub-Test 4C: To verify Local serial numbers pool, Central serial numbers pool,"
								+ " Tasks, Latest events panes are displayed or not ========================================");
			int count2 = 0;
			List<WebElement> ele3 = driver.findElements(By.xpath("//div[@class='head linearbackground']"));
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
				System.out.println("Sub-Test 4C: Passed - Dashboard headers are proper");
				ccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"Sub-Test 4C: Failed - Dashboard headers are not proper"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 4D: To verify Language icon is displayed or not=======================================================");
			
			if (driver.findElement(By.xpath("//div[@id ='languageactive']")).isDisplayed())
			{
				System.out.println("Sub-Test 4D: Passed");
				ccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"Sub-Test 4D: Failed"+ANSI_RESET);
			
			
			
			System.out.println("Sub-Test 4E: To verify Log-out icon is available or not========================================================");
			
			if (driver.findElement(By.xpath("//i[@class ='fa fa-sign-out']")).isDisplayed())
			{
				System.out.println("Sub-Test 4E: Passed");
				ccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"Sub-Test 4E: Failed"+ANSI_RESET);
			
			//Now 
			if (ccount == 5)
				System.out.println("Test 4: Passed");
			else
				System.out.println(ANSI_RED_BACKGROUND+"Test 4: Failed"+ANSI_RESET);
		}
		
		@Test(priority=6)
		public static void VerifyProductionOrdersPage()throws Exception
		{
			// Test 5: To verify Production orders tab is proper or not
			System.out.println();
			System.out.println("Test 5: To verify Production orders tab is proper or not-----------------------------------------------------------");
			driver.findElement(By.xpath("//a[contains(text(),'Production orders')]")).click();
			Thread.sleep(5000);
			int poccount = 0;

			System.out.println(
					"Sub-Test 5A: To verify search filter is available or not on PO window==============================================");
			
			if (driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]")).isDisplayed()) {
				System.out.println("Passed");
				poccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 5B: To verify that Page sorter is available on Po window=================================================");
			
			if (driver.findElement(By.xpath("//div[@id='productionorders_list_pages']")).isDisplayed()) {
				System.out.println("Passed");
				poccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 5C: To verify that Import from Excel icon is available on Po window======================================");
			
			if (driver.findElement(By.xpath("//i[@title='Import from Excel']")).isDisplayed()) {
				System.out.println("Passed");
				poccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 5D: To verify that New Production order icon is visible on PO window or not==============================");
			
			if (driver.findElement(By.xpath("//i[@title='New production order']")).isDisplayed()) {
				System.out.println("Passed");
				poccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 5E: To verify the table headers are proper on PO window or not=============================================");
			
			List<WebElement> poheaderlist = driver
					.findElements(By.xpath("/html/body/div[4]/div[2]/div[2]/div[4]/table[2]/thead/tr/th"));
			int phlcount = 0;
			for (WebElement phl : poheaderlist) {
				String sphl = phl.getAttribute("aria-label");
				// System.out.println(sphl);
				if (sphl.contains("Id") || 
					sphl.contains("PO order name / number") || 
					sphl.contains("Product") || 
					sphl.contains("Location") || 
					sphl.contains("Regulation") || 
					sphl.contains("Production date")|| 
					sphl.contains("Requested") || 
					sphl.contains("Produced") || 
					sphl.contains("Created") || 
					sphl.contains("Status") || 
					sphl.contains("Actions")
					) 
				{
					phlcount++;
					// System.out.println(phlcount);
				}
			}
			if (phlcount == 11) 
			{
				System.out.println("Passed");
				poccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

		}
		
		
		@Test(priority=7)
		public static void verifyReportingTab()
		{
		// Test 6: To verify that Reporting tab is proper or not
					System.out.println();
					System.out.println("Test 6: To verify that Reporting tab is proper or not-------------------------------------------------------------");
					driver.findElement(By.xpath("//a[contains(text(), 'Reporting')]")).click();
					int repcount=0;
					
					System.out.println("Sub-Test 6A: To verify Outgoing messages and Incoming messages tabs are available or not===========================");
					boolean om = driver.findElement(By.xpath("//a[contains(text(), 'Outgoing Messages')]")).isDisplayed();
					boolean in = driver.findElement(By.xpath("//a[contains(text(), 'Incoming Messages')]")).isDisplayed();
					
					if (om && in)
					{
						System.out.println("passed");
						repcount++;
					}
					else
						System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
					
					System.out.println("Sub-Test 6B: To verify MMMP filters are available or not==========================================================");
					if (driver.findElement(By.xpath("//div[@id='MMMPFilter']")).isDisplayed())
					{
						System.out.println("Passed");
						repcount++;
					}
					else
						System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
					
					
					System.out.println("Sub-Test 6C: To verify clear filters icon is available or not=====================================================");
					if (driver.findElement(By.xpath("//i[@title='Clear filters']")).isDisplayed())
					{
						System.out.println("Passed");
						repcount++;
					}
					else
						System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
					
					
					System.out.println("Sub-Test 6D: To verify Auto-refresh toggle icon is available or not================================================");
					if (driver.findElement(By.xpath("//i[@id='autorefreshicon']")).isDisplayed())
					{
						System.out.println("Passed");
						repcount++;
					}
					else
						System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
						
					
					System.out.println("Sub-Test 6E: To verify Messages table is displayed properly or not=================================================");
					boolean m = driver.findElement(By.xpath("//div[contains(text(),'Messages')]")).isDisplayed();
					
					List<WebElement> meslist = driver.findElements(By.xpath("//table[@id='emvslogs_messages_table']/thead/tr/th"));
					int mescount=0;
					for (WebElement mlist : meslist)
					{	
						String mheader = mlist.getText();
						//System.out.println(sphl);
						if (mheader.contains("Hub") ||
							mheader.contains("Message type") ||
							mheader.contains("Product") ||
							mheader.contains("Batch") ||
							mheader.contains("PO Number") ||
							mheader.contains("Status") ||
							mheader.contains("Date and time") ||
							mheader.contains("Actions")
							)
						{
							mescount++;
							//System.out.println(phlcount);
						}
					}
					if (mescount==8 && m)
					{
						System.out.println("Passed");		
						repcount++;
					}
					else
						System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);

		}
		
		
		@Test(priority=8)
		public static void verfiyStockPage() throws Exception
		{
			System.out.println();
			System.out.println(
					"Test 7: To verify Stock tab is proper or not-----------------------------------------------------------");
			driver.findElement(By.xpath("//a[contains(text(),'Stock')]")).click();
			Thread.sleep(2000);
			int stcount = 0;

			System.out.println(
					"Sub-Test 7A: To verify search filter is available or not on Stock window==============================================");
			if (driver.findElement(By.xpath("//*[@id='stock_list_pages']/following-sibling::div[1]")).isDisplayed()) {
				System.out.println("Passed");
				stcount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 7B: To verify that Page sorter is available on Stock window=================================================");
			if (driver.findElement(By.xpath("//div[@id='stock_list_pages']")).isDisplayed()) {
				System.out.println("Passed");
				stcount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 7C: To verify that Reload icon is available on Stock window======================================");
			if (driver.findElement(By.xpath("//*[@id='stock_list_pages']/preceding-sibling::a/i")).isDisplayed()) {
				System.out.println("Passed");
				stcount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 7D: To verify the table headers are proper on Stock window or not=============================================");
			List<WebElement> stheaderlist = driver
					.findElements(By.xpath("//table[@id='stock_list_table-sticky']/thead/tr/th"));
			int sthcount = 0;
			for (WebElement sth : stheaderlist) {
				String stheader = sth.getAttribute("aria-label");
				// System.out.println(stheader);
				if (stheader.contains("Id") || stheader.contains("Product") || stheader.contains("Lot/Batch")
						|| stheader.contains("Standard") || stheader.contains("Expiration date")
						|| stheader.contains("Produced") || stheader.contains("Reserved")
						|| stheader.contains("Shipped") || stheader.contains("Stock") || stheader.contains("Actions")) {
					sthcount++;
					// System.out.println(sthcount);
				}
			}
			if (sthcount == 10) {
				System.out.println("Passed");
				stcount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);
		}
		
		
		@Test(priority=9)
		public static void verifyShippingOrdersPage() throws Exception
		{
			System.out.println();
			System.out.println(
					"Test 8: To verify Shipping orders tab is proper or not-----------------------------------------------------------");
			driver.findElement(By.xpath("//a[contains(text(),'Shipping orders')]")).click();
			Thread.sleep(2000);
			int soccount = 0;

			System.out.println(
					"Sub-Test 8A: To verify search filter is available or not on Shipping orders window==============================================");
			if (driver.findElement(By.xpath("//div[@id='shippingorders_list_pages']/following-sibling::div[1]"))
					.isDisplayed()) {
				System.out.println("Passed");
				soccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 8B: To verify that Page sorter is available on Shipping orders window=================================================");
			if (driver.findElement(By.xpath("//div[@id='shippingorders_list_pages']")).isDisplayed()) {
				System.out.println("Passed");
				soccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 8C: To verify that Reload icon is available on Shipping orders window======================================");
			if (driver.findElement(By.xpath("//div[@id='shippingorders_list_pages']/preceding-sibling::a[1]/i"))
					.isDisplayed()) {
				System.out.println("Passed");
				soccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 8D: To verify that Download consignment logs icon is available on Shipping orders window======================================");
			System.out.println("Not applicable");
			/*
			 * if (driver.findElement(By.xpath("//*[@title='Download Consignment Logs']")).
			 * isDisplayed()) { System.out.println("Passed"); soccount++; } else
			 * System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			 */

			System.out.println(
					"Sub-Test 8E: To verify that New Shipping order option icon is available on Shipping orders window======================================");
			if (driver.findElement(By.xpath("//*[@title='New shipping order']")).isDisplayed()) {
				System.out.println("Passed");
				soccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 8F: To verify the table headers are proper on Shipping orders window or not=============================================");
			List<WebElement> soheaderlist = driver
					.findElements(By.xpath("//table[@id='shippingorders_list_table-sticky']/thead/tr/th"));
			int sohcount = 0;
			for (WebElement soh : soheaderlist) {
				String soheader = soh.getAttribute("aria-label");
				// System.out.println(stheader);
				if (soheader.contains("Id") || soheader.contains("Name") || soheader.contains("Regulation")
						|| soheader.contains("Type") || soheader.contains("Source location")
						|| soheader.contains("Destination location") || soheader.contains("Date and time of shipment")
						|| soheader.contains("Qty.") || soheader.contains("Agg.") || soheader.contains("Created")
						|| soheader.contains("Status") || soheader.contains("Actions")) {
					sohcount++;
					// System.out.println(sthcount);
				}
			}
			if (sohcount == 12) {
				System.out.println("Passed");
				soccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);
		}
		
		@Test(priority=10)
		public static void verfiyCompanyDetailsPage() throws Exception
		{
			System.out.println();
			System.out.println(
					"-----------------------------*********************************************************************----------------------");
			System.out.println("Validation of Master Data module");
			driver.findElement(By.xpath("//a[@href='/?link=masterdata']")).click();
			Thread.sleep(2000);

			System.out.println();
			System.out.println(
					"Test 9: To verify Company details page----------------------------------------------------------------------------");

			System.out.println(
					"Sub-Test 9A: To verify that company name, address details, GCP and GLN fields are present or not============================");
			int cdccount = 0;

			WebElement Company_name = driver
					.findElement(By.xpath("//*[@id='editcompanyntinner']/div/table/tbody/tr/td/b"));
			WebElement Address = driver
					.findElement(By.xpath("//div[@class='upperform']/table/tbody/tr/td[contains(text(),'Address: ')]"));
			WebElement City = driver
					.findElement(By.xpath("//div[@class='upperform']/table/tbody/tr/td[contains(text(),'City: ')]"));
			WebElement Zip = driver
					.findElement(By.xpath("//div[@class='upperform']/table/tbody/tr/td[contains(text(),'Zip: ')]"));
			WebElement State = driver
					.findElement(By.xpath("//div[@class='upperform']/table/tbody/tr/td[contains(text(),'State ')]"));
			WebElement Country = driver
					.findElement(By.xpath("//div[@class='upperform']/table/tbody/tr/td[contains(text(),'Country: ')]"));
			WebElement GCP = driver.findElement(By.xpath("//*[@value='GS1 Company Prefix/GTIN Prefix']"));
			WebElement GLN = driver.findElement(By.xpath("//*[@value='GLN']"));

			if (Company_name.isDisplayed() && Address.isDisplayed() && City.isDisplayed() && Zip.isDisplayed()
					&& State.isDisplayed() && Country.isDisplayed() && GCP.isDisplayed() && Country.isDisplayed()) {
				System.out.println("Passed");
				cdccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "Failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 9B: To verify the sub-tabs in company details are displayed properly or not=============================");

			int cdstcount = 0;
			List<WebElement> cdst = driver.findElements(By.xpath("//*[@id=\"editcompanyntinner\"]/div[2]/ul/li/a"));

			for (WebElement cdste : cdst) {
				String cdsth = cdste.getText();
				// System.out.println(cdsth);
				if (cdsth.contains("Properties") || cdsth.contains("CRPT settings") || cdsth.contains("ERP settings")
						|| cdsth.contains("Trusted counterparties")) {
					cdstcount++;
				}
			}
			if (cdstcount == 4) {
				System.out.println("Passed");
				cdccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);
		}
		
		
		
		@Test(priority=11)
		public static void verifyProductsPage() throws Exception
		{
			System.out.println();
			System.out.println(
					"Test 10: To verify Products page----------------------------------------------------------------------------");
			driver.findElement(By.xpath("//a[contains(text(),'Products')]")).click();
			Thread.sleep(2000);
			int pccount = 0;

			System.out.println(
					"Sub-Test 10A: To verify search filter is available or not on Products page==============================================");
			if (driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]")).isDisplayed()) {
				System.out.println("Passed");
				pccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 10B: To verify that Page sorter is available or not on products page=================================================");
			if (driver.findElement(By.xpath("//*[@id='products_pages']")).isDisplayed()) {
				System.out.println("Passed");
				pccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 10C: To verify that Reload icon is available on products page===================================================");
			if (driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/a[2]/i")).isDisplayed()) {
				System.out.println("Passed");
				pccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 10D: To verify that import products option icon is available on products page======================================");
			if (driver.findElement(By.xpath("//*[@id='importproductsbutton']")).isDisplayed()) {
				System.out.println("Passed");
				pccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 10E: To verify that New product option icon is available on products page=========================================");
			if (driver.findElement(By.xpath("//*[@title='New product']")).isDisplayed()) {
				System.out.println("Passed");
				pccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);

			System.out.println(
					"Sub-Test 10F: To verify the table headers are proper on Products page or not=============================================");
			List<WebElement> pheaderlist = driver
					.findElements(By.xpath("//table[@id='products_table-sticky']/thead/tr/th"));
			int phcount = 0;
			for (WebElement ph : pheaderlist) {
				String pheader = ph.getAttribute("aria-label");
				// System.out.println(stheader);
				if (pheader.contains("ID") || pheader.contains("Product name") || pheader.contains("Manufacturer")
						|| pheader.contains("Group") || pheader.contains("Description") || pheader.contains("Created")
						|| pheader.contains("Status") || pheader.contains("Actions")) {
					phcount++;
					// System.out.println(sthcount);
				}
			}
			if (phcount == 8) {
				System.out.println("Passed");
				pccount++;
			} else
				System.out.println(ANSI_RED_BACKGROUND + "failed" + ANSI_RESET);
		}
		
		
		@Test(priority=12)
		public static void verifyProductGroupPage() throws Exception
		{
			System.out.println();
			System.out.println("Test 11: To verify Product groups page----------------------------------------------------------------------------");
			driver.findElement(By.xpath("//a[contains(text(),'Product groups')]")).click();
			Thread.sleep(2000);
			int pgccount=0;
			
			System.out.println("Sub-Test 11A: To verify search filter is available or not on Product groups page==============================================");
			if (driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[4]/div[2]")).isDisplayed())
			{
				System.out.println("Passed");
				pgccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 11B: To verify that Page sorter is available or not on product groups page=================================================");
			if (driver.findElement(By.xpath("//*[@id='product_groups_pages']")).isDisplayed())
			{
				System.out.println("Passed");
				pgccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
				
			
			System.out.println("Sub-Test 11C: To verify that Reload icon is available on product groups page===================================================");
			if (driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[4]/a[2]/i")).isDisplayed())
			{
				System.out.println("Passed");
				pgccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 11D: To verify the table headers are proper on Product groups page or not=============================================");
			List<WebElement> pgheaderlist = driver.findElements(By.xpath("//table[@id='product_groups_table']/thead/tr/th"));
			int pghcount=0;
			for (WebElement pgh : pgheaderlist)
			{	
				String pgheader = pgh.getAttribute("aria-label");
				//System.out.println(stheader);
				if (
						pgheader.contains("ID") ||
						pgheader.contains("Product group") ||
						pgheader.contains("Created") ||
						pgheader.contains("Actions")  
					)
				{
					pghcount++;
					//System.out.println(sthcount);
				}
			}
			if (pghcount==4)
			{
				System.out.println("Passed");		
				pgccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			
			System.out.println("Sub-Test 11E: To verify the New Product group creation option icon is available"
								+ " on Product groups page or not=============================================");
			if (driver.findElement(By.xpath("//*[@title='New product group']")).isDisplayed())
			{
				System.out.println("Passed");
				pgccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
		}
		
		
		@Test(priority=13)
		public static void verifySubjectsPage() throws Exception
		{
			System.out.println();
			System.out.println("Test 12: To verify Subjects page----------------------------------------------------------------------------");
			driver.findElement(By.xpath("//a[contains(text(),'Subjects')]")).click();
			Thread.sleep(2000);
			int subjccount=0;
			
			System.out.println("Sub-Test 12A: To verify search filter is available or not on Subjects page==============================================");
			if (driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[6]/div[2]")).isDisplayed())
			{
				System.out.println("Passed");
				subjccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 12B: To verify that Page sorter is available or not on Subjects page=================================================");
			if (driver.findElement(By.xpath("//*[@id='subjects_list_pages']")).isDisplayed())
			{
				System.out.println("Passed");
				subjccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
				
			
			System.out.println("Sub-Test 12C: To verify that Reload icon is available on Subjects page===================================================");
			if (driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[6]/a[2]/i")).isDisplayed())
			{
				System.out.println("Passed");
				subjccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 12D: To verify the table headers are proper on Subjects page or not=============================================");
			List<WebElement> subjheaderlist = driver.findElements(By.xpath("//table[@id='subjects_list_table-sticky']/thead/tr/th"));
			int subjhcount=0;
			for (WebElement subjh : subjheaderlist)
			{	
				String subjheader = subjh.getAttribute("aria-label");
				//System.out.println(stheader);
				if (
						subjheader.contains("ID") ||
						subjheader.contains("Name") ||
						subjheader.contains("GS1 company prefix") ||
						subjheader.contains("Group") ||
						subjheader.contains("Description") ||
						subjheader.contains("Created") ||
						subjheader.contains("Status") ||
						subjheader.contains("Actions") 
					)
				{
					subjhcount++;
					//System.out.println(sthcount);
				}
			}
			if (subjhcount==8)
			{
				System.out.println("Passed");		
				subjccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 12E: To verify New Subject creation option icon is available on Subjects page or not=============================================");
			if (driver.findElement(By.xpath("//*[@title='New subject']")).isDisplayed())
			{
				System.out.println("Passed");
				subjccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
		}
			
			
		
			
		@Test(priority=14)
		public static void verifyLocationsPage() throws Exception
		{
									
			System.out.println();
			System.out.println("Test 13: To verify Locations page----------------------------------------------------------------------------");
			driver.findElement(By.xpath("//a[contains(text(),'Locations')]")).click();
			Thread.sleep(2000);
			int Locccount=0;
			
			System.out.println("Sub-Test 13A: To verify search filter is available or not on Locations page==============================================");
			if (driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[8]/div[2]")).isDisplayed())
			{
				System.out.println("Passed");
				Locccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 13B: To verify that Page sorter is available or not on Locations page=================================================");
			if (driver.findElement(By.xpath("//*[@id='locations_list_pages']")).isDisplayed())
			{
				System.out.println("Passed");
				Locccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
				
			
			System.out.println("Sub-Test 13C: To verify that Reload icon is available on Locations page===================================================");
			if (driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[8]/a[2]/i")).isDisplayed())
			{
				System.out.println("Passed");
				Locccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 13D: To verify the table headers are proper on Locations page or not=============================================");
			List<WebElement> locheaderlist = driver.findElements(By.xpath("//table[@id='locations_list_table']/thead/tr/th"));
			int lochcount=0;
			for (WebElement loch : locheaderlist)
			{	
				String locheader = loch.getAttribute("aria-label");
				//System.out.println(locheader);
				if (
						locheader.contains("ID") ||
						locheader.contains("Name") ||
						locheader.contains("Subject") ||
						locheader.contains("GLN") ||
						locheader.contains("Description") ||
						locheader.contains("Address") ||
						locheader.contains("City") ||
						locheader.contains("Country") ||
						locheader.contains("Created") ||
						locheader.contains("Actions")
					)
				{
					lochcount++;
					//System.out.println(sthcount);
				}
			}
			if (lochcount==10)
			{
				System.out.println("Passed");		
				Locccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 13E: To verify New Location creation option icon is available on Locations page or not=============================================");
			if (driver.findElement(By.xpath("//*[@title='New location']")).isDisplayed())
			{
				System.out.println("Passed");
				Locccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
		}
		
		
		@Test(priority=15)
		public static void verifyUsersTab()throws Exception
		{
			System.out.println();
			System.out.println("-----------------------------*********************************************************************----------------------");
			System.out.println("Validation of Administration module");
			driver.findElement(By.xpath("//a[@href='/?link=admins']")).click();
			Thread.sleep(2000);
			
			System.out.println();
			System.out.println("Test 14: To verify users tab---------------------------------------------------------------------------------------");
			int usersccount=0;
			
			System.out.println("Sub-Test 14A: To verify search filter is available or not on users page==============================================");
			if (driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]")).isDisplayed())
			{
				System.out.println("Passed");
				usersccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 14B: To verify that Page sorter is available or not on users page=================================================");
			if (driver.findElement(By.xpath("//*[@id='admins_list_pages']")).isDisplayed())
			{
				System.out.println("Passed");
				usersccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
				
			
			System.out.println("Sub-Test 14C: To verify that Synchronize with LDA icon is available on users page===================================================");
			if (driver.findElement(By.xpath("//*[@title='Synchronize with LDAP']")).isDisplayed())
			{
				System.out.println("Passed");
				usersccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 14D: To verify that Print option icon is available on users page===================================================");
			if (driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/a[1]/i")).isDisplayed())
			{
				System.out.println("Passed");
				usersccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 14E: To verify the table headers are proper on users page or not=============================================");
			List<WebElement> uheaderlist = driver.findElements(By.xpath("//table[@id='admins_list_table-sticky']/thead/tr/th"));
			int uhcount=0;
			for (WebElement uh : uheaderlist)
			{	
				String uheader = uh.getAttribute("aria-label");
				//System.out.println(uheader);
				if (
						uheader.contains("Lastname, Firstname") ||
						uheader.contains("Username") ||
						uheader.contains("E-mail") ||
						uheader.contains("Logged In") ||
						uheader.contains("Machine Name") ||
						uheader.contains("User Status") ||
						uheader.contains("Login Status") ||
						uheader.contains("Role") ||
						uheader.contains("Updated Date") ||
						uheader.contains("Creation Date") ||
						uheader.contains("Actions")
					)
				{
					uhcount++;
					//System.out.println(uhcount);
				}
			}
			if (uhcount==11)
			{
				System.out.println("Passed");		
				usersccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 14F: To verify New user creation option icon is available on users page or not=============================================");
			if (driver.findElement(By.xpath("//*[@title='New user']")).isDisplayed())
			{
				System.out.println("Passed");
				usersccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
		}
		
		
		@Test(priority= 16)
		public static void verifyRolesTab() throws Exception
		{
			System.out.println();
			System.out.println("Test 15: To verify Roles tab---------------------------------------------------------------------------------------");
			int roleccount=0;
			driver.findElement(By.xpath("//*[contains(text(),'Roles')]")).click();
			Thread.sleep(3000);
			
			System.out.println("Sub-Test 15A: To verify search filter is available or not on Roles page==============================================");
			if (driver.findElement(By.xpath("//*[@id='roles_list_pages']/following-sibling::div[1]")).isDisplayed())
			{
				System.out.println("Passed");
				roleccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 15B: To verify that Page sorter is available or not on Roles page=================================================");
			if (driver.findElement(By.xpath("//*[@id='admins_list_pages']")).isDisplayed())
			{
				System.out.println("Passed");
				roleccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			
			System.out.println("Sub-Test 15C: To verify the table headers are proper on Roles page or not=============================================");
			List<WebElement> roleheaderlist = driver.findElements(By.xpath("//table[@id='roles_list_table-sticky']/thead/tr/th"));
			int rolehcount=0;
			for (WebElement roleh : roleheaderlist)
			{	
				String roleheader = roleh.getAttribute("aria-label");
				//System.out.println(uheader);
				if (
						roleheader.contains("Role title") ||
						roleheader.contains("Actions")
					)
				{
					rolehcount++;
					//System.out.println(uhcount);
				}
			}
			if (rolehcount==2)
			{
				System.out.println("Passed");		
				roleccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 15D: To verify New Role creation option icon is available on Roles page or not=============================================");
			if (driver.findElement(By.xpath("//*[@title='New role']")).isDisplayed())
			{
				System.out.println("Passed");
				roleccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
		}
		
		
		@Test(priority=17)
		public static void verifyAuditTrailPage() throws Exception
		{
			System.out.println();
			System.out.println("Test 16: To verify Audit trail tab---------------------------------------------------------------------------------------");
			int atccount=0;
			driver.findElement(By.xpath("//*[contains(text(),'Audit trail')]")).click();
			Thread.sleep(4000);
			
			
			System.out.println("Sub-Test 16A: To verify that Page sorter is available or not on Audit trail page=================================================");
			if (driver.findElement(By.xpath("//*[@id='audits_list_pages']")).isDisplayed())
			{
				System.out.println("Passed");
				atccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			
			System.out.println("Sub-Test 16B: To verify the table headers are proper on Audit trail page or not=============================================");
			List<WebElement> atheaderlist = driver.findElements(By.xpath("//table[@id='audits_list_table']/thead/tr/th"));
			int athcount=0;
			for (WebElement ath : atheaderlist)
			{	
				String atheader = ath.getText();
				//System.out.println(atheader);
				if (
						atheader.contains("Date and time") ||
						atheader.contains("Event") ||
						atheader.contains("User") ||
						atheader.contains("Message") ||
						atheader.contains("Details") 
					)
				{
					athcount++;
					//System.out.println(athcount);
				}
			}
			if (athcount==5)
			{
				System.out.println("Passed");		
				atccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 16C: To verify Export to Excel option icon is available on Audit trail page or not=============================================");
			if (driver.findElement(By.xpath("//*[@title='Export to Excel']")).isDisplayed())
			{
				System.out.println("Passed");
				atccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			System.out.println("Sub-Test 16D: To verify Reload icon is available on Audit trail page or not=============================================");
			if (driver.findElement(By.xpath("//*[@id='audits_list_pages']/preceding-sibling::a[1]/i")).isDisplayed())
			{
				System.out.println("Passed");
				atccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			System.out.println("Sub-Test 16E: To verify Print icon is available on Audit trail page or not=============================================");
			if (driver.findElement(By.xpath("//*[@id='audits_list_pages']/preceding-sibling::a[3]/i")).isDisplayed())
			{
				System.out.println("Passed");
				atccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
		}
		
		
		@Test(priority=18)
		public static void ProviderPage() throws Exception
		{

			System.out.println();
			System.out.println("Test 17: To verify Serial Number providers tab---------------------------------------------------------------------------------------");
			int snpccount=0;
			driver.findElement(By.xpath("//a[contains(text(),'Serial numbers providers')]")).click();
			Thread.sleep(3000);
			
			
			System.out.println("Sub-Test 17A: To verify that Page sorter is available or not on Serial Number providers page=================================================");
			if (driver.findElement(By.xpath("//*[@id='sngproviders_list_pages']")).isDisplayed())
			{
				System.out.println("Passed");
				snpccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			
			System.out.println("Sub-Test 17B: To verify the table headers are proper on Serial Number providers page or not=============================================");
			List<WebElement> snpheaderlist = driver.findElements(By.xpath("//table[@id='sngproviders_list_table-sticky']/thead/tr/th"));
			int snphcount=0;
			for (WebElement snph : snpheaderlist)
			{	
				String snpheader = snph.getAttribute("aria-label");
				//System.out.println(atheader);
				if (
						snpheader.contains("Title") ||
						snpheader.contains("Actions") 
					)
				{
					snphcount++;
					//System.out.println(athcount);
				}
			}
			if (snphcount==11)
			{
				System.out.println("Passed");		
				snpccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 17C: To verify Search option is available on Serial Number providers page or not=============================================");
			if (driver.findElement(By.xpath("//div[@id='sngproviders_list_pages']/following-sibling::div[1]")).isDisplayed())
			{
				System.out.println("Passed");
				snpccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			System.out.println("Sub-Test 17D: To verify ADD provider icon is available on Serial Number providers page or not=============================================");
			if (driver.findElement(By.xpath("//div[@id='sngproviders_list_pages']/following-sibling::div[2]")).isDisplayed())
			{
				System.out.println("Passed");
				snpccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			System.out.println("Sub-Test 17E: To verify Cron Job status icon is available on Serial Number providers page or not=============================================");
			if (driver.findElement(By.xpath("//i[@id='cronjobicon']")).isDisplayed())
			{
				System.out.println("Passed");
				snpccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
		}
		
		
		@Test(priority=19)
		public static void verifyCMOIntegrationTab() throws Exception
		{
			System.out.println();
			System.out.println("Test 18: To verify CMO Integration tab---------------------------------------------------------------------------------------");
			int ciccount=0;
			driver.findElement(By.xpath("//a[contains(text(),'CMO Integration')]")).click();
			Thread.sleep(3000);
			
			
			System.out.println("Sub-Test 18A: To verify that Page sorter is available or not on CMO Integration page=================================================");
			if (driver.findElement(By.xpath("//*[@id='cmointegration_list_pages']")).isDisplayed())
			{
				System.out.println("Passed");
				ciccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			
			System.out.println("Sub-Test 18B: To verify the table headers are proper on CMO Integration page or not=============================================");
			List<WebElement> ciheaderlist = driver.findElements(By.xpath("//table[@id='cmointegration_list_table']/thead/tr/th"));
			int cihcount=0;
			for (WebElement cih : ciheaderlist)
			{	
				String ciheader = cih.getAttribute("aria-label");
				//System.out.println(atheader);
				if (
						ciheader.contains("Title") ||
						ciheader.contains("Actions")  
					)
				{
					cihcount++;
					//System.out.println(athcount);
				}
			}
			if (cihcount==2)
			{
				System.out.println("Passed");		
				ciccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			
			System.out.println("Sub-Test 18C: To verify Search option is available on CMO Integration page or not=============================================");
			if (driver.findElement(By.xpath("//div[@id='cmointegration_list_pages']/following-sibling::div[1]")).isDisplayed())
			{
				System.out.println("Passed");
				ciccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			System.out.println("Sub-Test 18D: To verify Reload icon is available on CMO Integration page or not=============================================");
			if (driver.findElement(By.xpath("//div[@id='cmointegration_list_pages']/preceding-sibling::a[1]/i")).isDisplayed())
			{
				System.out.println("Passed");
				ciccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
			
			System.out.println("Sub-Test 18E: To verify New FTP Destination option icon is available on CMO Integration page or not=============================================");
			if (driver.findElement(By.xpath("//div[@id='cmointegration_list_pages']/preceding-sibling::a[2]/i")).isDisplayed())
			{
				System.out.println("Passed");
				ciccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
		}
		
		
		
		
		@Test(priority=20)
		public static void verifySettingsPage() throws InterruptedException
		{
			System.out.println();
			System.out.println("Test 19: To verify Settings tab---------------------------------------------------------------------------------------");
			int sccount=0;
			driver.findElement(By.xpath("//a[contains(text(),'Settings')]")).click();
			//takeSnapShot(driver, "C:\\Users\\Chandrakant.W\\Documents\\TakeSnapShotsSelenium\\SettingsPage.png");
			
			Thread.sleep(3000);
			
			
			System.out.println("Sub-Test 19A: To verify that all required settings are available or not on Settings page=================================================");
			String S[] = { 
					
							"maximum allowed quantity for production order item", "session timeout in minutes",
							"allow login from multiple machines (0 - No, 1 - Yes)", "siteID (0-SNG, 1,2,3... site number)",
							"API_URL", "CLIENT_URL", "APP_URL", "CERT_URL", "PIC_URL", "ROOT", "CLIENTROOT", "APIROOT",
							"Minimum password length", "Lockout attempts", "Password expiry", "Password complexity",
							"Username type", "password_expiry_notification_days",
							"External serial numbers providers minimum_quantity (max value)",
							"External serial numbers providers renewal_quantity (max value)",
							"GTIN is unique across products (0 - No, 1 - Yes)",
							"Allow same product name across products (0 - No, 1 - Yes)",
							"Maximum serial numbers download attempts", "Serial numbers download interval in minutes",
							"use quarantine status for production order (0 - No, 1 - Yes)",
							"Allow same lot number for different products (0 - No, 1 - Yes)",
							"Is Manufacturing Date mandatory? (0 - no,1 - yes)", "Verishield Proxy Server URL",
							"Proxy Server Alias Id", "Date Formats", "Product fetch from FG Code(SAP)(0 - No,1 -Yes)",
							"VRS add email list max count", "Validate password for last used? 0-N0, 1-Yes",
							"Provision to download the consignment XML", "Automatically Download Reporting Message?",
							"Export audittrial to excel? (0 - no,1 - yes)",
							"Is serial numbers to be discarded? (0-> No, 1-> Yes)",
							"Allow users to run Multiple Production Order on same machine ? (0-> Yes, 1-> No)",
							"Allow user to select past date for production date in production order? (0-> Yes, 1-> No)",
							"Display option to select request/response format (PURE URI ID) for Rfxcel (1=>Display, 0=>Hide)",
							"Allow a production order to be internally transferred (1=>YES,0=>NO)",
							"Allow sending ASN 856 Message File (1=>YES, 0=>NO)",
							"Display option to select SFTP based request/response format for tracelink (1=>Display, 0=>Hide)",
							"Enable Force Password Change (1=>YES, 0=>NO)", "Enable contact details in subject (1=>YES,0=>NO)",
							"Enable Default Filter Value (1=>YES, 0=>NO)", "Enable Default subject Value (1=>YES, 0=>NO)" 
							
						};

			int count_of_setting = 0;

			driver.findElement(By.xpath("//*[@id=\"datacontenttab_7\"]/a")).click();
			Thread.sleep(2000);

			List<WebElement> list_of_settings = driver
					.findElements(By.xpath("//div[@id='datacontentinner_tab_7']/div/table/tbody/tr/td[1]"));

			for (int loop1 = 0; loop1 < 47; loop1++) {
				// System.out.println("Loop to check:
				// "+S[loop1]+"--------------------------------");
				for (WebElement we4 : list_of_settings) {
					String set1 = we4.getText();
					// System.out.println(set1);
					if (S[loop1].equals(set1)) {
						count_of_setting++;
					}
				}
				// System.out.println(count_of_setting);
			}
			if (count_of_setting == 47)
			{
				System.out.println("Passed");
				//System.out.println("All settings are present and valid");
				sccount++;
			}
			else
				System.out.println(ANSI_RED_BACKGROUND+"failed"+ANSI_RESET);
		}
		
		
		
		
		
		@Test(priority=21)
		public static void verifyAddingSubject() throws Exception
		{
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			System.out.println("==========================================Functional testing starts here==========================================");
			System.out.println();
			System.out.println("Test 20: verify adding subject");
			//Test 3: verify adding subject 
			driver.findElement(By.xpath("//a[@href='/?link=masterdata']")).click();
			//Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@href='javascript:subjectsList(1)']")).click();
			
			String earlier_gcp = driver.findElement(By.xpath("//table[@id='subjects_list_table']/tbody/tr/td[3]")).getText();
			int gcpn = Integer.parseInt(earlier_gcp)+1;
			gcp = String.valueOf(gcpn);
			GLN_for_subject = getGLN(gcp);
			
			//Thread.sleep(5000);
			//driver.findElement(By.xpath("//*[@id='datacontentinner_tab_4']/a[1]/i")).click();
			driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[6]/a[1]/i")).click();
			//Thread.sleep(5000);
			random = (int) (100*Math.random());
			
			driver.findElement(By.xpath("//input[@id='subjectname']")).sendKeys("Test_Subject_"+random);
			Select sub_grp = new Select(driver.findElement(By.xpath("//select[@id='subjectgroup_id']")));
			sub_grp.selectByVisibleText("Manufacturer");
			//Thread.sleep(3000);
			//driver.findElement(By.xpath("//input[@id='property_24']")).click();
			//Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@class='certtitle subjectpropertyvalue  numeric']")).sendKeys(String.valueOf(gcp));
			//Thread.sleep(3000);
			//driver.findElement(By.xpath("//input[@id='property_28']")).click();
			//Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@id='property_28' and @class='certtitle subjectpropertyvalue ']")).sendKeys(String.valueOf(GLN_for_subject));
			//Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='savesubjectbutton']")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("/html/body/div[11]/div[3]/div[1]/button[2]")).click();
			
			new WebDriverWait(driver, 10).until(
		            ExpectedConditions.visibilityOfElementLocated(
		                By.xpath("/html/body/div[11]/div[3]/div[1]/button[2]/span"))).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']/button")).click();
			Thread.sleep(10000);							
			table = driver.findElement(By.xpath("//table[@id='subjects_list_table']"));
			
			newsubject = table.findElements(By.xpath(".//tr")).get(1).findElements(By.xpath(".//td")).get(1).getText();
			System.out.println(newsubject);
			subjectwait = new WebDriverWait(driver, 10);
			subjectwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='subjects_list_table']/tbody/tr[1]/td[8]/a[@title='Activate subject']"))).click();
			subjectwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[11]/div[3]/div[1]/button[2]/span"))).click();
			
			
			if (newsubject.equalsIgnoreCase("Test_Subject_"+random))
			{
				System.out.println("Test 20: Passed - Subject Added succesfully");
			}
			else 
			{
				System.out.println(ANSI_RED_BACKGROUND+"Test 20: Failed - Failed to add Subject"+ANSI_RESET);
			}
		}
		
		
		@Test(priority=22)
		public static void verifyAddingProduct() throws Exception
		{

			System.out.println();
			System.out.println("Test 21: Verify addition of product-------------------------------------------------");
			Thread.sleep(2000);
			// Test: Verify addition of product
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/?link=masterdata']"))).click();
			
			driver.findElement(By.xpath("//*[@id='datacontenttab_3']/a")).click();
			driver.findElement(By.xpath("//i[@title='New product']")).click();
			String productname="Test_subject_"+random;
			driver.findElement(By.xpath("//input[@id='product_name']")).sendKeys("Product_test_"+random);
			Select manufacturer = new Select(driver.findElement(By.xpath("//select[@id='manufacturer_id']")));
			manufacturer.selectByVisibleText(newsubject);
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
			
			driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[3]/td[6]/input")).sendKeys("12345");
			driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[5]/td[6]/input")).sendKeys("12345");
			driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[7]/td[6]/input")).sendKeys("12345");
			
			
			driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[3]/td[7]/input")).sendKeys(String.valueOf(1));
			
			driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[3]/td[8]/input")).sendKeys(String.valueOf(1));
			driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[5]/td[8]/input")).sendKeys(String.valueOf(4));
			driver.findElement(By.xpath("//*[@id=\"productpackaging_table\"]/tbody/tr[7]/td[8]/input")).sendKeys(String.valueOf(2));
			
			driver.findElement(By.xpath("//i[@id='saveproductbutton']")).click();
			
			driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']/button[2]")).click();
			Thread.sleep(10000);
			String product_name = driver.findElement(By.xpath("//table[@id='products_table']/tbody/tr[1]/td[2]")).getText();
			
			if (product_name.equalsIgnoreCase("Product_test_"+random))
				System.out.println("Test 21: Product added succesfully: "+product_name);
			else
				System.out.println(ANSI_RED_BACKGROUND+"Test 21: Product addition failed"+ANSI_RESET);
		}
		
		@Test(priority=23)
		public static void verifyAddingLocation()throws Exception
		{
			System.out.println();
			System.out.println("Test 22: Verify adding of Location --------------------------------------------------");
			
			driver.findElement(By.xpath("//a[contains(text(),'Locations')]")).click();
			driver.findElement(By.xpath("//i[@title='New location']")).click();
			driver.findElement(By.id("location_name")).sendKeys("Test_Location_"+random);
			
			Select Location_owner= new Select(driver.findElement(By.id("location_subject_id")));
			Location_owner.selectByVisibleText(newsubject);
			
			Select country = new Select(driver.findElement(By.id("location_country")));
			country.selectByVisibleText("India");
			
			String GLN_for_Location = GLN_for_subject.replace(gcp, "");
							
			driver.findElement(By.xpath("//table[@id='locationproperties_table']/tbody/tr[6]/td[2]/input")).sendKeys(GLN_for_Location);
			
			
			driver.findElement(By.id("savelocationbutton")).click();
			
			subjectwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Save location')]/ancestor::div/div[3]/div/button/span[contains(text(),'OK')]"))).click();
			
			Thread.sleep(5000);
			
			String LocationName= driver.findElement(By.xpath("//table[@id='locations_list_table']/tbody/tr[1]/td[2]")).getText();
			
			if (LocationName.equals("Test_Location_"+random))
			{
				System.out.println("Test 22: Location added succesfully: "+LocationName);
			}
			else
			{
				System.out.println(ANSI_RED_BACKGROUND+"Test 22: Location addition failed"+ANSI_RESET);
			}
			Thread.sleep(5000);
		}
		
		
		
		@Test(priority=24)
		public static void verifyUserPermissions() throws Exception
		{
			System.out.println();
			System.out.println("Test : To verify all the required user permission are present and in proper sequence ----------------------------- ");
			driver.findElement(By.xpath("//a[@href='/?link=admins']")).click();
			driver.findElement(By.xpath("//a[contains(text(),'Roles')]")).click();
			driver.findElement(By.xpath("//i[@title='New role']")).click();
			driver.findElement(By.xpath("//a[contains(text(),'User permissions')]")).click();
			
		
			Vector<String> v1 =new Vector<String>();
			
			v1.add("Get production orders list");
			v1.add("Get production order's items");
			v1.add("Edit production order");
			v1.add("Batch Repack");
			v1.add("Delete production order");
			v1.add("Send production order to production");
			v1.add("Download production order codes");
			v1.add("Download aggregated production order codes");
			v1.add("Import production order serial numbers");
			v1.add("Aggregate production order codes");
			v1.add("Finalize production order");
			v1.add("Create shipping order from production order");
			v1.add("Get production order issues");
			v1.add("Edit production order issue");
			v1.add("Send production order issue to production");
			v1.add("Download production order issue codes");
			v1.add("Delete production order issue");
			v1.add("Reopen production order");
			v1.add("Get production orders codes aggregated");
			v1.add("Deaggregate production order codes");
			v1.add("Decommison production order codes");
			v1.add("Get production orders statuses");
			v1.add("Recall batch");
			v1.add("Damage batch");
			v1.add("Import production orders");
			v1.add("Production order calculator");
			v1.add("Prepare product pack data for EMVS");
			v1.add("Get stock list");
			v1.add("Get stock items");
			v1.add("Get shipping orders list");
			v1.add("Edit shipping order");
			v1.add("Delete shipping order");
			v1.add("Delete shipping order's item");
			v1.add("Shipping Orders aggregate codes");
			v1.add("Ship shipping order");
			v1.add("Get shipping orders types");
			v1.add("Get standards");
			v1.add("Download shipping order codes");
			v1.add("Process shipping order");
			v1.add("Cancel shipping order");
			v1.add("Download shipping order codes with excluded codes");
			v1.add("Get products list");
			v1.add("Save product");
			v1.add("Delete product");
			v1.add("Get product properites");
			v1.add("Get product units");
			v1.add("Get product stock");
			v1.add("Get products groups list");
			v1.add("Save products group");
			v1.add("Delete products group");
			v1.add("Get product packaging versions");
			v1.add("Delete product packaging version");
			v1.add("Activate product");
			v1.add("Get subjects list");
			v1.add("Edit subject");
			v1.add("Delete subject");
			v1.add("Get subject properites");
			v1.add("Get subject locations");
			v1.add("Get subjects groups list");
			v1.add("Activate subject");
			v1.add("Get locations");
			v1.add("Edit location");
			v1.add("Delete location");
			v1.add("Get location properties");
			v1.add("Get production lines");
			v1.add("Edit production line");
			v1.add("Delete production line");
			v1.add("Get location line properties");
			v1.add("Get line systems");
			v1.add("Get systems");
			v1.add("Edit system");
			v1.add("Get application identifiers");
			v1.add("Get special fields list");
			v1.add("Get Standards List");
			v1.add("Get serial numbers templates list");
			v1.add("Get serialization events");
			v1.add("Get serialization event XML");
			v1.add("Get latest events");
			v1.add("Get Regulatory PRN's/Delete PRN");
			v1.add("Get code statuses");
			v1.add("Get product info certificates list");
			v1.add("Get product info certificate details");
			v1.add("Insert product info");
			v1.add("Delete product info certificate");
			v1.add("Get product info users");
			v1.add("Get certificate users");
			v1.add("Download certificates codes");
			v1.add("Get certificates codes");
			v1.add("Get notifications list");
			v1.add("Get notification details");
			v1.add("Save notification");
			v1.add("Delete notification");
			v1.add("Notifications users");
			v1.add("Acivate notification");
			v1.add("List end users");
			v1.add("Get special mobile users");
			v1.add("Insert special user");
			v1.add("Delete mobile user");
			v1.add("Get mobile user certificates");
			v1.add("Reports - scans per month");
			v1.add("Reports - scans per country");
			v1.add("Reports - scans per gender");
			v1.add("Reports - scans per age");
			v1.add("Get events list");
			v1.add("Get users list");
			v1.add("Edit user");
			v1.add("Activate/Deactivate User.");
			v1.add("Get user roles types");
			v1.add("Get roles");
			v1.add("Edit role");
			v1.add("Delete role");
			v1.add("Get audits");
			v1.add("Get audit details");
			v1.add("Get settings");
			v1.add("Update settings");
			v1.add("Get general record statuses");
			v1.add("Take Backup");
			v1.add("Get machine permissions");
			v1.add("Get FTP destinations");
			v1.add("Get FTP destination details");
			v1.add("Save FTP destination");
			v1.add("Log FTP upload");
			v1.add("Save serial numbers provider settings");
			v1.add("Get external serials pools");
			v1.add("Edit external serials pool");
			v1.add("Delete external serials pool");
			v1.add("Get external serials pools for providers MAH");
			v1.add("Edit external serials pool for providers MAH");
			v1.add("Edit connection owner name - serial number provider");
			v1.add("Re-upload of rfXcel event files");
			v1.add("Edit digital signature settings");
			v1.add("View company details");
			v1.add("Edit company details");
			v1.add("Get feedbacks");
			v1.add("Get feedback templates");
			v1.add("Insert feedback");
			v1.add("cancelShippingOrderPartial");
			v1.add("Get code browser");
			v1.add("Code browser update");
			v1.add("Get certificate issues");
			v1.add("Get certificates types");
			v1.add("Get module log");
			v1.add("Get machine types");
			v1.add("Get serial numbers pools");
			v1.add("Edit serial numbers pool settings");
			v1.add("Change user language");
			v1.add("Reporting Data Staging");
			v1.add("sp_get_turon_crypto_order_details");
			v1.add("Response from EMVS for product pack data");
			v1.add("CMO - get serial numbers");
			v1.add("CMO - return production data");
			v1.add("Trusted Counter Party");
			v1.add("Request SSCC Information");
			v1.add("Contract Information");
			v1.add("Reporting");
			v1.add("VRS Configuration settings");
			v1.add("iVEDA Products Xml Download");
			v1.add("serial number providers dynamic client token updated");
			v1.add("serial number providers oms connection detail updated");
			v1.add("serial number providers oms connection detail inserted");
			v1.add("import_crpt_turon");
			v1.add("Import crypto code");
			v1.add("Save CMO Provider");
			v1.add("Edit CMO Integration Serials Pools");
			v1.add("Internal Transfer");
			v1.add("Generate ASN XML FILE");
			v1.add("Send ASN XML File");
			v1.add("Client-Get serial weblinks for production");
			v1.add("Download ShippingOrder Weblinks Codes");
			v1.add("import_crpt_nutra");
			v1.add("Archive");

			Vector v2 = new Vector();
			List <WebElement> user_permissions = driver.findElements(By.xpath("//table[@id='rolestable']/tbody/tr/td[1]"));
			
			for (WebElement up : user_permissions)
			{
				String upstr = up.getText();
				for(int j=0; j < v1.size(); j++)
				{
					if(upstr.equalsIgnoreCase(v1.elementAt(j)))
					{
						v2.add(upstr);
						break;
					}
				}
			}
			
			if (v1.equals(v2))
			{
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>All user permissions are OK");
			}
			else 
			{
				System.out.println("User permissions are not proper");
			}		
			
		}
		
		
		
		
		
		public static String b, e, GLNsuffix;
		
		
		
		
		public static String getGLN(String m)
		{
			String a = m;
			
			switch(a.length())
			{
			case 1:
				b="12345678912";
				break;
				
			case 2:
				b="1234567891";
				break;
			case 3:
				b="123456789";
				break;
			case 4:
				b="12345678";
				break;
			case 5:
				b="1234567";
				break;
			case 6:
				b="123456";
				break;
			case 7:
				b="12345";
				break;
			case 8:
				b="1234";
				break;
			case 9:
				b="123";
				break;
			case 10:
				b="12";
				break;
			case 11:
				b="1";
				break;
				
			}
			//b = "12345";
			String c = a+b;
			
			long d = Long.parseLong(c);
			long  n = d;
			int mk[] = new int[12];
			int i=0, a1=0, a2=0;
			while (n!=0)
			{
				mk[i]=(int) (n%10);
				i++;
				n=n/10;
			}
			for (int j =0; j<12; j++)
			{
				if (j==0 || j==2 || j==4 || j==6 || j==8 || j==10)
				{
					a1=a1+mk[j];
				}
				else
				{
					a2=a2+mk[j];
				}
			}
			
			int addi= a1*3+a2;
			System.out.println(addi);
			
			int prop = 0;
			prop = (addi/10)*10+10;
			
			int check_digit = prop - addi;
			if (check_digit==10)
				check_digit=0;
			
			System.out.println("check digit ="+check_digit);
			
			e = String.valueOf(check_digit);
			System.out.println(e);
			
			String GLN1= c+e;
			GLN1= GLN1.substring(0, 13);
			System.out.println(GLN1);
			GLNsuffix = b+e;
			
			
			
			return GLN1;
		}
}
			







