package com.ubs.beans;

public class ExpectedEODPositions {

	private String instrument;
	private Long account;
	private char accountType;
	private Long quantity;
	private Long delta;
	
	public ExpectedEODPositions(String instrument, Long account, char accountType, Long quantity, Long delta) {
		super();
		this.instrument = instrument;
		this.account = account;
		this.accountType = accountType;
		this.quantity = quantity;
		this.delta = delta;
	}
	
	public ExpectedEODPositions() {
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
	public Long getDelta() {
		return delta;
	}
	public void setDelta(Long delta) {
		this.delta = delta;
	}
	
	
	
}
