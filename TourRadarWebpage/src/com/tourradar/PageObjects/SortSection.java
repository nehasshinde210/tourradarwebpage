package com.tourradar.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SortSection {
	private static String PAGE_URL = "https://www.tourradar.com/d/europe";
    private WebDriver driver;
    int first_price_int, second_price_int;
    
    //Sort Section Page Objects
    By sortdropdown= By.name("sort");
    By priceFirst= By.xpath("/html/body/main/div[2]/div[1]/ul/li[1]/div[3]/div[1]/div/dl/dd/span[2]");    
    By priceSecond= By.xpath("/html/body/main/div[2]/div[1]/ul/li[2]/div[3]/div[1]/div/dl/dd/span[2]");
    By prefpopup= By.xpath("//*[@type='button' and @data-cy='cookie-notification--accept-all-cookies']");
    
    public SortSection(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this); //returns a Page Object with its fields fully initialised 
    }
    
    public void clickSort() {
		//method to click on sort and sort by the preference provided in the input sheet
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOfElementLocated(sortdropdown));
		WebElement sort=driver.findElement(sortdropdown);
		Select select=new Select(sort);
		select.selectByVisibleText("Total price: Lowest first");
    }
    
public boolean confirmSortbyPriceAsc() throws Exception {
	Thread.sleep(20000); // till the page is loaded after sort
	
	//to accept the "Accept cookie" pop-up
	WebDriverWait wait6 = new WebDriverWait(driver, Duration.ofSeconds(35));
	wait6.until(ExpectedConditions.visibilityOfElementLocated(prefpopup));	
	wait6.until(ExpectedConditions.elementToBeClickable(prefpopup));
	driver.findElement(prefpopup).click();	
		
	JavascriptExecutor js = (JavascriptExecutor) driver;
	//after page is loaded to check price of first tour
    WebElement Element = driver.findElement(priceFirst);
    js.executeScript("arguments[0].scrollIntoView();", Element);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
	wait.until(ExpectedConditions.visibilityOfElementLocated(priceFirst));		
		
	String first_price_str=driver.findElement(priceFirst).getText();
	
	JavascriptExecutor js1 = (JavascriptExecutor) driver;
	//after page is loaded to check price of second tour 
    WebElement Element1 = driver.findElement(priceSecond);
    js1.executeScript("arguments[0].scrollIntoView();", Element1);
	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(45));
	wait1.until(ExpectedConditions.visibilityOfElementLocated(priceSecond));		
	
	String second_price_str=driver.findElement(priceSecond).getText();
	
	//to convert string into integer for comparison 
	first_price_int= Integer.parseInt(first_price_str);
	second_price_int= Integer.parseInt(second_price_str);
	
	//sort by lowest price works fine if first tour price is less then second tour price
		if(second_price_int > first_price_int) {
			return true;
		}else {
			return false;
		}		
    }

}
