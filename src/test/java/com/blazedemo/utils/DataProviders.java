package com.blazedemo.utils;

import java.io.IOException;

import org.testng.annotations.*;

public class DataProviders {
	
  @DataProvider(name="PassengerDetail")
  public String [][] getData() throws IOException {
	  
	  String path =".//testData//Blazedemo_Passenger_Details.xlsx";
	  
	  ExcelUtility xlutil = new ExcelUtility(path);
	  
	  int totalrows = xlutil.getRowCount("Sheet1");
	  int totalcols = xlutil.getCellCount("Sheet1", 1);
	  
	  String details[][] = new String [totalrows][totalcols];
	  
	  for(int i=1;i<=totalrows;i++) {
		  
		  for(int j=0;j<totalcols;j++) {
			  
			  details[i-1][j]=xlutil.getCellData("Sheet1", i, j);
		  }
	  }
	  
	  return details;
  }
}
