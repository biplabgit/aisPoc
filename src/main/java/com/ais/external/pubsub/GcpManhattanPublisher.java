package com.ais.external.pubsub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.stereotype.Component;

@Component
public class GcpManhattanPublisher {
	
	private final String topic ;
    private final PubSubTemplate pubSubTemplate;
    
    
    public GcpManhattanPublisher(
        @Value("maDummyTopic") String topic,
        PubSubTemplate pubSubTemplate) {
        this.topic = topic;
        this.pubSubTemplate = pubSubTemplate;
    }
    
    
    public void publish(String payload) {
        pubSubTemplate.publish(topic, payload);
    }

}
