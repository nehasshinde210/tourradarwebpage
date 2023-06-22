package com.tourradar.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadWriteData {
	
	//to get input data from Test Case sheet for search functionality
	public String getTourData() throws IOException
	{  
		//obtaining input bytes from a file  
		FileInputStream fis=new FileInputStream(new File("C:\\Users\\NS00853858\\eclipse-workspace\\TourRadarWebpage\\TestData\\Test Case.xls"));  
		//creating workbook instance that refers to .xls file  
		HSSFWorkbook wbk=new HSSFWorkbook(fis);   
		//creating a Sheet object to retrieve the object  
		HSSFSheet sheet=wbk.getSheetAt(0);  
		String tourData=sheet.getRow(10).getCell(5).getStringCellValue(); 
	 	wbk.close();
		return tourData;  
	}
	
	//to get input data from Test Case sheet for download Brochure functionality
	public String getEmailAddress() throws IOException
	{  
		//obtaining input bytes from a file  
		FileInputStream fis1=new FileInputStream(new File("C:\\Users\\NS00853858\\eclipse-workspace\\TourRadarWebpage\\TestData\\Test Case.xls"));  
		//creating workbook instance that refers to .xls file  
		HSSFWorkbook wbk1=new HSSFWorkbook(fis1);   
		//creating a Sheet object to retrieve the object  
		HSSFSheet sheet1=wbk1.getSheetAt(0);  
		String emailAddress=sheet1.getRow(16).getCell(5).getStringCellValue();
	 	wbk1.close();
		return emailAddress;  		
	}
	
	//to write Pass/Fail status in Test Case sheet for each functionality
	public void writeResult(int row,String result) throws IOException
	{  
		//obtaining input bytes from a file  
		FileInputStream fis3=new FileInputStream(new File("C:\\Users\\NS00853858\\eclipse-workspace\\TourRadarWebpage\\TestData\\Test Case.xls"));  
		//creating workbook instance that refers to .xls file  
		HSSFWorkbook wbk3=new HSSFWorkbook(fis3);   
		//creating a Sheet object to retrieve the object  
		HSSFSheet sheet3=wbk3.getSheetAt(0);
		sheet3.getRow(row).getCell(9).setCellValue(result);
        
		//To write into Excel File
		FileOutputStream outputStream = new FileOutputStream("C:\\Users\\NS00853858\\eclipse-workspace\\TourRadarWebpage\\TestData\\Test Case.xls");
		wbk3.write(outputStream);  
	}
}
