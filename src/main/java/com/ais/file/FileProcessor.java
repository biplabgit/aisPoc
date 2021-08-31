package com.ais.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.ais.transactionProcessor.PurchaseOrderDataBuilder;

public class FileProcessor {	
	
	static List<String> lines = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		File file = new File("C://Users//bipla//Downloads//DATARECV20210723031510.TXT");
		
		 try {
			 	InputStream input = new FileInputStream(file);
			 	
			 	 InputStreamReader inr = new InputStreamReader(input, "UTF-8");
		         lines = IOUtils.readLines(inr);		         
		         System.out.println("No of Lines - >" + lines.size());
		         groupMessage(lines);		        
		
		 }catch (IOException e) {
	         e.printStackTrace();
	     }		 
	}
	
	
	public static Map<String, List<String>> groupMessage(List<String> lines) {
		
		Map<String, List<String>> splitterMap = new HashMap<String, List<String>>();
		
		 for(String line : lines) {	
			 
        	// System.out.println(line);		        	 
        	 String splitterKey1 = line.split("\\|")[0];	
        	 String splitterKey2 = line.split("\\|")[6];
        	 String finalKey = splitterKey1 + "-" + splitterKey2;
        	// System.out.println(splitterKey);    	        	 
        	 // [0161554519, list<string>]
        	 
        	 if(splitterMap.containsKey(finalKey)) {
        		 
        		List<String> messageList = (List<String>)splitterMap.get(finalKey);
        		messageList.add(line);
        		 
        	 }else {
        		 
        		 List<String> messageList = new ArrayList<String>();
        		 messageList.add(line);
        		 splitterMap.put(finalKey, messageList);        		 
        	 }
        	 
        	// break;
         }
		 
		 
		System.out.println(" Total No of Transaction Messages -> " + splitterMap.size());	
		
		return splitterMap;
		
	}
	
	/*
	 * public static void divertAndProcessTransaction(Map<String, List<String>>
	 * splitterMap) {
	 * 
	 * 
	 * for (Map.Entry<String, List<String>> e : splitterMap.entrySet()) {
	 * 
	 * String splitterKey = e.getKey(); String transactionKey =
	 * splitterKey.split("-")[1];
	 * 
	 * System.out.println("Transaction Key ->" + transactionKey);
	 * 
	 * if("001".equalsIgnoreCase(transactionKey)) {
	 * 
	 * PurchaseOrderDataBuilder.buildPoData(e.getValue()); }else
	 * if("004".equalsIgnoreCase(transactionKey)) {
	 * 
	 * PurchaseOrderDataBuilder.buildPoData(e.getValue()); }
	 * 
	 * 
	 * System.out.println("Key: " + e.getKey() + " Value: " + e.getValue());
	 * 
	 * 
	 * } }
	 */
	

}