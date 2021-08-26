package com.ais.processor;

import java.util.Date;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ais.pojos.PurchaseOrderIDDI;

@RestController
public class GenericFileProcessorController {
	
	
	
	  @RequestMapping(method = RequestMethod.GET, path = "/process/{fileName}")
	  public PurchaseOrderIDDI processTransaction (@PathVariable String fileName) {	 
		  
		  return new PurchaseOrderIDDI(fileName,new Date().toString());  
	  
	  }
	
}
