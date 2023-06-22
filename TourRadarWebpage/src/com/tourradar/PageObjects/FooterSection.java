package com.tourradar.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FooterSection {
	private static String PAGE_URL = "https://www.tourradar.com/d/europe";
	private WebDriver driver;
	
	//Footer Section Page Objects
	By aboutUsLink= By.xpath("//a[contains(text(),' About ')]");
	By confirmpage= By.xpath("//h2[contains(text(),'Who We Are')]");
	
	   public FooterSection(WebDriver driver) {
	       this.driver = driver;
	       driver.get(PAGE_URL);
	       //Initialise Elements
	       PageFactory.initElements(driver, this); //returns a Page Object with its fields fully initialised 
	   }
	   
	   public void clickAboutUs() {
			
		   //to scroll till the "About Us" link is visible 
		   JavascriptExecutor js = (JavascriptExecutor) driver;
		   WebElement Element = driver.findElement(aboutUsLink);
		   js.executeScript("arguments[0].scrollIntoView();", Element);
		   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		   wait.until(ExpectedConditions.visibilityOfElementLocated(aboutUsLink));		
		   driver.findElement(aboutUsLink).click();		
	   }
	   
	   public String confirmDetails() {
		   
		   //to confirm if correct details are displayed after clicking on "About Us" link
		   JavascriptExecutor js1 = (JavascriptExecutor) driver;
		   WebElement Element1 = driver.findElement(confirmpage);
		   js1.executeScript("arguments[0].scrollIntoView();", Element1);
		   WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(25));
		   wait1.until(ExpectedConditions.visibilityOfElementLocated(confirmpage));		
		   String Result=driver.findElement(confirmpage).getText();
		   return Result;
	   }

}
