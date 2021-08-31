package com.ais.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ais")
public class AisTransactionProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AisTransactionProcessorApplication.class, args);
	}	
	
}
