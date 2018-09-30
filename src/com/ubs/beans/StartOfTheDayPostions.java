package com.ubs.beans;

public class StartOfTheDayPostions {

	private String instrument;
	private Long account;
	private char accountType;
	private Long quantity;
	
	public StartOfTheDayPostions(String instrument, Long account, char accountType, Long quantity) {
		super();
		this.instrument = instrument;
		this.account = account;
		this.accountType = accountType;
		this.quantity = quantity;
	}
	
	public StartOfTheDayPostions() {
		// TODO Auto-generated constructor stub
	}

	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	public Long getAccount() {
		return account;
	}
	public void setAccount(Long account) {
		this.account = account;
	}
	public char getAccountType() {
		return accountType;
	}
	public void setAccountType(char accountType) {
		this.accountType = accountType;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	
	
}
