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
import com.tourradar.PageObjects.ContentSection;

public class ContentSectionTest {
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
	
	@Test(enabled=true)
	public void testDownloadBrochure() throws IOException {
		ContentSection contentpage=new ContentSection(driver);
        
      //read country name from Test Case excel sheet
		ReadWriteData readexceldata2 = new ReadWriteData();
        String emailID= readexceldata2.getEmailAddress();       

        //Verifying whether Download Brochure functionality works
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        contentpage.downloadBrochure(emailID);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        String DBResult= contentpage.confirmEmailSent();
        Boolean DownloadBrochureResult = DBResult.contains("successfully");
        if(DownloadBrochureResult== true) {
        	status="Pass";
        }else {
        	status="Fail";
        }
        
        // write testcase status in Test Case sheet
        readexceldata2.writeResult(16, status);
        Assert.assertTrue(DownloadBrochureResult);
    }
	
	@AfterClass
    public void tearDown() {
	 driver.quit();
    }
}
