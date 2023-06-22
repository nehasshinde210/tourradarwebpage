package com.tourradar.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationSection {
	private static String PAGE_URL = "https://www.tourradar.com/d/europe";
    private WebDriver driver;
    
	//Navigation Section Page Objects
	By searchbar= By.xpath("/html/body/header/div/div[3]/div/input");
	By searchResult= By.linkText("3-Day Discover Northern Ireland Small-Group Tour from Dublin");
	By prefpopup= By.xpath("//*[@type='button' and @data-cy='cookie-notification--accept-all-cookies']");
	
	public NavigationSection(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);//returns a Page Object with its fields fully initialised
    }
	
	public void enter_data_searchbar(String Country) {
		
		//if-else if the "Accept Cookies" pop-up is displayed
		WebElement ele = driver.findElement(prefpopup);
		if(ele.isDisplayed()) {
			
			//to accept the pop-up
			WebDriverWait wait6 = new WebDriverWait(driver, Duration.ofSeconds(35));
			wait6.until(ExpectedConditions.visibilityOfElementLocated(prefpopup));	
			wait6.until(ExpectedConditions.elementToBeClickable(prefpopup));
			driver.findElement(prefpopup).click();
		}else {
			
			//to locate the searchbar and provide the input as country name
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchbar));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.findElement(searchbar).click();
			driver.findElement(searchbar).sendKeys(Country);
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();
		}		
		
		
    }
	
	public String confirmSearchResult() {
		
		//to confirm if the correct output is given after search 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(65));
		wait7.until(ExpectedConditions.visibilityOfElementLocated(searchResult));	
		String result= driver.findElement(searchResult).getText();
		return result;	
    }
}
