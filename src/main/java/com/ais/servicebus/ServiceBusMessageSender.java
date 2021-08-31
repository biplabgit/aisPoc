package com.ais.servicebus;

import com.azure.messaging.servicebus.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.List;

public class ServiceBusMessageSender {
	
	static String connectionStringPO = "Endpoint=sb://aisservicebusasda.servicebus.windows.net/;SharedAccessKeyName=asdaPoQueueAccessPolicy;SharedAccessKey=FgkMFGaNW++lue7hrvW+xQiW0UPXBxdj28GQLP4BEuE=;EntityPath=aispoqueue";
	static String queueNamePO = "aispoqueue";  
	
	static String connectionStringSO = "Endpoint=sb://aisservicebusasda.servicebus.windows.net/;SharedAccessKeyName=asdaSoQueueAccessPolicy;SharedAccessKey=RO/NYWo+Su8JM6tnypQ00jHMjMlM7zmQzsv5SBZCn/Q=;EntityPath=aissoqueue";
	static String queueNameSO = "aissoqueue"; 
	
	public static void main(String[] args) {
		
		sendPoMessage("Testing ..");		
	}

	public static void sendPoMessage(String message)
	{
	    // create a Service Bus Sender client for the queue 
	    ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
	            .connectionString(connectionStringPO)
	            .sender()
	            .queueName(queueNamePO)
	            .buildClient();

	    // send one message to the queue
	    senderClient.sendMessage(new ServiceBusMessage(message));
	    System.out.println("Sent messages to the queue: " + queueNamePO);        
	}
	
	
	public static void sendSoMessage(String message)
	{
	    // create a Service Bus Sender client for the queue 
	    ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
	            .connectionString(connectionStringSO)
	            .sender()
	            .queueName(queueNameSO)
	            .buildClient();

	    // send one message to the queue
	    senderClient.sendMessage(new ServiceBusMessage(message));
	    System.out.println("Sent messages to the queue: " + queueNameSO);        
	}
		
}
