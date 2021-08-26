package com.ais.pojos;

public class PurchaseOrderIDDI {
	
	private String transactionName;
	private String date;	

	public PurchaseOrderIDDI(String transactionName, String date) {
		super();
		this.transactionName = transactionName;
		this.date = date;
	}

	public String getName() {
		return transactionName;
	}

	public void setName(String name) {
		this.transactionName = name;
	}

		
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return String.format("PurchaseOrderIDDI [name=%s]", transactionName);
	}

	
}
