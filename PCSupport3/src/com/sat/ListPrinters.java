package com.sat;

import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class ListPrinters {	
	

	
	private static String getPrinterNames() {
		 String content = "";
		 PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
		 
		 content += "Number of print services: " + services.length;
		 content += "\n";
		 
		 if(services.length != 0 || services != null) {
		  int i = 1;
		  for(PrintService service : services) {
		   String name = service.getName();
		   
		   content += "Printer " + i + " name: " + name;
		   content += "\n";
		   i++;
		  }
		 }
		 
		 System.out.println(content);
		 
		 return content;
		}


}


	



