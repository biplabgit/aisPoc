package com.ais.external.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;


@Component
public class PubSubPublisher {
	
	
	private static final String TOPIC = "maDummyTopic";
	
	@Bean
	@ServiceActivator(inputChannel = "pubsubOutputChannel")
	public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
		
		return new PubSubMessageHandler(pubsubTemplate,TOPIC);
	}
	
	@MessagingGateway(defaultRequestChannel = "pubsubOutputChannel")
	public interface PubsubGateWay{		
		void sendToPubSub (String text);
	}
	
	


}
