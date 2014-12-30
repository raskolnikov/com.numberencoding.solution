package com.mehmetaktas.numberencoding.solution.util;

public class ContactPhone {
	
	private String originalNumber;
	private String formattedNumber;
	public ContactPhone(String originalNumber, String formattedNumber) { 
		this.originalNumber = originalNumber;
		this.formattedNumber = formattedNumber;
	}
	public String getOriginalNumber() {
		return originalNumber;
	}
	public void setOriginalNumber(String originalNumber) {
		this.originalNumber = originalNumber;
	}
	public String getFormattedNumber() {
		return formattedNumber;
	}
	public void setFormattedNumber(String formattedNumber) {
		this.formattedNumber = formattedNumber;
	}
	
	
	

}
