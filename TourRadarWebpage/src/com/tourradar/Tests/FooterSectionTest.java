package com.tourradar.Tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tourradar.Utilities.ReadWriteData;
import com.tourradar.PageObjects.FooterSection;

public class FooterSectionTest {
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
	public void testAboutUs() throws Exception {
		
		//Verify About Us result
		FooterSection footerPage=new FooterSection(driver);
		ReadWriteData readexceldata4= new ReadWriteData();
        //Verifying whether Download Brochure functionality works
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        footerPage.clickAboutUs();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        String AUResult= footerPage.confirmDetails();
        Boolean AboutUsResult = AUResult.contains("Who We Are");
        if(AboutUsResult== true) {
        	status="Pass";
        }else {
        	status="Fail";
        }
        
        // write testcase status in Test Case sheet
        readexceldata4.writeResult(17, status);
        Assert.assertTrue(AboutUsResult);
    }
	
	@AfterClass
    public void tearDown() {
	 driver.quit();
    }
	

}
