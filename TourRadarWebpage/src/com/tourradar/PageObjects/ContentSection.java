package com.tourradar.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContentSection {
	private static String PAGE_URL = "https://www.tourradar.com/d/europe";
    private WebDriver driver;
    
    //Content Section Page Objects
   By downloadButton= By.xpath("//button[@type='button']");
   By emailAddtxt= By.name("email");   
   By confirmDownloadButton= By.linkText("Download Brochure");
   By EmailSent= By.xpath("//h3[contains(text(),'Brochure successfully sent!')]");
   By prefpopup= By.xpath("//*[@type='button' and @data-cy='cookie-notification--accept-all-cookies']");
   
   //constructor
   public ContentSection(WebDriver driver) {
       this.driver = driver;
       driver.get(PAGE_URL);
       //Initialise Elements
       PageFactory.initElements(driver, this);   //returns a Page Object with its fields fully initialised 
   }
   
   public void downloadBrochure(String emailID) {
	
	   //to scroll until the Download Brochure Button is visible 
	   JavascriptExecutor js = (JavascriptExecutor) driver; 
	   
	    WebElement Element = driver.findElement(downloadButton);
	    js.executeScript("arguments[0].scrollIntoView();", Element);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOfElementLocated(downloadButton));		
		driver.findElement(downloadButton).click();
		
		//to locate email address textbox and provide emailAddress 
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(emailAddtxt));
		driver.findElement(emailAddtxt).sendKeys(emailID);		

		//to click on "Accept the cookies" pop up
		WebDriverWait wait6 = new WebDriverWait(driver, Duration.ofSeconds(35));
		wait6.until(ExpectedConditions.visibilityOfElementLocated(prefpopup));	
		wait6.until(ExpectedConditions.elementToBeClickable(prefpopup));
		driver.findElement(prefpopup).click();
		
		//to click and confirm the brochure send option
		WebElement Element3 = driver.findElement(confirmDownloadButton);
		js.executeScript("arguments[0].scrollIntoView();", Element3);
		WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait7.until(ExpectedConditions.visibilityOfElementLocated(confirmDownloadButton));		
		driver.findElement(confirmDownloadButton).click();   
   }
   
   public String confirmEmailSent() {
	   
	   //to wait and confirm the Success message for Download Brochure 
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	   WebDriverWait wait9 = new WebDriverWait(driver, Duration.ofSeconds(55));
		wait9.until(ExpectedConditions.visibilityOfElementLocated(EmailSent));		
		String Result= driver.findElement(EmailSent).getText();
		return Result;
   }

}
