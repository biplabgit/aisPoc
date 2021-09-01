package com.ais.processor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ais.constants.AisConstants;
import com.ais.external.pubsub.GcpManhattanPublisher;
import com.ais.external.pubsub.PubSubPublisher.PubsubGateWay;
import com.ais.pojos.PurchaseOrderIDDI;
import com.ais.pojos.PurchaseOrderMA;
import com.ais.pojos.ResponseObject;
import com.ais.pojos.StoreOrderMA;
import com.ais.servicebus.ServiceBusMessageSender;
import com.ais.transactionProcessor.PurchaseOrderDataBuilder;
import com.ais.transformation.AisMessageUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class GenericFileProcessorController {
	
	@Autowired
	private GcpManhattanPublisher gcpManhattanPublisher;
		  
	/**
	 * 
	 * @param fileName to be processed , 
	 * @return  Transaction status along with details of transactions processed , number of transactions processed
	 * @throws Any exception during processing
	 */  	
	  @RequestMapping(method = RequestMethod.GET, path = "/process/{fileName}")
	  public String processTransaction (@PathVariable String fileName) throws Exception{	 	 
		  
		  ResponseObject responseObject = new ResponseObject();
		  String transactionTypes = "";  
		  
		  // transform , send to queue, send to pub sub		  
		  //call to process file from storage and create Splitter Map
		  
		  Map<String, List<String>> splitterMap = AisMessageUtility.transactionSplitter(fileName);		  
		  responseObject.setNumberOfTransactions(splitterMap.size());
		  
		  // Use Splitter Map to process the transactions further 		  
		  for (Map.Entry<String, List<String>> e : splitterMap.entrySet()) {
				
				String splitterKey = e.getKey();
				String transactionKey = splitterKey.split("-")[1];				
				transactionTypes = transactionTypes + transactionKey + "-";				
				System.out.println("Transaction Key ->" + transactionKey);
				
				if(AisConstants.PURCHASE_ORDER_TX.equalsIgnoreCase(transactionKey)) {
					
					PurchaseOrderMA poMA = PurchaseOrderDataBuilder.buildPoData(e.getValue());				
					ObjectMapper mapper = new ObjectMapper();
					
					try {
						
						String convertedData = mapper.writeValueAsString(poMA);							
						//Send Data to Queue
						ServiceBusMessageSender.sendPoMessage(convertedData);						
						System.out.println(" Sent data to PO Message Queue");						
						//Send Data to PubSub
						gcpManhattanPublisher.publish(convertedData);						
						System.out.println("Sent Data to Google Pub Sub");
						
					} catch (JsonProcessingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						responseObject.setStatus("Failure");
					} catch (Exception ex) {						
						ex.printStackTrace();
						responseObject.setStatus("Failure");
					}							
				}else if(AisConstants.STORE_ORDER_TX.equalsIgnoreCase(transactionKey)) {
					
					StoreOrderMA soMA = PurchaseOrderDataBuilder.buildSoData(e.getValue());					
					ObjectMapper mapper = new ObjectMapper();
					
					try {
						String convertedData = mapper.writeValueAsString(soMA);							
						//Send Data to Queue
						ServiceBusMessageSender.sendSoMessage(convertedData);						
						System.out.println(" Sent data to SO Message Queue");	
												
					} catch (JsonProcessingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						responseObject.setStatus("Failure");
					} catch (Exception ex) {						
						ex.printStackTrace();
						responseObject.setStatus("Failure");
					}					
				}						
	           // System.out.println("Key: " + e.getKey()  + " Value: " + e.getValue());						
			}		  
		  
		  responseObject.setStatus("Success");
		  responseObject.setTransactionTypes(transactionTypes);	  
		  
		  return new ObjectMapper().writeValueAsString(responseObject);  	  
	  }
	
}
