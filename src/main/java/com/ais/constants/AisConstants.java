package com.ais.constants;

public interface AisConstants {
	
	
	public static final String PURCHASE_ORDER_TX = "00001";
	public static final String STORE_ORDER_TX = "00004";
 
	public static String connectionStringPO = "Endpoint=sb://aisservicebusasda.servicebus.windows.net/;SharedAccessKeyName=asdaPoQueueAccessPolicy;SharedAccessKey=FgkMFGaNW++lue7hrvW+xQiW0UPXBxdj28GQLP4BEuE=;EntityPath=aispoqueue";
	public static String queueNamePO = "aispoqueue";  
	
	public static String connectionStringSO = "Endpoint=sb://aisservicebusasda.servicebus.windows.net/;SharedAccessKeyName=asdaSoQueueAccessPolicy;SharedAccessKey=RO/NYWo+Su8JM6tnypQ00jHMjMlM7zmQzsv5SBZCn/Q=;EntityPath=aissoqueue";
	public static String queueNameSO = "aissoqueue"; 
	
	public static final String storageConnectionString =
			"DefaultEndpointsProtocol=https;AccountName=asdapocstorage;AccountKey=SKk/zAao7aJ69hk/VOJsAM55SMY8VBT3lOoeiw6wpGVhUyTz8pw9LGdylNPGKlnLGwXYzVw7hYjPkA4bC9toVQ==;EndpointSuffix=core.windows.net;";
}
