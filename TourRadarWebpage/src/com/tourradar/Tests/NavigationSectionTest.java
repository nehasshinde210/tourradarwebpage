package com.tourradar.Tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tourradar.Utilities.ReadWriteData;
import com.tourradar.PageObjects.NavigationSection;

public class NavigationSectionTest {
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
	
	
	//verify searchbar result
	@Test(enabled=true)
	public void testSearchTour() throws IOException {
		NavigationSection homepage = new NavigationSection(driver);  
        
        //read country name from Test Case excel sheet
        ReadWriteData readexceldata = new ReadWriteData();
        String Country= readexceldata.getTourData();
        
        //Verifying whether search works
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        homepage.enter_data_searchbar(Country);
        String SearchResult= homepage.confirmSearchResult();
        Boolean S_Result = SearchResult.contains(Country);
        if(S_Result== true) {
        	status="Pass";
        }else {
        	status="Fail";
        }
        
        // write testcase status in Test Case sheet
        readexceldata.writeResult(10, status);
        Assert.assertTrue(S_Result);        
    }
	
	
	 @AfterClass
	    public void tearDown() {
		 driver.quit();
	    }


}
