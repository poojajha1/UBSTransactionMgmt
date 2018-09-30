package com.ubs.beans;

public class Transactions {


	private Long transactionId ;
	private String instrument ;
	private char transactionType;
	private long transactionQuantity;
	
	public Transactions(Long transactionId, String instrument, char transactionType, long transactionQuantity) {
		super();
		this.transactionId = transactionId;
		this.instrument = instrument;
		this.transactionType = transactionType;
		this.transactionQuantity = transactionQuantity;
	}
	
	
	public Transactions() {
		// TODO Auto-generated constructor stub
	}


	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	public char getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(char transactionType) {
		this.transactionType = transactionType;
	}
	public long getTransactionQuantity() {
		return transactionQuantity;
	}
	public void setTransactionQuantity(long transactionQuantity) {
		this.transactionQuantity = transactionQuantity;
	}
	
}
