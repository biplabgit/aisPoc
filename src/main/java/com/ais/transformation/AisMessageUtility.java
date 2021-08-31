package com.ais.transformation;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.ais.file.FileProcessor;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

public class AisMessageUtility {
		

	public static final String storageConnectionString =
			"DefaultEndpointsProtocol=https;AccountName=asdapocstorage;AccountKey=SKk/zAao7aJ69hk/VOJsAM55SMY8VBT3lOoeiw6wpGVhUyTz8pw9LGdylNPGKlnLGwXYzVw7hYjPkA4bC9toVQ==;EndpointSuffix=core.windows.net;";

	public static void main(String[] args) {
		//read();
		//transactionSplitter();

	}
	
	static List<String> lines = new ArrayList<String>();
	
	/*
	 * public static void read(){ try { // Retrieve storage account from
	 * connection-string. CloudStorageAccount storageAccount =
	 * CloudStorageAccount.parse(storageConnectionString);
	 * System.out.println("connection established"); // Create the blob client.
	 * CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
	 * System.out.println("client created"+blobClient.getStorageUri()); // Retrieve
	 * reference to a previously created container. CloudBlobContainer container =
	 * blobClient.getContainerReference("dc1");
	 * 
	 * CloudBlob blob = container.getBlockBlobReference("PO1.txt"); InputStream
	 * input = blob.openInputStream(); InputStreamReader inr = new
	 * InputStreamReader(input, "UTF-8"); String utf8str = IOUtils.toString(inr);
	 * System.out.println(utf8str);
	 * 
	 * System.out.println("download success");
	 * 
	 * } catch (Exception e) { // Output the stack trace. e.printStackTrace(); } }
	 */
		
	
	public static Map<String, List<String>> transactionSplitter(String blobReference){
		
		Map<String, List<String>> splitterMap = null; 
		
		try
		{
			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
			System.out.println("connection established");
			// Create the blob client.
			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
			System.out.println("client created"+blobClient.getStorageUri());
			// Retrieve reference to a previously created container.
			CloudBlobContainer container = blobClient.getContainerReference("dc1");
			
			 CloudBlob blob = container.getBlockBlobReference(blobReference);
	            InputStream input =  blob.openInputStream();
	            InputStreamReader inr = new InputStreamReader(input, "UTF-8");
	            lines = IOUtils.readLines(inr);
	           
	            System.out.println("No of Lines - >" + lines.size());	            
	           // System.out.println(lines.get(1));
	           // System.out.println("download success");	
	            
	           // Process Lines 
	            
	            splitterMap = FileProcessor.groupMessage(lines);
	            
	                      
			
		}
		catch (Exception e)
		{
			// Output the stack trace.
			e.printStackTrace();
		}
		
		return splitterMap;
	}
	
}
