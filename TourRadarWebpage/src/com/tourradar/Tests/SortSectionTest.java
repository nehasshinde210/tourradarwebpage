package com.tourradar.Tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tourradar.PageObjects.SortSection;
import com.tourradar.Utilities.ReadWriteData;


public class SortSectionTest {
	private WebDriver driver;
	String driverPath = "C:\\Users\\NS00853858\\Documents\\Personal\\jars\\chromedriver.exe";
	String status;
	
	@BeforeClass
    public void setUp() {
        //Setup Chromedriver
		System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();        
        //full screen window
        driver.manage().window().maximize();
        // wait for the element to appear before the exception occurs
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }
	
	
	//Test to verify sort
	@Test(enabled=true)
	public void testSort() throws Exception {
		SortSection sort=new SortSection(driver);
        
      //read & write testcase status in Test Case excel sheet
		ReadWriteData readexceldata1 = new ReadWriteData();

        //Verifying whether sort works
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        sort.clickSort();
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
        boolean SortResult= sort.confirmSortbyPriceAsc();
        if(SortResult== true) {
        	status="Pass";
        }else {
        	status="Fail";
        }
        //write testcase status in Test Case sheet
        readexceldata1.writeResult(13, status);
        Assert.assertTrue(SortResult);
    }
	
	
	 @AfterClass
	    public void tearDown() {
		 driver.quit();
	    }
}
