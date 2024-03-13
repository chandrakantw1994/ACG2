package com.tests;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.pages.DashboardPage;
import com.pages.LoginPage;
import com.pages.ProductionOrders;



public class TestCases extends LoginPage
{
	
	@Test(priority=1)
	public static void Verify_Login_Functionality()
	{
		launchBrowser();
		openURL("https://172.16.70.122/");
		performLogin("AutoUser", "tnt1234");
		boolean user = DashboardPage.verifyUser();
		Assert.assertEquals(user, true);
		Reporter.log("Login Successful");
	}
	
	
	@Test(priority=2)
	public static void Verify_Dashboard_Page_isDispalyed()
	{
		boolean v =DashboardPage.isDashboardDisplayed();
		Assert.assertEquals(true, v);
	}
	
	
	@Test(priority=3)
	public static void verify_Application_Name_And_Version()
	{
		String ExpectedTitile= "VeriShield SM300 v.1.2.10.45";
		boolean title = DashboardPage.getPageTitle(ExpectedTitile);
		Assert.assertEquals(title, true);
		Reporter.log("Title Validated"+ExpectedTitile);
	}
	
	
	@Test(priority=4)
	public static void verify_All_Three_Module_Names_Are_Displayed()
	{
		boolean abc = DashboardPage.checkModules();
		Assert.assertEquals(abc, true);
		Reporter.log("Serialization | Master Data | Administration");
		Reporter.log("All three modules are displayed");
	}
	
	
	@Test(priority=5)
	public static void Verify_Sub_Tabs_On_DashboardPage()
	{
		boolean abc = DashboardPage.checkTabs();
		Assert.assertEquals(abc, true);
		Reporter.log("All required tabs are present");
	}
	
	
	@Test(priority=6)
	public static void verify_Serial_pools_and_event_panes_are_displayed()
	{
		boolean abc = DashboardPage.checkFrames();
		Assert.assertEquals(abc, true);
		Reporter.log("Central Pool, Local Pool, Tasks and Latest events frames are presnt");
	}
	
	
	@Test(priority=7)
	public static void verify_search_filter_on_PO_page()
	{
		boolean abc = ProductionOrders.checkSearchFilter();
		Assert.assertEquals(abc, true);
		Reporter.log("Seach filter available on PO page");
	}
	
	
	@Test(priority=8)
	public static void verify_page_sorter_on_PO_page()
	{
		boolean abc = ProductionOrders.checkPageSoter();
		Assert.assertEquals(abc, true);
		Reporter.log("Page sorter available on PO page");
	}
	
	
	@Test(priority=10)
	public static void verify_import_from_excel_Icon()
	{
		boolean abc = ProductionOrders.checkExcelIcon();
		Assert.assertEquals(abc, true);
		Reporter.log("Import from Excel Icon available");
	}
	
	
	@Test(priority=11)
	public static void verify_New_Production_order_Icon()
	{
		boolean abc = ProductionOrders.checkNewProductionOrderIcon();
		Assert.assertEquals(abc, true);
		Reporter.log("New Production Order Icon available");
	}
	
	
	
}
